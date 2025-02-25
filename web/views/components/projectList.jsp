<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<div class="table-container">
    <table>
        <thead>
            <tr>
                <th style="width: 80%;">Project name</th>
                <th class="action-column">Action</th>
            </tr>
        </thead>
        <tbody>
            <c:forEach var="project" items="${requestScope['project-list']}">
                <tr>
                    <td>${project.getName()}</td>
                    <td class="action-column">
                        <a href="${param.baseUrl}?id=${project.projectId}" class="btn-gradient">
                            Details
                        </a>
                    </td>
                </tr>
            </c:forEach>
        </tbody>
    </table>
</div>
