# Working With SQL Databases in Apache Camel for Beginners
Code samples demonstrated in https://youtu.be/Nix3W8NJpTk .

## How to run
To run the example, first enable local postgresql database (need docker client installed):
```shell script
docker compose -f ./src/main/resources/docker/docker-compose.yml up
```
This docker-compose file also runs a pgAdmin instance that can be accessed at `http://localhost:8888/browser/`.

After the docker-compose is up, application can be started by running the java main class `ch.cyberlogic.camel.examples.Main`.
You can run it inside your IDE by yourself, in this case, before running the code, you will need to run the following maven goal:
```shell script
./mvnw openjpa:enhance
```
Or you can simply run the application with a single command by using the following maven goals:
```shell script
./mvnw compile org.codehaus.mojo:exec-maven-plugin:java -D"exec.mainClass=ch.cyberlogic.camel.examples.Main"
```
