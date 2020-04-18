# Start from Java 8 image
FROM openjdk:8-jdk-alpine

# For Java 11
# FROM adoptopenjdk/openjdk11:alpine-jre

# This is final uber jar
ARG EXECUTABLE_FILE=target/recipe-app-0.0.1-SNAPSHOT.jar

# Change to the working dir
WORKDIR /opt/app

# Copy the uber jar to container path
COPY ${EXECUTABLE_FILE} application.jar

# Execute the jar
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","application.jar"]