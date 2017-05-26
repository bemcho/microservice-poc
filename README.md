# Intoduction

This is a very early PoC implementation of a microservice architecture. 
In *this moment* it is an active project and consist of:

* API Gateway Service - uses Spring Cloud Netflix - Zuul Proxy. Routes API calls, load balancer, circuit breaker, avoids CORS issues.
* registry service - uses Spring Cloud Netflix - Eureka. Visible only internally and not via the gateway.
* product service - a spring boot app exposing REST API. Operates as a resource server communicating with the `uaa-service`.
* uaa service - authentication server and user management.

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

Though this is not a must start the services in the following order:

* registry service
* uaa serive
* product service
* api gateway

# More information

Check our [wiki](https://github.com/REWE-Digital-Bulgaria/microservice-poc/wiki) for details.

# TODO

Countless features:

* Monitoring (e.g. ELK stack)
* Hystrix integration
* Client apps 
* Price and promotion service
* Lots and lots easy to follow documentation
* CI
* Dockerizing
* etc etc etc
