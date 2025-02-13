<%-- 
    Document   : register
    Created on : Feb 9, 2025, 10:48:43 PM
    Author     : NGHIA
--%>

<%@page import="java.util.HashMap"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>

    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Register</title>
    </head>

    <body>
        <%
            HashMap<String,String> inputError = (HashMap<String,String>) request.getAttribute("validation-error");
            String nameErr = inputError.get("name");
            String emailErr = inputError.get("email");
            String passwordErr = inputError.get("password");
            String roleErr = inputError.get("role");
        %>
        <form action="/register">
            <label for="name">Name:</label>
            <br>
            <input type="name" name="name" id="name" required>
            <p><%=(nameErr==null)?nameErr:""%></p>
            <label for="email">Email:</label>
            <br>
            <input type="email" name="email" id="email" required>
            <p><%=(emailErr==null)?emailErr:""%></p>
            <label for="password">Password:</label>
            <br>
            <input type="password" name="password" id="password" required>
            <p><%=(passwordErr==null)?passwordErr:""%></p>
            <label for="role">Role:</label>
            <br>
            <select name="role" id="role">
                <option value="Team Member">Team Member</option>
                <option value="Project Manager">Project Manager</option>
            </select>
            <p><%=(roleErr==null)?roleErr:""%></p>
            <input type="submit" value="Register">
        </form>
    </body>

