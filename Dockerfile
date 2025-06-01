# Use official OpenJDK image
FROM openjdk:17-jdk-slim

# Set working directory
WORKDIR /app

# Copy jar file (build dulu jar kamu, biasanya di target/)
COPY target/*.jar app.jar

# Expose port yang digunakan Spring Boot
EXPOSE 8005

# Jalankan aplikasi
ENTRYPOINT ["java","-jar","/app/app.jar"]
