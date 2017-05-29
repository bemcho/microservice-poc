[![Build Status](https://api.travis-ci.org/Digital-Bulgaria/microservice-poc.svg?branch=master)](https://travis-ci.org/Digital-Bulgaria/microservice-poc)
[![Language](http://img.shields.io/badge/language-java-brightgreen.svg)](https://www.java.com/)


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

# More information

Check our [wiki](https://github.com/REWE-Digital-Bulgaria/microservice-poc/wiki) for details.

# TODO

Countless features:

* Monitoring (e.g. ELK stack)
* Client apps 
* Price and promotion service
* CI
* Dockerizing the application
* etc etc etc
