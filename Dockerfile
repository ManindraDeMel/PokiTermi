# Use the official Maven image with Java 17
FROM maven:3.8.4-openjdk-17

# Set working directory inside the container
WORKDIR /app

# Copy pom.xml and source code to the container
COPY pom.xml .
COPY PokiTermi/src ./src

# Build the project inside the container
RUN mvn clean package

# Run the application using java -jar
CMD ["java", "-jar", "target/comp2120-assignment-1.0-SNAPSHOT.jar"]
