We made this project as an example to show working of our high level architecture for Juniper Innovation Contest - 2014. This is random log analyser which uses apache kafka to generate RDD which are analysed by Spark. The analysed data is put into mongodb.

Technology Stack Used in the project Apache Zookeeper Apache Kafka MongoDB

To run this project we need to run following commands in order.

$ sbt pack
This command will compile the project and generate the class file

Start your Zookeeper:

$ /usr/share/zookeeper/bin/zkServer.sh start-foreground
To Start Kafka Just go to your kafka root folder and run this command:

$ bin/kafka-server-start.sh config/kafka-server1.properties
Then we need to create a topic in kafka which can be created using this command. Just go to your kafka root folder and run this command:

$ bin/kafka-topics.sh --create --zookeeper localhost:2181 --replication-factor 1 --partitions 1 --topic ad-topic

(replication depends upon the no of nodes of kafka running. For single system, localhost it will be 1)
Run MongoDB:

$ sudo mongod
On one window, run the aggregator:

$ target/pack/bin/aggregator
On the other one, run the adserver log random generator:

$ target/pack/bin/generator
You can also see the messages that are sent using the Kafka console consumer:

$ kafka-console-consumer.sh --topic ad-topic --zookeeper localhost:2181
After a few seconds, you should be able to see the results in MongoDB in collection named adddb.
