FROM maven:latest AS MAVEN_BUILD
WORKDIR /build/
COPY pom.xml /build/
RUN mvn dependency:go-offline
COPY src /build/src/
RUN mvn package

FROM eclipse-temurin:17-jre
WORKDIR /app
COPY --from=MAVEN_BUILD /build/target/activity-system-0.0.1-SNAPSHOT.jar /app/
ENTRYPOINT ["java", "-jar", "activity-system-0.0.1-SNAPSHOT.jar"]
