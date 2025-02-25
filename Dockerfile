# Sử dụng OpenJDK 8 làm base image
FROM openjdk:8-jdk-alpine

# Cài đặt Maven trong container để build ứng dụng
RUN apk add --no-cache maven

# Đặt thư mục làm việc trong container
WORKDIR /app

# Sao chép toàn bộ source code vào container
COPY . /app

# Chạy Maven để build ứng dụng
RUN mvn clean package

# Chạy ứng dụng từ file .jar đã build
CMD ["java", "-jar", "/app/target/TaskManagementApp.jar"]
