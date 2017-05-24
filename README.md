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

Start the services in the following order.

## Registry service

From the project root execute:

```
gradlew :registry-service:bootRun
```
This will start Eureka at at [http://localhost:5051/](http://localhost:5051). Should be accessible via browser.

![image](https://cloud.githubusercontent.com/assets/10339738/26397475/271238d6-407f-11e7-990c-b99891a82575.png)

## Product service

From the project root execute:

```
gradlew :product-service:bootRun
```

This will start the product service exposing REST API at [http://localhost:5000/](http://localhost:5000).

Test it:

```
curl -X GET \
  http://localhost:5000/api/products/1 \
  -H 'cache-control: no-cache' \
  -H 'postman-token: 9c8802ea-b848-8c3a-2873-0b1b51fa014b'
```

Expected response is:

```
{
  "id": 1,
  "sku": "KAM1",
  "name": "Beer Kamentiza"
}
```

The product service is now also registered at Eureka and should be available there.

## API Gateway service (zuul)

The Zuul proxy should be able to contact Eureka and route incomming calls accordingly. Start the proxy.


From the project root execute:

```
gradlew :api-gateway-service:bootRun
```

The proxy should be up and running at port 8080 now. It should "hide" all other services and route the requests accordingly with its configuration. Test it.

```
curl -X GET \
  http://localhost:8080/api/products/1 \
  -H 'cache-control: no-cache' \
  -H 'postman-token: 9c8802ea-b848-8c3a-2873-0b1b51fa014b'
```

The answer of the product service (shown above) should be routed to the client.

# TODO

Countless features:

* UAA service
* Monitoring
* Hystrix integration
* Client apps 
* Price and promotion service
* Docus
* etc etc etc
