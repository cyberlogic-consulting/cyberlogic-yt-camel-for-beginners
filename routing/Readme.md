# Apache Camel Routing Patterns for Beginners
Code samples demonstrated in https://www.youtube.com/watch?v=d74rGcoeg64 .

## How to run
To run the example, first enable Artemis MQ (need docker client installed):
```shell script
docker run --detach --name mycontainer -p 61616:61616 -p 8161:8161 --rm apache/activemq-artemis:2.38.0-alpine
```
You can either run the java main class `ch.cyberlogic.camel.examples.Main` inside your IDE by yourself or using the following maven command:
```shell script
mvn org.codehaus.mojo:exec-maven-plugin:java -D"exec.mainClass=ch.cyberlogic.camel.examples.Main"
```
