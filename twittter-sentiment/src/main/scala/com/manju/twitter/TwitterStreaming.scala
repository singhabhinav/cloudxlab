package com.manju.twitter


import org.apache.spark.SparkConf
import org.apache.spark.SparkContext
import org.apache.spark.SparkContext._
import org.apache.spark.streaming.StreamingContext
import org.apache.spark.streaming.Seconds
import scala.sys.SystemProperties
import org.apache.spark.streaming.twitter.TwitterUtils
import java.util.Properties
import org.apache.kafka.clients.producer.ProducerConfig

import com.manju.twitter.SentimentAnalyser._
import com.typesafe.config.ConfigFactory

/**
 * @author Manjunath Kempaiah
 * @version 1.0
 * 
 * Class connects to twitter using apache spark streaming api and twitter streaming api to fetch the popular ten hash tags in
 * the last 60 seconds and also does the sentiment analysis on the tweets using stanford nlp library.
 *
 */
object TwitterStreaming {
  
  val appConf = ConfigFactory.load();
  
  /*
   * Method sets the system properties for twitter api.
   */
  def setTweeterSystemProperties() {
    val property = new SystemProperties()
    property.put("twitter4j.oauth.consumerKey", appConf.getString("twitter4j.oauth.consumerKey"))
    property.put("twitter4j.oauth.consumerSecret", appConf.getString("twitter4j.oauth.consumerSecret"))
    property.put("twitter4j.oauth.accessToken", appConf.getString("twitter4j.oauth.accessToken"))
    property.put("twitter4j.oauth.accessTokenSecret", appConf.getString("twitter4j.oauth.accessTokenSecret"))
  }
  
  /*
   * Method creates the properties object by setting the necessary configuration parameters for connecting
   * to apache kafka cluster.
   */
  def getKafkaProperties(): Properties = {
    val props = new Properties();
    props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, appConf.getString("bootstrap.servers.config"))
    props.put(ProducerConfig.CLIENT_ID_CONFIG, appConf.getString("client.id.config"))
    props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, appConf.getString("key.serializer.class.config"))
    props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, appConf.getString("value.serializer.class.config"))
    
    return props
  }
  
  /*
   * Method where the main code starts.
   */
  def main(args: Array[String]) {
    val conf = new SparkConf().setAppName(appConf.getString("application.name")).setMaster(appConf.getString("master.mode"))
    val sc = new SparkContext(conf)
    
    sc.setLogLevel(appConf.getString("log.level"))
    
    setTweeterSystemProperties();
    
    val ssc = new StreamingContext(sc, Seconds(5))
    
    val stream  = TwitterUtils.createStream(ssc,None)
    
    val prodcer = new KafkaTweetProducer(getKafkaProperties())
       
    //Fetching the tweets from the twitter.
    val tweets = stream.window(Seconds(60)).map(tweet => tweet);
    
    //Fetching the popular ten hash tags along with count in the last 60 seconds.
    val topCounts60 = tweets.flatMap(tweet => tweet.getText.split(" ").filter(_.startsWith("#"))).map((_, 1)).reduceByKey(_ + _).map{case (tweet, count) => (count, tweet)}
                      .transform(_.sortByKey(false))
    
    //Sending the popular ten hash tags(json converted string) to kafka topic.
    topCounts60.foreachRDD(rdd => {
      val topList = rdd.take(10)
      var str = "{\"tweets\":["
      if(topList.length > 0) {
        topList.foreach{case (count, tag) => str = str + "{\"tag\":\"%s\", \"count\":%d},".format(tag, count)}
        prodcer.sendMessage(appConf.getString("twitter.streaming.topic"), str.substring(0, str.length()-1) + "]}")
      }
    })   
    
    //Doing sentiment analysis on the tweets in the last 60 seconds.
    val data = tweets.filter(tweet => tweet.getText.contains("#")).map(tweet =>{
                  val sentiment = detectSentiment(tweet.getText)
                  (tweet.getText, sentiment)
                })
    
    //Sending the analysed tweet along with sentiment to kafka topic.
    data.foreachRDD(rdd => {
      val topList = rdd.collect()
      topList.foreach{case (text, sentiment) => 
        val str = "{\"tweet\":\"%s\", \"sentiment\":\"%s\"}".format(text, sentiment).toString()
        prodcer.sendMessage(appConf.getString("twitter.sentiment.topic"), str)}
    })
    
    
    ssc.start()

    ssc.awaitTermination()
    
    
  }
}
