
<%@page import="java.util.ArrayList"%>
<%@page import="entities.Project"%>
<%@page import="entities.User"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Dash board</title>
        <style>
            a{
                color: inherit; 
                text-decoration: inherit;
            }
        </style>
    </head>
    <%
        User user = (User) session.getAttribute("user");
    %>
    <body>
        <h1>Hello, <%=user.getName()%></h1>
        <%
            ArrayList<Project> projects =(ArrayList<Project>) request.getAttribute("projects");
        %>
        <h2>Your projects:</h2>
        <%
            for(Project p : projects){
        %>
            <a href="project-details">
                <table>
                    <tr><td><%=p.getName()%></td></tr>
                    <tr>
                        <td>
                            <b>Description:</b>
                            <p><%=p.getDescription()%></p>
                        </td>
                    </tr>
                </table>        
            </a>
        <%}%>

    </body>
</html>
