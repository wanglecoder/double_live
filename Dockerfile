FROM openjdk:8-jdk-alpine
VOLUME /tmp
ARG JAR_FILE
COPY ${JAR_FILE} double.jar
ENTRYPOINT ["java","-jar","/double.jar"]
EXPOSE 8080