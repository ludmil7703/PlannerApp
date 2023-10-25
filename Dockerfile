FROM maven:3.6.3-jdk-11-slim AS build
WORKDIR /app

COPY . .
RUN mvn clean package -DskipTests

FROM openjdk:11-jre-slim
WORKDIR /app

COPY --from=build /target/PlannerApp-0.0.1-SNAPSHOT.jar PlannerApp-0.0.1-SNAPSHOT.jar
EXPOSE 8080
ENTRYPOINT ["java","-jar","PlannerApp-0.0.1-SNAPSHOT.jar"]
