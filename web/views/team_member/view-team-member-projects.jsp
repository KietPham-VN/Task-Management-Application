<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="vi">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Danh sách Dự án</title>
        <link rel="stylesheet" href="css/style.css">
    </head>
    <body>
        <div class="container">
            <h2>Danh sách Dự án</h2>

            <c:choose>
                <c:when test="${empty requestScope['project-list']}">
                    <p class="no-projects">Không có dự án nào.</p>
                </c:when>
                <c:otherwise>
                    <jsp:include page="../components/projectList.jsp">
                        <jsp:param name="team-member" value="ProjectDetailsController" />
                    </jsp:include>                
                </c:otherwise>
            </c:choose>
        </div>
    </body>
</html>
