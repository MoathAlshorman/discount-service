ARG BASE_IMAGE="eclipse-temurin:11-jre"
FROM ${BASE_IMAGE}
LABEL maintainer="moath.alshorman@hotmail.com"
ARG PROFILE="dev"
ENV EXPOSE_PORT=8080
ENV MONGO_AUTH_DATABASE=admin
ENV MONGO_USERNAME=root
ENV MONGO_PASSWORD=root
ENV MONGO_DATABASE=discount_db
ENV MONGO_PORT=27017
ENV MONGO_HOST=mongodb
COPY target/discounts-service.jar app.jar
EXPOSE ${EXPOSE_PORT}
ENV PROFILE=$PROFILE
ENTRYPOINT ["java", "-Dspring.profiles.active=${PROFILE}", "-jar", "app.jar"]
