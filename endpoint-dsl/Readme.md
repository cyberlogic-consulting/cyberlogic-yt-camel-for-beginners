# Working With Apache Camel Endpoint DSL for Beginners
Code samples demonstrated in video that is not yet published.

## How to run
To run the example, first enable local sftp server (need docker client installed):
```shell script
docker compose -f ./src/main/resources/sftp/docker/docker-compose.yml up
```
And then run the java main class `ch.cyberlogic.camel.examples.Main` either inside your IDE by yourself or use the following maven command:
```shell script
./mvnw org.codehaus.mojo:exec-maven-plugin:java -D"exec.mainClass=ch.cyberlogic.camel.examples.Main"
```

## How to run tests
Tests can be run either inside your IDE or using the following maven command:
```shell script
./mvnw test
```