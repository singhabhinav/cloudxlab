from pyspark import SparkContext
from pyspark.streaming import StreamingContext
from pyspark.streaming.kafka import KafkaUtils
from pykafka import KafkaClient
import json
import sys
import pprint

def pushOrderStatusInKafka(status_counts):
    client = KafkaClient(hosts="ip-172-31-13-154.ec2.internal:6667")
    topic = client.topics['order-one-min-data']
    for status_count in status_counts:
	    with topic.get_producer() as producer:
		    producer.produce(json.dumps(status_count))

zkQuorum, topic = sys.argv[1:]
sc = SparkContext(appName="KafkaOrderCount")
ssc = StreamingContext(sc, 60)
kvs = KafkaUtils.createStream(ssc, zkQuorum, "spark-streaming-consumer", {topic: 1})
lines = kvs.map(lambda x: x[1])
status_count = lines.map(lambda line: line.split(",")[2]) \
              .map(lambda order_status: (order_status, 1)) \
              .reduceByKey(lambda a, b: a+b)
status_count.pprint()
status_count.foreachRDD(lambda rdd: rdd.foreachPartition(pushOrderStatusInKafka))
ssc.start()
ssc.awaitTermination()
