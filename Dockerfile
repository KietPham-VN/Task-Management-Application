# Sử dụng OpenJDK 8 làm base image
FROM openjdk:8-jdk-alpine

# Đặt thư mục làm việc trong container
WORKDIR /app

# Sao chép file .jar đã build từ NetBeans vào container
COPY dist/TaskManagementApp.jar /app/app.jar

# Chạy ứng dụng
CMD ["java", "-jar", "/app/app.jar"]
