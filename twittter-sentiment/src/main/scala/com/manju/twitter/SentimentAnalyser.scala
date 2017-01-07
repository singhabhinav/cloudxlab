package com.manju.twitter

import java.util.Properties
import edu.stanford.nlp.pipeline.StanfordCoreNLP
import edu.stanford.nlp.ling.CoreAnnotations
import edu.stanford.nlp.sentiment.SentimentCoreAnnotations

import scala.collection.JavaConversions._
import edu.stanford.nlp.neural.rnn.RNNCoreAnnotations
import edu.stanford.nlp.sentiment.SentimentCoreAnnotations.SentimentAnnotatedTree

/**
 * @author Manjunath Kempaiah
 * @version 1.0
 * 
 * Class does the sentiment analysis on the tweets using stanford nlp library.
 *
 */
object SentimentAnalyser {
 
  /*
   * Fetching custom Sentiment value based on conditions from the stanford sentiment value.
   */
  def fetchSentimentType(sentiment:Int): String = {
      sentiment match {
        case sentiment if sentiment == 0 => NOT_UNDERSTOOD.toString()
        case sentiment if sentiment == 1 => NEGATIVE.toString()
        case sentiment if sentiment == 2 => NEUTRAL.toString()
        case sentiment if sentiment == 3 => POSITIVE.toString()
        case sentiment if sentiment == 4 => VERY_POSITIVE.toString()
        case sentiment if sentiment > 4 => NOT_UNDERSTOOD.toString()
      }
  }
  
  /*
   * Real fun begins from this method where it does the sentiment analysis on the tweet
   * using Stanford NLP library.
   */
  def detectSentiment(message:String): String = {
    //if the message is blank then return the sentiment value as not understood.
    if(message == null || message == "" || message.length() == 0) {
        NOT_UNDERSTOOD
    }
    
    val props = new Properties()
    props.setProperty("annotators", "tokenize, ssplit, pos, lemma, parse, sentiment")
    val pipeline = new StanfordCoreNLP(props)
    //Calling stanford NLP api to process the message.
    val annotation = pipeline.process(message)
    
    var sentiment = 0
    var longest = 0
    
    for (sentence <- annotation.get(classOf[CoreAnnotations.SentencesAnnotation])) {
      val tree = sentence.get(classOf[SentimentCoreAnnotations.SentimentAnnotatedTree])
      val tempSentiment = RNNCoreAnnotations.getPredictedClass(tree)
      val partText = tempSentiment.toString
      if (partText.length() > longest) {
        sentiment = tempSentiment
        longest = partText.length()
      }      
    }
    fetchSentimentType(sentiment)    
  }
  
}
