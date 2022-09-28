FROM maven:latest AS MAVEN_BUILD
WORKDIR /build/
LABEL MAINTAINER="Firas Ben Saad"
COPY pom.xml /build/
COPY src /build/src/
RUN mvn package

FROM eclipse-temurin:17-jre
WORKDIR /app
COPY --from=MAVEN_BUILD /build/target/activity-system-0.0.1-SNAPSHOT.jar /app/
ENTRYPOINT ["java", "-jar", "activity-system-0.0.1-SNAPSHOT.jar"]
