FROM frolvlad/alpine-oraclejdk8
ADD ./build/libs/monitoring-service.jar app.jar

ENTRYPOINT ["sh", "-c", "java -Xmx256m -Djava.security.egd=file:/dev/./urandom -jar /app.jar"]

EXPOSE 5088