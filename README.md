[![Build Status](https://api.travis-ci.org/Digital-Bulgaria/microservice-poc.svg?branch=master)](https://travis-ci.org/Digital-Bulgaria/microservice-poc)
[![Language](http://img.shields.io/badge/language-java-brightgreen.svg)](https://www.java.com/)


# Intoduction

This is a very early PoC implementation of a microservice architecture. At *this moment* it is an active project and consist of:

* API Gateway Service - uses Spring Cloud Netflix Zuul project. It routes API calls and avoids CORS issues. This is the single entry point into the "backend".
* Registry service - uses Spring Cloud Netflix Eureka project. Should be normally visible only internally and not exposed through the gateway.
* Product service - a spring boot app exposing simplistic REST API. Operates as a resource server communicating with the `uaa-service`.
* UAA service - authentication server and user management service.
* Configuration service - a service that holds the configurations for the remaining services.
* Monitoring service - a monitoring service which gives an overview of performance, load and failed services.

The project is organized as a gradle multi module project due to its educational purpose. Normally each service would be self contained.

# More information

Check our [wiki](https://github.com/REWE-Digital-Bulgaria/microservice-poc/wiki) for details.

# TODO

This is an ongoing educational project with countless possible features:

* Client apps;
* Price and promotion service;
* Kafka image for inter-service asynchronous communication;
* etc etc etc;

At some moment of time it will be frozen and used as a reference for a real project.
