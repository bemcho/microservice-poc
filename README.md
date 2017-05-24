# Intoduction

This is a very early PoC implementation of a microservice architecture. Currently under construction.

In *this moment* it consist of:

* registry service - uses Spring Cloud Netflix - Eureka. Runs at [http://localhost:5051/](http://localhost:5051).
* product service - a spring boot app exposing REST API and registered at Eureka. Runs at [http://localhost:5000/](http://localhost:5000).
* API Gateway Service - uses Spring Cloud Netflix - Zuul Proxy. Routes API calls and avoids CORS issues. Later will be enhanced. [http://localhost:8080/](http://localhost:8080)

The project is organized in a gradle multi module project.

# Import in Eclipse/STS

Clone the project:

```
git clone https://github.com/REWE-Digital-Bulgaria/microservice-poc.git
```

Switch to the project directory and execute:

```
gradlew eclipse
```

This will prepare the eclipse files for all subprojects. These can be now imported as existing java projects.

# Run

## Registry service

From the project root start:

```
gradlew :registry-service:bootRun
```
This will start Eureka at at [http://localhost:5051/](http://localhost:5051). Should be accessible via browser.


