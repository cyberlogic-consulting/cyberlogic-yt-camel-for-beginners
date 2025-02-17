# Apache Camel Message Mapping for Beginners
Code samples demonstrated in a video that is not yet published.

## How to run
You can either run the java main class `ch.cyberlogic.camel.examples.Main` inside your IDE by yourself or use the following maven command:
```shell script
./mvnw compile org.codehaus.mojo:exec-maven-plugin:java -D"exec.mainClass=ch.cyberlogic.camel.examples.Main"
```
After application is started, you can either copy jsons from `./src/main/resources` to Postman or similar program, or run the following commands to create pets:
```shell script
curl http://localhost:8080/petstore/api/pet -X POST -H "Content-Type: application/json" --data-binary "@./src/main/resources/pet0.json" -i
curl http://localhost:8080/petstore/api/pet -X POST -H "Content-Type: application/json" --data-binary "@./src/main/resources/pet1.json" -i
curl http://localhost:8080/petstore/api/pet -X POST -H "Content-Type: application/json" --data-binary "@./src/main/resources/pet2.json" -i
```
Then, to get, delete or update pets, you can use the following commands:
```shell
# to get pet by id:
curl http://localhost:8080/petstore/api/pet/0
curl http://localhost:8080/petstore/api/pet/1
curl http://localhost:8080/petstore/api/pet/2
# to get all pets:
curl http://localhost:8080/petstore/api/pet
# to delete pet by id:
curl http://localhost:8080/petstore/api/pet/0 -X DELETE
curl http://localhost:8080/petstore/api/pet/1 -X DELETE
curl http://localhost:8080/petstore/api/pet/2 -X DELETE
# to update pet by id:
curl http://localhost:8080/petstore/api/pet/0 -X PUT -H "Content-Type: application/json" -d "<your updated json string here>" -i
curl http://localhost:8080/petstore/api/pet/1 -X PUT -H "Content-Type: application/json" -d "<your updated json string here>" -i
curl http://localhost:8080/petstore/api/pet/2 -X PUT -H "Content-Type: application/json" -d "<your updated json string here>" -i
```
To get an OpenApi schema, you can use the following command:
```shell script
curl http://localhost:8080/petstore/api/api-doc
```