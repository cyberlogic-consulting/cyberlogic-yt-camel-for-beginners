# Apache Camel Routing Patterns for Beginners
Code samples demonstrated in https://youtu.be/48LO_rmxRgc .

## How to run
To run the example, first enable Artemis MQ (need docker client installed):
```shell script
docker run --detach --name mycontainer -p 61616:61616 -p 8161:8161 --rm apache/activemq-artemis:2.38.0-alpine
```
You can either run the java main class `ch.cyberlogic.camel.examples.Main` inside your IDE by yourself or use the following maven command:
```shell script
./mvnw compile org.codehaus.mojo:exec-maven-plugin:java -D"exec.mainClass=ch.cyberlogic.camel.examples.Main"
```
