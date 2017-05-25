# Intoduction

This is a very early PoC implementation of a microservice architecture. Currently under construction.

In *this moment* it consist of:

* API Gateway Service - uses Spring Cloud Netflix - Zuul Proxy. Routes API calls, load balancer, circuit breaker, avoids CORS issues. Later will be enhanced. [http://localhost:8080/](http://localhost:8080)
* registry service - uses Spring Cloud Netflix - Eureka. Runs at [http://localhost:5051/](http://localhost:5051). Visible only internally.
* product service - a spring boot app exposing REST API. Runs at [http://localhost:5000/](http://localhost:5000).
* uaa service - authentication server and user management. Runs at [http://localhost:5060/](http://localhost:5060).


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

Start the services in the following order.

* registry service
* uaa serive
* product service
* api gateway

# More information

Under constuction. Check our [wiki](https://github.com/REWE-Digital-Bulgaria/microservice-poc/wiki).

# TODO

Countless features:

* Monitoring (e.g. ELK stack)
* Hystrix integration
* Client apps 
* Price and promotion service
* Lots of documentation
* etc etc etc
