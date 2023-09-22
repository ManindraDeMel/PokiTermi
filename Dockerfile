# Use the official Maven image with Java 17
FROM maven:3.8.4-openjdk-17 AS build
# Set working directory inside the container
WORKDIR /app
# Copy pom.xml and source code to the container
COPY pom.xml .
COPY PokiTermi/src ./src
# Build the project inside the container
RUN mvn clean package

# Use OpenJDK to run
FROM openjdk:17
WORKDIR /app
COPY --from=build /app/target/comp2120-assignment-1.0-SNAPSHOT.jar /app
CMD ["java", "-jar", "comp2120-assignment-1.0-SNAPSHOT.jar"]
