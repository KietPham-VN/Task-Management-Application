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
        <title>Create Project</title>
    </head>
    <body>
        <h1>Create new project</h1>
        <form action="CreateProject" method="POST">
            <label>Project name</label>
            <input type='text' name='name' required/>
            <label>Description</label>
            <input type='text' name='desc' required/>
            <label>Created By</label>
            <input type='text' name='createdBy'/>
            <input type='submit' />
        </form>
    </body>
</html>
