# commoninfra-superrepo
Super Repo for Backend Services for -Pagebook -Quora -CMS -CRM -Hungry

## Common Dependencies
  - `Eureka Discovery Client`
  - `OAuth2 Resource Server`. Add this depedency upon common infra teams intimation, There is a seperate process to configure when OAuth is involved, Team is still working on it
  - `Spring boot Actuator`
  
## General Instructions:
  1. Clone the main branch
  2. Create a new branch from main branch
  3. Add your spring boot application folder with required dependencies [Java 8, JAR, Spring Boot 2.4.2]
  4. GoTo commoninfra-superrepo -> pom.xml and add your application name in the modules section ```<module>business</module>```
  5. Create a application.yml file in resources folder of your springboot application
  6. Add the following configuration in the application.yml file


```
spring:
  application:
    name: YOURMODULETITLE-YOURSERVICENAME[ex: pagebook-business] {DO NOT USE ANY UNDERSCORES WHILE NAMING}

eureka:
  client:
    registerWithEureka: true
    serviceUrl:
      defaultZone: ${EUREKA_SERVER:http://10.177.68.9:8761/eureka}

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

  7. Register your service with the gateway
      - commoninfra-superrepo -> gateway -> src -> main -> resources -> application.yml
      - add your service in the routes based on following example
      ```
      - id: pagebook-business
        uri: lb://pagebook-business
        predicates:
        -Path=/pagebook/business/**
      ```
   8. You can see your registry's status at [Eureka Dashboard](http://10.177.68.70:8761/)
   
   9. PLEASE MAKE SURE YOU ARE CONNECTED TO **BIBLI CORP WIFI**
   
   10. For Further Assistance - Reach Common Infra Team at B - Basement
