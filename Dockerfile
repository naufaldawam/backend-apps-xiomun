# Gunakan image Maven + JDK untuk build
FROM maven:3.9.1-eclipse-temurin-17 AS build

WORKDIR /app

# Copy semua source code ke container
COPY . .

# Build aplikasi (pakai clean package)
RUN mvn clean package -DskipTests

# -------------------------------------------------------

# Stage kedua: Jalankan hasil build dengan JDK yang ringan
FROM openjdk:17-jdk-slim

WORKDIR /app

# Copy jar hasil build dari stage build
COPY --from=build /app/target/*.jar app.jar

# Expose port aplikasi Spring Boot
EXPOSE 8005

# Jalankan aplikasi
ENTRYPOINT ["java","-jar","app.jar"]
