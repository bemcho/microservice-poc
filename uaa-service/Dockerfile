FROM java:8-jre
ADD ./build/libs/uaa-service.jar app.jar

ENTRYPOINT ["sh", "-c", "java -Xmx256m -jar /app.jar"]

EXPOSE 5060