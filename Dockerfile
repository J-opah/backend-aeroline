# Stage 1: Build usando Maven oficial
FROM maven:3.9.4-eclipse-temurin-17 AS build

WORKDIR /app

# Copia apenas pom.xml e src para aproveitar cache do Docker
COPY pom.xml .
COPY src src

# Build do projeto
RUN mvn clean package -DskipTests

# Stage 2: Runtime
FROM eclipse-temurin:17-jdk-jammy

WORKDIR /app

# Copia o JAR do stage de build
COPY --from=build /app/target/*.jar app.jar

# Expondo a porta do Spring Boot
EXPOSE 3333

# Comando para iniciar a aplicação
ENTRYPOINT ["java", "-jar", "app.jar"]