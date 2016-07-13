from pyspark import SparkContext
from pyspark.streaming import StreamingContext
from pyspark.streaming.kafka import KafkaUtils
from pykafka import KafkaClient
import json
import sys
import pprint

def pushDataInKafka(iter1):
    client = KafkaClient(hosts="ip-172-31-13-154.ec2.internal:6667")
    topic = client.topics['one-mindata']
    for sr in iter1:
	    with topic.get_producer() as producer:
		    producer.produce(json.dumps(sr))

zkQuorum, topic = sys.argv[1:]
sc = SparkContext(appName="KafkaOrderCount")
ssc = StreamingContext(sc, 10)
kvs = KafkaUtils.createStream(ssc, zkQuorum, "spark-streaming-consumer", {topic: 1})
lines = kvs.map(lambda x: x[1])
status_count = lines.map(lambda line: line.split(",")[2]) \
              .map(lambda order_status: (order_status, 1)) \
              .reduceByKey(lambda a, b: a+b)
status_count.pprint()
status_count.foreachRDD(lambda rdd: rdd.foreachPartition(pushDataInKafka))
ssc.start()
ssc.awaitTermination()
