import org.apache.spark._
import org.apache.spark.streaming._

object WordCount {
    def main(args: Array[String]) {
        // Create a local StreamingContext with batch interval of 10 second
        val conf = new SparkConf().setAppName("WordCount")
        val ssc = new StreamingContext(conf, Seconds(10))

        // Create a DStream that will connect to hostname:port, like localhost:9999
        val lines = ssc.socketTextStream("localhost", 9999)

        // Split each line in each batch into words
        val words = lines.flatMap(_.split(" "))

        // Count each word in each batch
        val pairs = words.map(word => (word, 1))
        val wordCounts = pairs.reduceByKey(_ + _)

        // Print the elements of each RDD generated in this DStream to the console
        wordCounts.print()

        // Start the computation
        ssc.start()

        // Wait for the computation to terminate
        ssc.awaitTermination()
    }
}
