# Testing Apache Camel Routes for Beginners
Code samples demonstrated in https://youtu.be/1O_yhnWUHpY .

## How to run
You can either run the java main class `ch.cyberlogic.camel.examples.Main` inside your IDE by yourself or use the following maven command:
```shell script
./mvnw compile org.codehaus.mojo:exec-maven-plugin:java -D"exec.mainClass=ch.cyberlogic.camel.examples.Main"
```

## How to run tests
Tests can be run either inside your IDE or using the following maven command:
```shell script
./mvnw test
```
