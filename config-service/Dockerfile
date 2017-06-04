FROM java:8-jre
ADD ./build/libs/config-service.jar app.jar

ENTRYPOINT ["sh", "-c", "java -Xmx256m -jar /app.jar"]

HEALTHCHECK --interval=20s --timeout=3s CMD curl -f http://localhost:5070/health || exit 1

EXPOSE 5070