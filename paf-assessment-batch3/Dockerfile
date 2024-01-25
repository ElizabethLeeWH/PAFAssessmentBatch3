FROM maven:3-eclipse-temurin-21 AS builder

## Build the application
# Create a directory call /app
# go into the directory cd /app
WORKDIR /src

# everything after this is in /app
COPY mvnw .
COPY mvnw.cmd .
COPY pom.xml .
COPY .mvn .mvn
COPY src src

# Build the application
RUN mvn package -Dmaven.test.skip=true

FROM maven:3-eclipse-temurin-21

WORKDIR /app 

COPY --from=builder /src/target/paf-assessment-batch3-0.0.1-SNAPSHOT.jar app.jar

## Run the application
# Define environment variable 
ENV PORT=8080

# Expose the port
EXPOSE ${PORT}

# Run the program
ENTRYPOINT SERVER_PORT=${PORT} java -jar ./app.jar