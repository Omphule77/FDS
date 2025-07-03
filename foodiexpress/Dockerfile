# Use a lightweight Java 17 image
FROM eclipse-temurin:17-jdk-alpine

# Create app directory
WORKDIR /app

# Copy your JAR file into the image
COPY target/*.jar app.jar

# Run the JAR file
ENTRYPOINT ["java", "-jar", "/app/app.jar"]
