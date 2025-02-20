<%-- 
    Document   : register
    Created on : Feb 9, 2025, 10:48:43 PM
    Author     : NGHIA
--%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.util.HashMap"%>
<%@page import="java.util.List"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Register</title>
    </head>

    <body>
        <%
            HashMap<String, String> formData = (HashMap<String, String>) request.getAttribute("formData");

            HashMap<String, String> inputError = (HashMap<String, String>) request.getAttribute("validation-error");
            String nameErr = (inputError != null) ? inputError.get("name") : null;
            String emailErr = (inputError != null) ? inputError.get("email") : null;
            String passwordErr = (inputError != null) ? inputError.get("password") : null;
            String roleErr = (inputError != null) ? inputError.get("role") : null;

            String invalidDataErr = (String) request.getAttribute("invalid-data-exception");
        %>
        <form action="register" method="POST">
            <label for="name">Name:</label>
            <br>
            <input type="text" name="name" id="name" value="<%= (formData != null && formData.get("name") != null) ? formData.get("name") : ""%>" required>
            <p><%=(nameErr != null) ? nameErr : ""%></p>
            <label for="email">Email:</label>
            <br>
            <input type="email" name="email" id="email" value="<%= (formData != null && formData.get("email") != null) ? formData.get("email") : ""%>" required>
            <p><%=(emailErr != null) ? emailErr : ""%></p>
            <label for="password">Password:</label>
            <br>
            <input type="password" name="password" id="password" value="<%= (formData != null && formData.get("password") != null) ? formData.get("password") : ""%>" required>
            <p><%=(passwordErr != null) ? passwordErr : ""%></p>
            <label for="role">Role:</label>
            <br>
            <select name="role" id="role">
                <option value="Team Member" <%= (formData != null && "Team Member".equals(formData.get("role"))) ? "selected" : ""%>>Team Member</option>
                <option value="Project Manager" <%= (formData != null && "Project Manager".equals(formData.get("role"))) ? "selected" : ""%>>Project Manager</option>
            </select>
            <p><%=(roleErr != null) ? roleErr : ""%></p>
            <input type="submit" value="Register">
            <p><%=(invalidDataErr != null) ? invalidDataErr : ""%></p>
        </form>
    </body>
</html>