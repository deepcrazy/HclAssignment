# HCL Assignment - Spring Boot User Microservice

## Table of Contents

- [Prerequisites](#prerequisites)
- [Getting Started](#getting-started)
    - [Building the Project](#building-the-project)
    - [Running the Application](#running-the-application)
- [Endpoints](#endpoints)
- [Request Validation](#request-validation)
- [Circuit Breaker](#circuit-breaker)
- [Logging](#logging)
- [Security](#security)
- [Testing](#testing)
- [Sample Postman Commands](#sample-postman-commands)

## Prerequisites

Before you begin, ensure you have met the following requirements:

- [Java 8](https://adoptopenjdk.net/)
- [Maven](https://maven.apache.org/install.html)

## Getting Started

### Building the Project

#### Clone the repository
git clone https://github.com/yourusername/your-application.git

#### Change directory
cd your-application

#### Build with Maven
~~~
./mvnw clean install
~~~

#### Run with Maven
~~~
./mvnw spring-boot:run
~~~

#### Or Run within IntelliJ Idea IDE 
- Go to File -> ProjectStructure -> And Choose JDK 1.8
- Go to HclAssignmentApplication.java file and Run it.

### Endpoints

- **GET /api/userdetails/{empId}**: Get user details by emp ID.
- **PUT /api/userdetails/{empId}**: Update user details by emp ID and update the user details passed in request body.

#### Request Validation

- Incoming requests are validated whether empId is non-zero numeric or not.
- Update/PUT endpoint only updates those fields which are not null and non-zero.

#### Circuit Breaker And Roll back transactions

- The circuit breaker pattern is implemented using CircuitBreaker's fallback mechanism along with the usage of @Transactional for database calls.

#### Logging

- Entry and exit logging are implemented to track method calls.

#### Security

- Pseudo code is provided for Basic authentication over [here]().

#### Testing

- Unit tests and integration tests are available.

#### Run unit tests
~~~
mvn test
~~~

#### Run integration tests
~~~
mvn verify
~~~

# GET User Details
~~~
curl -X GET http://localhost:8080/api/userdetails/1
~~~

# PUT Update User Details
~~~

curl -X PUT -H "Content-Type: application/json" -d '{"title":"mr","firstName":"UpdatedfirstName","lastName":"UpdatedLastName","gender":"male","address":{"street":"Updated address street","city":"Updated city","state":"Updated state","postcode":98765}}' http://localhost:8080/api/userdetails/2
~~~
