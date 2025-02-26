<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
        <title>Edit Task</title>
        <link href="${pageContext.request.contextPath}/css/create-update.css" rel="stylesheet" type="text/css"/>
    </head>

    <body class="container">
        <c:choose>
            <c:when test="${empty param.taskId or empty param.projectId or empty task or empty project}">
                <h1>404 Cannot find taskId or projectId</h1>
            </c:when>
            <c:otherwise>
                <div class="card my-4">
                   <h2 class="mb-4">Edit Task</h2>

                   <div>
                       <form action="${pageContext.request.contextPath}/project-manager/project-detail/editTask" method="post">
                           <input type="hidden" name="taskId" value="${task.getId()}">
                           <input type="hidden" name="projectId" value="${project.getProjectId()}" />

                           <div class="mb-3">
                               <label for="taskName" class="form-label">Task Name:</label>
                               <input type="text" class="form-control" id="taskName" name="taskName" value="${task.getName()}" required>
                           </div>

                           <div class="mb-3">
                               <label for="description" class="form-label">Description:</label>
                               <textarea class="form-control" id="description" name="description" required>${task.getDescription()}</textarea>
                           </div>

                           <div class="mb-3">
                               <label for="assignedTo" class="form-label">Assigned To:</label>
                                <select name="assignedTo" id="assignedTo" required>
                                    <c:forEach var="user" items="${requestScope['teamMembers']}">
                                        <option value="${user.getTeamMemberId()}" ${user.getTeamMemberId()==task.getAssignedTo()?"selected":""}>${user.getName()}</option>
                                    </c:forEach>
                                </select>                               
                           </div>

                           <div class="mb-3">
                               <label for="status" class="form-label">Status:</label>
                               <select class="form-select" id="status" name="status">
                                    <option value="PENDING" ${task.getStatus().equals("1") ? 'selected' : ''}>To Do</option>
                                    <option value="IN_PROGRESS" ${task.getStatus().equals("2") ? 'selected' : ''}>In Progress</option>
                                    <option value="COMPLETED" ${task.getStatus().equals("3") ? 'selected' : ''}>Done</option>
                               </select>
                           </div>

                           <div class="mb-3">
                               <label for="priority" class="form-label">Priority:</label>
                               <select class="form-select" id="priority" name="priority">
                                   <option value="LOW" ${task.getPriority().equals("1") ? 'selected' : ''}>Low</option>
                                   <option value="MEDIUM" ${task.getPriority().equals("2") ? 'selected' : ''}>Medium</option>
                                   <option value="HIGH" ${task.getPriority().equals("3") ? 'selected' : ''}>High</option>
                               </select>
                           </div>

                           <div class="mb-3">
                               <label for="dueDate" class="form-label">Due Date:</label>
                               <input type="date" class="form-control" id="dueDate" name="dueDate"required value="${task.getDueDate().toString()}">
                           </div>

                           <div class="d-flex justify-content-between">
                               <button type="submit" class="btn btn-primary">Update Task</button>
                               <a href="${pageContext.request.contextPath}/project-manager/project-detail?id=${param.projectId}" class="btn btn-secondary">Cancel</a>
                           </div>
                       </form>
                   </div>
               </div>
            </c:otherwise>
        </c:choose>
       

        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js"></script>
    </body>
</html>