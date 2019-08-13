# FROM java:8
FROM openjdk:8-jdk-alpine
ADD build/libs/api-demo-0.0.1-SNAPSHOT.jar /app.jar
ENTRYPOINT ["java","-jar","/app.jar"]