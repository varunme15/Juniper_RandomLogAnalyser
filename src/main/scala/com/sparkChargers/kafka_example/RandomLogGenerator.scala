package com.sparkChargers.kafka_example

import java.util.{Date, Properties}
import kafka.javaapi.producer.Producer
import kafka.producer.{KeyedMessage, ProducerConfig}
import Constants._
import kafka.producer
import org.joda.time.DateTime

import scala.collection.JavaConversions._

import kafka.Kafka

import scala.util.Random


object LogGenerator extends App {
  val random = new Random()

  val props = new Properties()
  props ++= Map(
    "serializer.class" -> "com.sparkChargers.kafka_example.LogEncoder",
    "metadata.broker.list" -> "127.0.0.1:9093"
  )

  val config = new ProducerConfig(props)
  val producer = new Producer[String, Log](config)

 
  var i = 0
  // infinite loop
  while(true) {
    val timestamp = System.currentTimeMillis()
    val publisher = Publishers(random.nextInt(NumPublishers))
    val advertiser = Advertisers(random.nextInt(NumAdvertisers))
    val website = s"website_${random.nextInt(Constants.NumWebsites)}.com"
    val cookie = s"cookie_${random.nextInt(Constants.NumCookies)}"
    val geo = Geos(random.nextInt(Geos.size))
    val bid = math.abs(random.nextDouble()) % 1
    val log = Log(timestamp, publisher, advertiser, website, geo, bid, cookie)
    producer.send(new KeyedMessage[String, Log](Constants.KafkaTopic, log))
    i = i + 1
    if (i % 1000 == 0) {
      println(s"Sent $i messages!")
    }
  }
}
