#Twitter Streaming Project

#Prerequisite
1. Download and Install Node.js.
2. Download and Run Apache Spark.
3. Download and Run Apache Kafka along with Apache Zookeeper.
4. Download and Install SBT.

#Clone the Project into the local machine
git clone https://github.com/ManjunathKmph/twitter-sentiment.git

#Project Design
![Alt Project Design](/images/ProjectDesign.png "Project Design")

#Steps to prepare the project
1. Download the Stanford NLP models jar from the below link and copy it into twitter-sentiment/jars folder.
  * https://drive.google.com/file/d/0B89AibnP3IZzcUhmZ2o3Q2lmM2M/view?usp=sharing
2. Open the file from the path src/main/resources/application.properties and change the values for the following key
  * twitter4j.oauth.consumerKey
  * twitter4j.oauth.consumerSecret
  * twitter4j.oauth.accessToken
  * twitter4j.oauth.accessTokenSecret
  * bootstrap.servers.config
3. Create the following kafka topics
  * kafka-topics.sh --create --topic twitterTopic --zookeeper localhost:2181 --replication-factor 1 --partitions 1
  * kafka-topics.sh --create --topic sentimentTopic --zookeeper localhost:2181 --replication-factor 1 --partitions 1
4. Change the spark home path in the following file
  * start-streaming.sh
5. Command to execute the files in the machine
  * chmod 777 start-server.sh
  * chmod 777 start-streaming.sh

#Steps to run the project
1. Open the terminal and type the following line
  * ./start-server.sh  -- To check the server is up and running successfully, in the console it will print use the url localhost:8888/web
2. Hit the http://localhost:8888/web in the browser and should show the following image.
![Alt Initial Screen](/images/initialscreen.png "Initial Screen")
3. Open another terminal and type the follwing line
  * ./start-streaming.sh
4. Refresh the browser url and should show the following image.
![Alt Inital Streaming Screen](/images/second.png "Inital Streaming Screen")

#How to start sentiment analysis for the hash tag
1. In the browser url, type the hash tag name(ex:- food) in the textbox and click on show tweets. It should show the following image.
![Alt Sentiment Analysis Screen](/images/third.png "Sentiment Analysis Screen")

Note:- In the above, code will search the mentioned hash tags in the tweets for the last 60 seconds, if it appear in the tweet then it will do the sentiment analysis using Stanford NLP library and marks the tweet as positive/negaitve/neutral/notunderstood. For the mentioned hash tag if the sentiment graph doesn't show then please wait till someone uses the hashtag in the last 60 seconds.
   
