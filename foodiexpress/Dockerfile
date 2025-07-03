# 🔧 Stage 1: Build the Spring Boot app
FROM maven:3.9.4-eclipse-temurin-17 AS builder

WORKDIR /app

# Copy only the FDS project folder contents
COPY FDS/ .

# Build the project and skip tests
RUN mvn clean package -DskipTests

# 🚀 Stage 2: Run the packaged JAR
FROM eclipse-temurin:17-jdk-alpine

WORKDIR /app

# Copy the built JAR from the builder stage
COPY --from=builder /app/target/*.jar app.jar

# Start the Spring Boot application
ENTRYPOINT ["java", "-jar", "app.jar"]
