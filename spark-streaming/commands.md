# Spark submit
spark-submit --jars spark-streaming-kafka-assembly_2.10-1.6.0.jar spark_streaming_order_status.py localhost:2181 order_test

# Put order status in Kafka
/bin/bash put_order_data_in_topic.sh ../data/order_data/ ip-172-31-13-154.ec2.internal:6667 order_test

# For running node
node index
