# Creating SOAP server with Apache Camel and Spring Boot for Beginners
Code samples demonstrated in a video that is not yet published.

## How to run
You can either run the application inside your IDE by yourself or use the following maven command:
```shell script
./mvnw spring-boot:run
```
After application is started, you can use the following command to get wsdl:
```shell script
curl http://localhost:8080/services/pets?wsdl
```