# Interstellar Travel System Service

Welcome, this is a showcase of my Java / Spring Boot skills together with the tools and frameworks that are in the **"Tech Stack"** section.<br/>
A rich share of my learnings and experiences as well.
<br/>
<br/>

## CONTEXT | BACKGROUND

The above graph represents an interstellar transport system used by Earth’s inhabitants in the year 2145. <br/>
They require you to build a system that will allow them to find the shortest path from point “A”, being Earth, through the galaxy to any of the planets represented by the other nodes. <br/>
You are provided with a list of node names and their respective distances between their linked planets. <br/>
In addition, you need to ensure that the system will work from any source to destination coordinates specified.
<br/>
<br/>

![Image Alt text](assets/interstellar-graph.png "Optional title")

<br/>
<br/>



## TECH STACK

**NB :** My main plan is to showcase my skills in the tech stack below.

The technology stack used in the project is :

* Java (JDK 17.x )
* Maven 3.8.7
* Docker ( either Docker or Rancher ) & Kubernetes
* Spring Boot 3.3.1 (with Spring 6.1.10)
* Jakarta Persistence API Specification 2.2.3 (supported by the Hibernate Implementation 5.6.15)
* Postgress database 16.2
* Project Lombok
* MapStruct ( for Model / DTO mapping )
* REST Web Service ( with Spring MVC, JSON, Swagger UI)
* JUNit 5
* Mockito
* JaCoco ( Java Code Coverage )
* Apache Commons ( for Text, Collections utilities )
* Apache POI ( for MS Excel Sheet Processing )
* Dijkstra's algorithm ( for the shorted path calculation )
<br/>
<br/>



## SETTING UP

You need to setup the tools and tech stack mentioned in the list above first. <br/>
You need to set up the following ENVARS ( Evnironment Variabled ) : <br/>

  * **DATABASE_HOST** : for your database host IP. This will mostly be localhost since you will be running locally.
  * **DATABASE_PORT** : This will be your database port.
  * **DATABASE_USERNAME** : Database username.
  * **DATABASE_PASSWORD** : Database password.
<br/>
<br/>



## BUILDING APPLICATION

### Building App ( Jar )

Given the technology stack mantioned above you will mainly need Java 17 installed on your machine.<br/>
You can use the maven included in the repo with via the supllied scipts for both Windows OS (`mvnw.cmd`) and Unix OS (`mvnw`) based machines.<br/>
Make sure that you have **Docker Desktop** or **Rancher Desktop**

1. To build the project change to the root directory and use the command `./mvnw clean install` to build
2. When the build is done you can run the service using `./mvnw spring-boot:run`
<br/>

### Building Docker Image
To containerize the application you can run the script.<br/>
```bash
./automation/build-app-image-docker.sh
```
<br/>
<br/>



## RUNNING APPLICATION

### Running Within IDE

For local development within your IDE you can run the class `com.toob.service.shortest.Application`.<br/>
This class is the main entry point and where the applicaiton programming starts.<br/>

**NB :** _The run process has been hooked up with with Postgres Database which will startup create and start up the database service inside your Docker Engine._<br/>

### Running Within Docker

Otherwise you can run it via scripts.<br/>
```bash
# Starting up the Database Container
./automation/postgres/start-database.sh

# Starting up the Application Container
./automation/app/start-app.sh
```

### Running Within Kubernetes

1. When running within Kubernetes the main thing is that you will need to use the local Docker Registry that's where Kubernetes will pull from.<br/>
This means you have to set up a local Registry unless you want to configure things differenly to use the Public Docker Hub Registry.<br/>
To set up localy you can run: <br/>
```bash
# Create and Start up Local Docker Registry
./automation/registry/start-registry.sh
```
2. You can make use of the HELM Charts for Kubernetes inside the directory `../src/kube` be running scripts : <br/>
```bash
# To Deploy to Kubernetes
./automation/kube/kube-deploy.sh

# To UnDeploy to Kubernetes
./automation/kube/kube-undeploy.sh
```
<br/>
<br/>



## SERVICE INFO

This application has two Web Services. A REST & SOAP type.

To access or use these APIs please see the follwing information :

* **REST** : This is for managing the Planets & Routes Entities, with the base path at : `http://localhost:8003/interstellar/rest`

There's a Postman collection for both Web Service types in the `src/test/resources` where the filename is : `Shortest Path API.postman_collection.json`

Importing that file will give more context on the Web Services and to test the Web Services.
<br/>
<br/>



## TEST COVERAGE

As mentioned above I am using JaCoCo for test coverage. The service is curretnly sitting at **94%** coverage. <br/>
After bulding the app with tests you can find the report in : `target/site/index.html`. <br/>
The image below has been attached in the folder ${roo}/assets/jacoco-test-coverage-report.png<br/>

Open that in using your preferred browser.
<br/>
<br/>

![Image Alt text](assets/jacoco-test-coverage-report.png "Coverate Report")

<br/>
<br/>

## CONCLUSION

This was a good almost well rounded show case of Java / Spring, Integration with something like a Database. <br/>
This also showcases Docker and Kubernetes and some Automation via Bash Scripting. ( though I prefere Golang but, yea ) a little bash does not hurt.

I will improve it as time goes. Thanks for Tuning in.
<br/>
<br/>
