<%-- 
    Document   : createTasks
    Created on : Feb 17, 2025, 9:05:45 PM
    Author     : Hoang Tran
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>
        <title>Create Task</title>
    </head>
    <c:choose>
        <c:when test="${empty param.projectId or empty project}">
            <h1>404 Cannot find the project</h1>
        </c:when>
        <c:otherwise>
            <body class="container">
                <h1>Create new task</h1>
                <form action="${pageContext.request.contextPath}/project-manager/project-detail/createTask" method="POST">
                    <input type="hidden" name="projectId" value="${param.projectId}" />
                    <label class="form-label">Task name</label>
                    <input class="form-control" type='text' name='name' required/>

                    <label class="form-label">Task description</label>
                    <textarea class="form-control" name='desc' required></textarea>

                    <label class="form-label" for="member">Assign to</label>
                    <select name="member" id="member" required>
                        <c:forEach var="user" items="${requestScope['teamMembers']}">
                            <option value="${user.getTeamMemberId()}">${user.getName()}</option>
                        </c:forEach>
                    </select>

                    <label class="form-label">Status</label>
                    <select class="form-select" name='status' required>
                        <option value="PENDING">Pending</option>
                        <option value="IN_PROGRESS">In progress</option>
                        <option value="COMPLETED">Done</option>
                    </select>

                    <label class="form-label">Priority</label>
                    <select class="form-select" name='priority' required>
                        <option value="LOW">Low</option>
                        <option value="MEDIUM">Medium</option>
                        <option value="HIGH">High</option>
                    </select><br>

                    <label class="form-label">Due Date</label>
                    <input class="form-control" type="date" name="dueDate" required/><br>

                    <div class="d-flex justify-content-between">
                        <button type="submit" class="btn btn-primary">Update Task</button>
                        <a href="${pageContext.request.contextPath}/project-manager/project-detail?id=${param.projectId}" class="btn btn-secondary">Cancel</a>
                    </div>
                </form>
            </body>
        </c:otherwise>
    </c:choose>
    
</html>
