from pyspark import SparkContext
from pyspark.streaming import StreamingContext
from pyspark.streaming.kafka import KafkaUtils
from pykafka import KafkaClient
import json
import sys

def putDataToKafka(iter):
	client = KafkaClient(hosts="e.cloudxlab.com:6667")
	topic = client.topics['one-mindata']
	with topic.get_producer() as producer:
		for record in iter:
			print "i am here"
			producer.produce(json.dumps(record))

sc = SparkContext(appName="KafkaWordCount")
ssc = StreamingContext(sc, 0.5)
zkQuorum, topic = sys.argv[1:]
kvs = KafkaUtils.createStream(ssc, zkQuorum, "spark-streaming-consumer", {topic: 1})
lines = kvs.map(lambda x: x[1])
words = lines.flatMap(lambda line: line.split(" "))
pairs = words.map(lambda word: (word, 1))
wordCounts = pairs.reduceByKey(lambda x, y: x + y)
wordCounts.foreachRDD(lambda rdd: rdd.foreachPartition(putDataToKafka))
ssc.start()
ssc.awaitTermination()
