FROM eclipse-temurin:17-jdk-alpine

LABEL maintainer="anudeepkulkarni111@gmail.com"

ARG JAR_FILE=target/*.jar
ADD ${JAR_FILE} app.jar

EXPOSE 8083

ENTRYPOINT ["java","-jar","/app.jar"]