cd spark-streaming

# Setup node modules
cd node
npm install


# Run Spark Streaming
## Login to one web console,
cd ../spark-streaming/spark
spark-submit --jars spark-streaming-kafka-assembly_2.10-1.6.0.jar spark_streaming_order_status.py localhost:2181 order_data

# Put order status in Kafka
## Login to new web console window
cd ../spark-streaming/kafka
/bin/bash put_order_data_in_topic.sh ../data/order_data/ ip-172-31-13-154.ec2.internal:6667 order_data

# Run Node.js server
## Login to new web console window
cd ../spark-streaming/node
node index
