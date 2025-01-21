# cyberlogic-yt-camel-for-beginners
Code samples demonstrated in the [Apache Camel Complete Beginner's Guide](https://youtube.com/playlist?list=PLhOKPsimMS-zdKEE_F53TKNROx2uJBPF6&feature=shared) playlist of the Cyberlogic Consulting's YouTube channel.

## How to prepare all the examples at once
You can clean, build, test and package _all_ the provided examples in this folder in one go:
```shell script
./mvnw clean package
```

Please note that some examples require a running docker client on your machine. Watch the corresponding videos or checkout the `Readme.md` files for more detail. 

## List of the topics covered:
1. [Building Your First Camel Route](./building-your-first-camel-route/)
2. [Working With Endpoints](./working-with-endpoints/)
3. [Apache Camel Routing](./routing/)
4. [Apache Camel Message Translation](./message-mapping/)
5. [Apache Camel Error Handling](./error-handling/)
6. [Apache Camel Testing](./testing/)
7. [Apache Camel with Spring Boot](./camel-sb-example/)
8. [Apache Camel with Quarkus](./camel-quarkus-example/)
9. [Working With Beans in Apache Camel](./working-with-beans/)
9. [Apache Camel Variables](./variables/)
9. [Apache Camel Endpoint DSL](./endpoint-dsl/)
