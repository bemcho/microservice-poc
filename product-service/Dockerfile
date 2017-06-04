FROM java:8-jre
ADD ./build/libs/product-service.jar app.jar

ENTRYPOINT ["sh", "-c", "java -Xmx256m -Dspring.profiles.active=docker -jar /app.jar"]

EXPOSE 5000