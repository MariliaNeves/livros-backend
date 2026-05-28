FROM maven:3.9.9-eclipse-temurin-17 AS build

WORKDIR /app

COPY pom.xml .
COPY src ./src

RUN mvn -q -DskipTests package

FROM eclipse-temurin:17-jre

WORKDIR /app

RUN useradd -r -u 1001 spring

COPY --from=build /app/target/*.jar app.jar

EXPOSE 8080

USER spring

ENTRYPOINT ["java", "-jar", "/app/app.jar"]
