'''
Run using the command
spark-submit word_count.py 2>/dev/null
'''

from pyspark import SparkContext
from pyspark import SparkConf
from pyspark.streaming import StreamingContext

# Create a local StreamingContext with batch interval of 10 seconds
conf = SparkConf()
conf.setAppName('WordCount')
sc = SparkContext(conf=conf)
ssc = StreamingContext(sc, 10)

# Create a DStream that will connect to hostname:port, like localhost:9999
lines = ssc.socketTextStream("localhost", 9999)

# Split each line in each batch into words
words = lines.flatMap(lambda line: line.split(" "))

# Count each word in each batch
pairs = words.map(lambda word: (word, 1))
wordCounts = pairs.reduceByKey(lambda x, y: x + y)

# Print the elements of each RDD generated in this DStream to the console
wordCounts.pprint()

# Start the computation
ssc.start()

# Wait for the computation to terminate
ssc.awaitTermination()
