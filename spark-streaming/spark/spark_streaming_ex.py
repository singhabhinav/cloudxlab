from pyspark import SparkContext
from pyspark.streaming import StreamingContext
from pyspark.streaming.kafka import KafkaUtils
from pykafka import KafkaClient
import json
import sys
import pprint

def putDataToKafka(iter1):
	client = KafkaClient(hosts="ip-172-31-13-154.ec2.internal:6667")
	topic = client.topics['one-mindata']
	for sr in iter1:
		with topic.get_producer() as producer:
			producer.produce(json.dumps(sr))

def putDataToKafka1(iter1):
	client = KafkaClient(hosts="ip-172-31-13-154.ec2.internal:6667")
	topic = client.topics['one-mindata']
	with topic.get_producer() as producer:
		for sr in iter1:
			sr.pprint()
			producer.produce(json.dumps(sr))


zkQuorum, topic = sys.argv[1:]
sc = SparkContext(appName="KafkaWordCount")
ssc = StreamingContext(sc, 1)
kvs = KafkaUtils.createStream(ssc, zkQuorum, "spark-streaming-consumer", {topic: 1})
lines = kvs.map(lambda x: x[1])
words = lines.flatMap(lambda line: line.split(" "))
pairs = words.map(lambda word: (word, 1))
wordCounts = pairs.reduceByKey(lambda x, y: x + y)
wordCounts.foreachRDD(lambda rdd: rdd.foreachPartition(putDataToKafka))
ssc.start()
ssc.awaitTermination()
