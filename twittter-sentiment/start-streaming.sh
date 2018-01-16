#!/bin/sh

#packing project jar
sbt assembly

#set spark bin path
export PATH=$PATH:/usr/spark2.0.1/bin

#submit the jar to spark to run
/usr/spark2.0.1/bin/spark-submit --class com.manju.twitter.TwitterStreaming --jars jars/stanford-corenlp-3.6.0-models.jar,jars/stanford-corenlp-3.6.0.jar,jars/ejml-0.23.jar target/twitter-sentiment-assembly-1.0.jar
