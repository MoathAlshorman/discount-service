ARG BASE_IMAGE="eclipse-temurin:11-jre"
FROM ${BASE_IMAGE}
LABEL maintainer="moath.alshorman@hotmail.com"
ARG PROFILE="dev"
ENV PROFILE=$PROFILE
COPY target/discounts-service.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java", "-Dspring.profiles.active=${PROFILE}", "-jar", "app.jar"]