# Intoduction

This is a very early PoC implementation of a microservice architecture. 
In *this moment* it is an active project and consist of:

* API Gateway Service - uses Spring Cloud Netflix - Zuul Proxy. Routes API calls, load balancer, circuit breaker, avoids CORS issues.
* Registry service - uses Spring Cloud Netflix - Eureka. Visible only internally and not via the gateway.
* Product service - a spring boot app exposing REST API. Operates as a resource server communicating with the `uaa-service`.
* UAA service - authentication server and user management.
* Configuration service - a service that holds the configurations for the remaining services.
* Monitoring service - a monitoring service which gives an overview of potential failures.

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

To start everything first start:

* the registry service
* the config service

Then in any order:

* the uaa serive
* the product service
* the api gateway
* the monitoring service

There is a convenient script for Windows users located in `util-scripts/windows`. Just execute `start-microservices.bat` and this will enable you to start the services interactively. If this is not possible each service can be started from the project root by executing:

```
gradlew :<service-name>:bootRun
```

Here `service-name` is the name of the service, e.g. `uaa-service`.

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
