FROM openjdk:8-jdk-alpine
ARG PORT=8080
ARG JAR_FILE=target/api.jar
VOLUME [ "/data" ]
EXPOSE ${PORT}
COPY . .
ENTRYPOINT [ "java", "-jar", "api.jar" ]
