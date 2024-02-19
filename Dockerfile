FROM adoptopenjdk:17-jdk-hotspot-bionic AS builder
COPY target/*.jar app.jar
WORKDIR /app
EXPOSE 8080
ENTRYPOINT ["java","-jar","/app.jar"]