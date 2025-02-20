<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="vi">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Danh sách Dự án</title>

        <style>
            /* Reset margin/padding để tránh ảnh hưởng từ trình duyệt */
            * {
                margin: 0;
                padding: 0;
                box-sizing: border-box;
            }

            body {
                font-family: Arial, sans-serif;
                margin: 20px;
                background-color: #f8f9fa;
            }

            .container {
                width: 80%;
                margin: 0 auto;
                text-align: center;
            }

            h2 {
                margin-bottom: 20px;
                color: #333;
            }

            table {
                width: 100%;
                border-collapse: collapse;  /* Giúp các ô bảng dính sát nhau */
                margin: 0;
            }

            /* Loại bỏ padding/margin bên trong bảng */
            .table-container {
                border: 3px solid #b44593;
                border-radius: 8px;
                overflow: hidden;
                background: white;
                padding: 0; /* Loại bỏ padding */
                margin: 0; /* Loại bỏ margin */
                box-shadow: 0px 4px 8px rgba(0, 0, 0, 0.1);
            }

            /* Tiêu đề bảng với gradient */
            thead {
                background: linear-gradient(to right, #ee7724, #d8363a, #dd3675, #b44593);
                color: white;
                text-align: center;
            }

            thead th {
                padding: 10px; /* Đảm bảo nội dung không bị lệch */
                font-size: 16px;
                font-weight: bold;
                border-bottom: 2px solid white;
            }

            /* Dính sát nội dung vào viền */
            tbody td {
                padding: 8px; /* Điều chỉnh padding cho hợp lý */
                text-align: center;
                /*border: 1px solid #b44593;  Đảm bảo border giữa các ô */
            }

            /* Dính sát viền bảng */
            tbody tr {
                border-bottom: 1px solid #b44593;
            }
            /* Hiệu ứng hover cho hàng */
            tbody tr:hover {
                background-color: #f1f1f1;
            }

            .action-column {
                width: 120px;
                text-align: center;
            }

            /* Nút gradient */
            .btn-gradient {
                display: inline-block;
                background: linear-gradient(to right, #ee7724, #d8363a, #dd3675, #b44593);
                color: white;
                border: none;
                padding: 8px 12px;
                text-decoration: none;
                font-size: 14px;
                border-radius: 5px;
                transition: 0.3s;
                cursor: pointer;
            }

            .btn-gradient:hover {
                opacity: 0.9;
            }

            .no-projects {
                color: #6c757d;
                font-style: italic;
                margin-top: 20px;
            }

        </style>
    </head>
    <body>
        <div class="container">
            <h2>Danh sách Dự án</h2>

            <c:choose>
                <c:when test="${empty sessionScope['team-member-projects']}">
                    <p class="no-projects">Không có dự án nào.</p>
                </c:when>
                <c:otherwise>
                    <div class="table-container">
                        <table>
                            <thead>
                                <tr>
                                    <th style="width: 80%;">Tên Dự án</th>
                                    <th class="action-column">Hành Động</th>
                                </tr>
                            </thead>
                            <tbody>
                                <c:forEach var="project" items="${sessionScope['team-member-projects']}">
                                    <tr>
                                        <td>${project.getName()}</td>
                                        <td class="action-column">
                                            <a href="ProjectDetailsController?id=${project.projectId}" class="btn-gradient">
                                                Details
                                            </a>
                                        </td>
                                    </tr>
                                </c:forEach>
                            </tbody>
                        </table>
                    </div>
                </c:otherwise>
            </c:choose>
        </div>
    </body>
</html>
