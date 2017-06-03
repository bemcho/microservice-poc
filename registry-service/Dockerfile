FROM java:8-jre
ADD ./build/libs/registry-service.jar app.jar

ENTRYPOINT ["sh", "-c", "java -Xmx256m -Djava.security.egd=file:/dev/./urandom -jar /app.jar"]

HEALTHCHECK --interval=20s --timeout=3s CMD curl -f http://localhost:5051/health || exit 1

EXPOSE 5051