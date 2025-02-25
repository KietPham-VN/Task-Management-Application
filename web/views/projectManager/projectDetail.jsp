<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@page contentType="text/html" pageEncoding="windows-1252"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=windows-1252">
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
        <title>Project detail</title>
        <link rel="stylesheet" href="css/style.css">
    </head>
    <body>
        <form method="POST" class="position-absolute top-0 start-0" action="${pageContext.request.contextPath}/LogoutController">
            <input  type="submit" data-mdb-button-init data-mdb-ripple-init class="btn btn-outline-danger" value="Logout">
        </form>
        
        <div class="container">
            <c:choose>
                <c:when test="${empty project}">
                    <h1>404 Cannot find the project</h1>
                </c:when>
                <c:otherwise>
                    <div>
                        <form action="">
                            <label class="form-label" for="searchName">Search</label>
                            <input type="hidden" name="id" id="id" value="${param.id}">
                            <input class="form-control" name="searchName" id="searchName" value="${param.searchName != null ? param.searchName : ''}">

                            <label>Sort by:</label>
                            <select name="sortBy" id="sortBy" class="my-2">
                                <option value="duedate" ${param.sortBy == 'duedate' ? 'selected' : ''}>Duedate</option>
                                <option value="status" ${param.sortBy == 'status' ? 'selected' : ''}>Status</option>
                                <option value="priority" ${param.sortBy == 'priority' ? 'selected' : ''}>Priority</option>
                            </select>
                            <input type="submit" class="btn btn-primary d-block" value="Search">
                        </form>
                        <div class="card my-2">
                            <div class="card-header">
                                ${project.getName()}
                            </div>
                            <div class="card-body">
                                <p class="card-text">${project.getDescription()}</p>
                                <p>Team members: </p>
                                <div class="overflow-auto my-2" style="max-height: 100px">
                                    <c:forEach var="user" items="${requestScope['member-list']}">
                                        <p>${user.getName()}</p>
                                    </c:forEach>
                                </div>
                                <form class="my-2" method="POST" action="${pageContext.request.contextPath}/project-manager/project-detail/add-user">
                                    <input type="hidden" value="${param.id}" name="projectId" id="projectId">
                                    <select name="userId" id="userId">
                                        <c:forEach var="user" items="${requestScope['available-users']}">
                                            <option value="${user.getId()}">${user.getName()}</option>
                                        </c:forEach>
                                    </select>
                                    <input type="submit" value="Add new member" class="btn btn-primary">
                                </form>
                                <a href="${pageContext.request.contextPath}/project-manager/project-detail/add-task?projectId=${param.id}">
                                    <button class="btn btn-primary">Add task</button>
                                </a>
                            </div>
                        </div>
                        <div>
                            <c:forEach var="task" items="${requestScope['task-list']}">
                                <div class="card mt-2">
                                    <div class="card-header d-flex justify-content-between">
                                        <b>${task.getName()}</b>
                                        <div>
                                            <a href="${pageContext.request.contextPath}/project-manager/project-detail/edit-task?projectId=${param.id}">
                                                <button class="btn btn-primary">Edit</button>
                                            </a>
                                            <form class="d-inline" method="POST" action="${pageContext.request.contextPath}/project-manager/project-detail/delete-task">
                                                <input type="hidden" value="${task.getId()}" name="taskId" id="taskId">
                                                <input type="submit" value="Delete" class="btn btn-danger">
                                            </form>
                                        </div>
                                    </div>
                                    <div class="card-body">
                                        <p class="card-text" >${task.getDescription()}</p>
                                    </div>
                                    <div class="card-footer d-flex justify-content-between">
                                        <div>
                                            <span>Status: ${task.getStatus()} | </span>
                                            <span>Priority: ${task.getPriority()}</span>
                                            <p>Duedate: ${task.getDueDate()}</p>
                                        </div>
                                        <p>Assigned to: ${task.getAssignedToUser()==null?"none":task.getAssignedToUser().getName()}</p>
                                    </div>
                                </div>
                            </c:forEach>
                        </div>
                    </div>
                </c:otherwise>
            </c:choose>
            
        </div>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-kenU1KFdBIe4zVF0s0G1M5b4hcpxyD9F7jL+jjXkk+Q2h455rYXK/7HAuoJl+0I4" crossorigin="anonymous"></script>
    </body>
</html>
