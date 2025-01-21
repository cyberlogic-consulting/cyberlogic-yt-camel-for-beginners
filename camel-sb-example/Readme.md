# Apache Camel with Spring Boot for Beginners
Code samples demonstrated in https://youtu.be/y2h5sUYDl34 .

## How to run
To run the example, first enable Artemis MQ (need docker client installed):
```shell script
docker run --detach --name mycontainer -p 61616:61616 -p 8161:8161 --rm apache/activemq-artemis:2.38.0-alpine
```
And then run the spring boot application either inside your IDE by yourself or using the following maven command:
```shell script
mvn spring-boot:run
```

## How to run tests
Tests are run either inside your IDE or using the following maven command:
```shell script
mvn test
```