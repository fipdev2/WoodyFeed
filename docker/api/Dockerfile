# Etapa 1: build com Maven
FROM maven:3.9.5-eclipse-temurin-17 as builder
WORKDIR /app

COPY ./quiz /app
RUN mvn clean package -DskipTests

# Etapa 2: imagem leve com JDK 17
FROM eclipse-temurin:17-jdk-alpine
WORKDIR /app
COPY --from=builder /app/target/*.jar app.jar

ENTRYPOINT ["java", "-jar", "app.jar"]
