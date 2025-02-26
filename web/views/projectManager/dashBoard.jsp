
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
        <form method="POST" class="position-absolute top-0 end-0 m-3" action="LogoutController">
            <div class="d-flex align-items-center justify-content-center">
                <h5 class="mb-0 mx-2">Hello, <%=user.getName()%></h5>
                <input  type="submit" data-mdb-button-init data-mdb-ripple-init class="btn btn-outline-danger" value="Logout">
            </div>
        </form>

        <div class="container text-start mt-4">
            <div class="text-center">

                <h2>Project list</h2>
            </div>
            <a href="project-manager/create-project">
                <button class="btn btn-gradient m-2" id="createprjbutton">+ Create</button>
            </a>
            <c:choose>
                <c:when test="${empty requestScope['project-list']}">
                    <p class="no-projects">Project not found</p>
                </c:when>
                <c:otherwise>
                    <jsp:include page="../components/projectList.jsp">
                        <jsp:param name="baseUrl" value="project-manager/project-detail" />
                    </jsp:include>
                </c:otherwise>
            </c:choose>

        </div>
    </body>
</html>
