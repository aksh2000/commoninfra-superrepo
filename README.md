# commoninfra-superrepo
Super Repo for Backend Services for -Pagebook -Quora -CMS -CRM -Hungry

## Common Dependencies
  - `Eureka Discovery Client`
  - `OAuth2 Resource Server`
  - `Spring boot Actuator`
  
## General Instructions:
  1. Clone the main branch
  2. Create a new branch from main branch
  3. Add your spring boot application folder with required dependencies [Java 8, JAR, Spring Boot 2.4.2]
  4. Create a application.yml file in resources folder of your springboot application
  5. Add the following configuration in the application.yml file


```
spring:
  application:
    name: YOURMODULETITLE-YOURSERVICENAME[ex: PAGEBOOK-POSTS] {DO NOT USE ANY UNDERSCORES WHILE NAMING}

eureka:
  client:
    registerWithEureka: true
    serviceUrl:
      defaultZone: ${EUREKA_SERVER:http://10.177.68.70:8761/eureka}

management:
  endpoints:
    web:
      exposure:
        include:
          - mappings
          - env
          - health
          - info
```
