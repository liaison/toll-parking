FROM maven:3.6.3-jdk-11-slim AS build
RUN mkdir -p /workspace

COPY api /workspace/api
WORKDIR /workspace

RUN mvn -B -f api/pom.xml clean package -DskipTests
FROM openjdk:11-jdk-slim
RUN mkdir -p api
COPY --from=build /workspace/api/target/*.jar /api/app.jar

EXPOSE 8080
ENTRYPOINT ["java","-jar","/api/app.jar"]
