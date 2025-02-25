# Sử dụng OpenJDK 8 để chạy ứng dụng
FROM openjdk:8-jdk-alpine

# Đặt thư mục làm thư mục làm việc trong container
WORKDIR /app

# Sao chép file JAR của ứng dụng vào container
COPY dist/TaskManagementApp.jar /app/app.jar

# Cấu hình biến môi trường (dùng khi chạy với docker-compose)
ENV DB_HOST=db
ENV DB_PORT=1433
ENV DB_NAME=task_management
ENV DB_USER=sa
ENV DB_PASSWORD=12345

# Mở cổng 8080 nếu ứng dụng có giao diện web
EXPOSE 8080

# Chạy ứng dụng
CMD ["java", "-jar", "/app/app.jar"]
