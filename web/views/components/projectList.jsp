<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<div class="table-container">
    <table>
        <thead>
            <tr>
                <th style="width: 80%;">Tên Dự án</th>
                <th class="action-column">Hành Động</th>
            </tr>
        </thead>
        <tbody>
            <c:forEach var="project" items="${sessionScope['team-member-projects']}">
                <tr>
                    <td>${project.getName()}</td>
                    <td class="action-column">
                        <a href="ProjectDetailsController?id=${project.projectId}" class="btn-gradient">
                            Details
                        </a>
                    </td>
                </tr>
            </c:forEach>
        </tbody>
    </table>
</div>
