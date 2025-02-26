<%-- 
    Document   : createProject
    Created on : Feb 16, 2025, 8:47:35 PM
    Author     : Hoang Tran
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>
        <title>Create Project</title>
        <link href="${pageContext.request.contextPath}/css/create-update.css" rel="stylesheet" type="text/css"/>
    </head>
    <body class="container mt-4">
        <div class="card">
            <h1>Create new project</h1>
            <form action="" method="POST">
                <label class="form-label">Project name</label>
                <input class="form-control" type='text' name='name' required/>
                <label class="form-label">Description</label>
                <textarea class="form-control" type='text' name='desc' required/></textarea>
                <input class="btn btn-gradient mt-2" type='submit' />
            </form>
        </div>
    </body>
</html>
