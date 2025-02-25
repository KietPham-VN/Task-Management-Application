<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@page contentType="text/html" pageEncoding="windows-1252"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=windows-1252">
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
        <title>Project Task List</title>
        <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
    </head>
    <body>
        <form method="POST" class="position-absolute top-0 end-0 m-3" action="${pageContext.request.contextPath}/MainController">
            <input type="submit" name="action" class="btn btn-outline-danger" value="Logout">
        </form>

        <div class="container text-start mt-4">
            <c:choose>
                <c:when test="${empty project}">
                    <h1>404 - Project Not Found</h1>
                </c:when>
                <c:otherwise>
                    <div>
                        <div class="card my-2">
                            <div class="card-header fw-bold fs-3">
                                ${project.getName()}
                            </div>
                            <div class="card-body">
                                <p class="card-text">${project.getDescription()}</p>
                            </div>
                        </div>

                        <h4>Project Tasks</h4>
                        <div>
                            <c:forEach var="task" items="${requestScope['task-list']}">
                                <div class="card mt-2">
                                    <div class="card-header d-flex justify-content-between">
                                        <b>${task.getName()}</b>
                                    </div>
                                    <div class="card-body">
                                        <p class="card-text">${task.getDescription()}</p>
                                    </div>
                                    <div class="card-footer d-flex justify-content-between">
                                        <div>
                                            <span>Status: </span>
                                            <form method="POST" action="${pageContext.request.contextPath}/team-member/update-task-status" class="d-inline">
                                                <input type="hidden" name="taskId" value="${task.getId()}">
                                                <select name="status" class="form-select d-inline w-auto">
                                                    <option value="PENDING" class="status-pending" ${task.getStatus().name() == 'PENDING' ? 'selected' : ''}>Not Started</option>
                                                    <option value="IN_PROGRESS" class="status-in-progress" ${task.getStatus().name() == 'IN_PROGRESS' ? 'selected' : ''}>In Progress</option>
                                                    <option value="COMPLETED" class="status-completed" ${task.getStatus().name() == 'COMPLETED' ? 'selected' : ''}>Completed</option>
                                                </select>
                                                <input type="submit" value="Update" class="btn btn-gradient">
                                            </form>
                                        </div>
                                        <p>Priority: ${task.getPriority()}</p>
                                        <p>Due Date: ${task.getDueDate()}</p>
                                    </div>
                                </div>
                            </c:forEach>
                        </div>
                    </div>
                </c:otherwise>
            </c:choose>
        </div>

        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js"></script>
    </body>
</html>