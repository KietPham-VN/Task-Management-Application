<%-- 
    Document   : register
    Created on : Feb 9, 2025, 10:48:43 PM
    Author     : NGHIA
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Register</title>
    </head>
    <body>
        <form action="/register">
            <label for="name">Name:</label>
            <br>
            <input type="name" name="name" id="name" required>
            <label for="email">Email:</label>
            <br>
            <input type="email" name="email" id="email" required>
            <label for="password">Password:</label>
            <br>
            <input type="password" name="password" id="password" required>
            <label for="role">Role:</label>
            <br>
            <select name="role" id="role">
                <option value="Team Member">Team Member</option>
                <option value="Project Manager">Project Manager</option>
            </select>
        </form>
    </body>
</html>
