FROM maven:3.9.5-eclipse-temurin-8 as builder
WORKDIR /app
COPY . /app/.
RUN mvn -f /app/pom.xml clean package -Dmaven.test.skip=true
FROM openjdk:8-jdk-alpine as runtime
WORKDIR /app
COPY --from=builder /app/target/*.jar /app/.jar
COPY --from=builder /app/assets/. /app/assets/.
EXPOSE 9090
ENTRYPOINT ["java", "-jar", "/app/.jar"]