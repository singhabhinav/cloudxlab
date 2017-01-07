package com.manju.twitter

import java.util.Properties
import org.apache.kafka.clients.producer.KafkaProducer
import org.apache.kafka.clients.producer.ProducerRecord

/**
 * @author Manjunath Kempaiah
 * @version 1.0
 * 
 * Class uses the kafka api to send the message
 * to the specific topics.
 *
 */
class KafkaTweetProducer(props:Properties) {
  
  val producer = new KafkaProducer[String, String](props)
  
  /*
   * Method will send the message to the specific kafka topic.
   * 
   * topic -- Topic name.
   * message -- Message to publish.
   */
  def sendMessage(topic:String, message:String) {
    val data = new ProducerRecord[String, String](topic, null, message)
    producer.send(data)
  }
  
  /*
   * Method will close the producer connection to the Kafka cluster.
   */
  def closeProducer() {
    producer.close()
  }
  
}