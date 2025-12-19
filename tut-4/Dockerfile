FROM maven:3.9.6-eclipse-temurin-17 AS build

WORKDIR /bezkoder_app

COPY . .

# Build without running tests
RUN mvn clean install -DskipTests

# Default command when container starts
CMD ["mvn", "spring-boot:run"]
