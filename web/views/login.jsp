<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <title>Login Page</title>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
        
        <!-- some css -->
        <style>
            body {
                background-color: #f8f9fa; /* Màu nền nhẹ */
            }
            .login-container {
                max-width: 400px;
                margin: 80px auto;
                padding: 20px;
                background: white;
                border-radius: 10px;
                box-shadow: 0px 4px 8px rgba(0, 0, 0, 0.1);
            }
            .form-control {
                border-radius: 5px;
            }
            .btn-login {
                background-color: #007bff;
                color: white;
            }
            .btn-login:hover {
                background-color: #0056b3;
            }
        </style>
    </head>
    <body>

        <div class="container">
            <div class="login-container">
                <h2 class="text-center mb-4">Đăng Nhập</h2>
                
                <!-- Display error -->
                <c:if test="${not empty error}">
                    <p>${error}</p>
                </c:if>

                <!-- Login Form -->
                <form action="MainController" method="POST">
                    <input type="hidden" name="action" value="login">

                    <div class="mb-3">
                        <label class="form-label">Username:</label>
                        <input type="text" name="username" class="form-control" required>
                    </div>

                    <div class="mb-3">
                        <label class="form-label">Password:</label>
                        <input type="password" name="password" class="form-control" required>
                    </div>

                    <div class="d-grid">
                        <button type="submit" class="btn btn-login">Đăng Nhập</button>
                    </div>
                </form>
            </div>
        </div>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
    </body>
</html>
