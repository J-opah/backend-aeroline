# Dockerfile para Spring Boot
FROM eclipse-temurin:17-jdk-jammy

# Diretório de trabalho dentro do container
WORKDIR /app

# Copia o pom.xml e os arquivos de código
COPY pom.xml mvnw ./
COPY .mvn .mvn
COPY src src

# Build do projeto
RUN ./mvnw clean package -DskipTests

# Copia o JAR gerado
COPY target/*.jar app.jar

# Porta que o Spring Boot vai expor
EXPOSE 3333

# Comando para rodar a aplicação
ENTRYPOINT ["java", "-jar", "app.jar"]