
<%@page import="entities.AuthenticatedUser"%>
<%@page import="java.util.ArrayList"%>
<%@page import="entities.Project"%>
<%@page import="entities.User"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
        <title>Dashboard</title>
        <link rel="stylesheet" href="css/style.css">
    </head>
    <%
        AuthenticatedUser user = (AuthenticatedUser) session.getAttribute("authenticated-user");
    %>
    <body>
        <form method="POST" class="position-absolute top-0 start-0" action="LogoutController">
            <input  type="submit" data-mdb-button-init data-mdb-ripple-init class="btn btn-outline-danger" value="Logout">
        </form>
        
        <div  class="container">
            <h1>Hello, <%=user.getName()%></h1>
            <h2>Danh sách Dự án</h2>
             <c:choose>
                <c:when test="${empty requestScope['project-list']}">
                    <p class="no-projects">Không có dự án nào.</p>
                </c:when>
                <c:otherwise>
                    <jsp:include page="../components/projectList.jsp">
                        <jsp:param name="baseUrl" value="project-manager/project-detail" />
                    </jsp:include>
                </c:otherwise>
            </c:choose>
            <a href="CreateProjectController">Create new project</a>
        </div>

    </body>
</html>
