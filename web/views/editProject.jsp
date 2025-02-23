<%-- 
    Document   : editProject
    Created on : Feb 23, 2025, 11:22:59 AM
    Author     : Hoang Tran
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
    <title>Edit Project</title>
</head>
<body class="container">
    <h1>Edit Project</h1>
    
    <% if (request.getAttribute("error") != null) { %>
        <div class="alert alert-danger"><%= request.getAttribute("error") %></div>
    <% } %>

    <form action="EditProject" method="POST">
        <input type="hidden" name="id" value="<%= request.getAttribute("project") != null ? ((entities.Project) request.getAttribute("project")).getProjectId() : "" %>">
        <label class="form-label">Project Name</label>
        <input class="form-control" type="text" name="name" value="<%= request.getAttribute("project") != null ? ((entities.Project) request.getAttribute("project")).getName() : "" %>" required>

        <label class="form-label">Description</label>
        <textarea class="form-control" name="desc" required><%= request.getAttribute("project") != null ? ((entities.Project) request.getAttribute("project")).getDescription() : "" %></textarea>
        
        <br>
        <input class="btn btn-primary" type="submit" value="Update Project">
    </form>
</body>
</html>