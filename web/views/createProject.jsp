<%-- 
    Document   : createProject
    Created on : Feb 16, 2025, 8:47:35 PM
    Author     : Hoang Tran
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>
        <title>Create Project</title>
    </head>
    <body class="container">
        <h1>Create new project</h1>
        <form action="CreateProject" method="POST">
            <label class="form-label">Project name</label>
            <input class="form-control" type='text' name='name' required/>
            <label class="form-label">Description</label>
            <textarea class="form-control" type='text' name='desc' required/></textarea>
            <label class="form-label">Created By</label>
            <input class="form-control" type='text' name='createdBy'/><br>
            <input class="btn btn-success" type='submit' />
    </form>
</body>
</html>
