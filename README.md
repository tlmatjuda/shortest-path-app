# Interstellar Travel System Service

The above graph represents an interstellar transport system used by Earth’s inhabitants in the year 2145.

They require you to build a system that will allow them to find the shortest path from point “A”, being Earth, through the galaxy to any of the planets represented by the other nodes.

You are provided with a list of node names and their respective distances between their linked planets.

In addition, you need to ensure that the system will work from any source to destination coordinates specified.

</br>

</br>

## Tech Stack

The technology stack used in the project is :

* **Java** (JDK 17.x )
* Maven 3.8.7
* Spring Boot 2.7.17 (with Spring 5.3.30)
* JPA Specification 2.2.3 (supported by the Hibernate Implementation 5.6.15)
* Apache Derby Database ( In-Memory )
* Project Lombok
* MapStruct ( for model / dto mapping )
* JAXB (Java Architector for XML Binding )
* WSDL4J
* SOAP Web Services ( WSDL)
* REST Web Service ( with Spring MVC, JSON, Swagger UI)
* Swagger API Documentation
* JSON
* JUNit 5
* Mockito
* JaCoco ( Java Code Coverage )
* Apache Commons ( for Text, Collections utilities )
* Apache POI ( for MS Excel Sheet Processing )
* Dijkstra's algorithm ( for the shorted path calculation )



## Setting Up

Given the technology stack mantioned above you will mainly need Java 17 installed on your machine.

You can use the maven included in the repo with via the supllied scipts for both Windows OS (`mvnw.cmd`) and Unix OS (`mvnw`)based machines.


1. To build the project change to the root directory and use the command `./mvnw clean install` to build
2. When the build is done you can run the service using `mvn spring-boot:run`

</br>

## Service Info

This application has two Web Services. A REST & SOAP type.

To access or use these APIs please see the follwing information : 

* SOAP : You can get the WSDL by accessing this url : `http://localhost:8003/interstellar/soap/shortest-path.wsdl` and the service is running on `http://localhost:8003/interstellar/soap`.
* REST : This is for managing the Planets & Routes Entities, with the base path at : `http://localhost:8003/interstellar/rest`


There's a Postman collection for both Web Service types in the `src/test/resources` where the filename is : `Shortest Path API.postman_collection.json`

Importing that file will give more context on the Web Services and to test the Web Services.






* [Official Apache Maven documentation](https://maven.apache.org/guides/index.html)
* [Spring Boot Maven Plugin Reference Guide](https://docs.spring.io/spring-boot/docs/2.7.17/maven-plugin/reference/html/)
* [Create an OCI image](https://docs.spring.io/spring-boot/docs/2.7.17/maven-plugin/reference/html/#build-image)
* [Spring Web](https://docs.spring.io/spring-boot/docs/2.7.17/reference/htmlsingle/index.html#web)
* [Spring Boot DevTools](https://docs.spring.io/spring-boot/docs/2.7.17/reference/htmlsingle/index.html#using.devtools)
* [Spring Configuration Processor](https://docs.spring.io/spring-boot/docs/2.7.17/reference/htmlsingle/index.html#appendix.configuration-metadata.annotation-processor)

### Guides

The following guides illustrate how to use some features concretely:

* [Building a RESTful Web Service](https://spring.io/guides/gs/rest-service/)
* [Serving Web Content with Spring MVC](https://spring.io/guides/gs/serving-web-content/)
* [Building REST services with Spring](https://spring.io/guides/tutorials/rest/)
