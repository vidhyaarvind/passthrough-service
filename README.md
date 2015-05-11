#Simple Kafka client implementation using finatra

##Start zookeeper
cd /opt/zookeeper-3.4.6/bin/
./zkServer.sh start

##start kafka
cd /opt/kafka_2.10-0.8.2.1/bin
./kafka-server-start.sh ../config/server.properties


##create topic if one does not exist (topic name is test)
./kafka-topics.sh --zookeeper localhost:2181 --create --topic test --partitions 1 --replication-factor 1


##build and start the http server
sbt assembly

java -jar ~/passthrough-service/target/scala-2.10/passthrough-service-assembly-0.1-SNAPSHOT.jar

//for dynamic conf file
java -jar ~/passthrough-service/target/scala-2.10/passthrough-service-assembly-0.1-SNAPSHOT.jar -Dconfig.file=application.conf
  
curl -XGET http://localhost:8888/status

curl -XPOST http://localhost:8888/event -d "data=water6"


##test if the data got to kafka (see the data as you post data or use --from-beginning option)
./kafka-console-consumer.sh --zookeeper localhost:2181 --topic test
