# ================================
# Stage 1 — Build
# ================================
FROM maven:3.9.9-eclipse-temurin-21 AS builder

WORKDIR /build

# Copy only pom.xml first (better Docker layer caching)
COPY pom.xml .

# Download dependencies (cached unless pom changes)
RUN mvn -B -e -DskipTests dependency:go-offline

# Copy source code
COPY src ./src

# Build the application
RUN mvn -B -q -DskipTests package


# ================================
# Stage 2 — Runtime
# ================================
FROM eclipse-temurin:21-jdk-jammy

WORKDIR /app

# Copy built jar from builder stage
COPY --from=builder /build/target/*.jar app.jar

# Expose application port
EXPOSE 8080

# JVM container optimizations
ENV JAVA_OPTS="-XX:+UseContainerSupport -XX:MaxRAMPercentage=75"

# Run application
ENTRYPOINT ["sh", "-c", "java $JAVA_OPTS -jar app.jar"]