# Use an official OpenJDK runtime as a parent image
FROM openjdk:17-jdk-alpine

# Set the working directory in the container
WORKDIR /app

# Copy the packaged jar file into the container at /app
COPY target/Stage-0.0.1-SNAPSHOT.jar app.jar

# Expose the application's port (ensure this matches your Spring Boot configuration)
EXPOSE 8085

# Run the jar file
ENTRYPOINT ["java", "-jar", "app.jar"]
