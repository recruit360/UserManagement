# Use an official OpenJDK runtime as a parent image
FROM openjdk:17-jdk-alpine

# Set the working directory in the container
WORKDIR /app

# Copy the application's JAR file into the container
COPY target/user-management-0.0.1-SNAPSHOT.jar app.jar

# Expose the port that your Spring Boot app runs on (default is 8080)
EXPOSE 8080

# Run the application
ENTRYPOINT ["java", "-jar", "app.jar"]