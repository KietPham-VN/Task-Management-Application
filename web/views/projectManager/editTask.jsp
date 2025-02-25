<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
        <title>Edit Task</title>
    </head>
    <body>
        <div class="container my-4">
            <h2 class="mb-4">Edit Task</h2>

            <div>
                <form action="${pageContext.request.contextPath}/project-manager/project-detail/editTask" method="post">
                    <input type="hidden" name="taskId" value="${param.taskId}">
                    <input type="hidden" name="projectId" value="${param.projectId}" />

                    <div class="mb-3">
                        <label for="taskName" class="form-label">Task Name:</label>
                        <input type="text" class="form-control" id="taskName" name="taskName" value="${task.name}" required>
                    </div>

                    <div class="mb-3">
                        <label for="description" class="form-label">Description:</label>
                        <textarea class="form-control" id="description" name="description" required>${task.description}</textarea>
                    </div>

                    <div class="mb-3">
                        <label for="assignedTo" class="form-label">Assigned To:</label>
                        <input class="form-control" name="assignedTo" value="${task.assignedTo}"required="">
                    </div>

                    <div class="mb-3">
                        <label for="status" class="form-label">Status:</label>
                        <select class="form-select" id="status" name="status">
                            <option value="PENDING">To Do</option>
                            <option value="IN_PROGRESS">In Progress</option>
                            <option value="COMPLETED"}>Done</option>
                        </select>
                    </div>

                    <div class="mb-3">
                        <label for="priority" class="form-label">Priority:</label>
                        <select class="form-select" id="priority" name="priority">
                            <option value="LOW">Low</option>
                            <option value="MEDIUM">Medium</option>
                            <option value="HIGH">High</option>
                        </select>
                    </div>

                    <div class="mb-3">
                        <label for="dueDate" class="form-label">Due Date:</label>
                        <input type="date" class="form-control" id="dueDate" name="dueDate"required>
                    </div>

                    <div class="d-flex justify-content-between">
                        <button type="submit" class="btn btn-primary">Update Task</button>
                        <a href="${pageContext.request.contextPath}/project-manager/project-detail?id=${task.projectId}" class="btn btn-secondary">Cancel</a>
                    </div>
                </form>
            </div>
        </div>

        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js"></script>
    </body>
</html>