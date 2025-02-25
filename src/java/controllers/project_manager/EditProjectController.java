package controllers.project_manager;

import common.constants.Pages;
import common.enums.AccountRoles;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import entities.Project;
import services.implementations.ProjectServices;

/**
 * Servlet to handle editing of projects by project managers.
 */
@WebServlet(name = "EditProjectController", urlPatterns = {"/EditProject"})
public class EditProjectController extends HttpServlet {

    private final ProjectServices projectServices = new ProjectServices();

    /**
     * Handles GET request to show the edit project form.
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        
        if (session == null || session.getAttribute("userId") == null) {
            response.sendRedirect(Pages.LOGIN);
            return;
        }

        if (session.getAttribute("role") == null || !session.getAttribute("role").toString().equals(AccountRoles.PROJECT_MANAGER.toString())) {
            response.sendRedirect(Pages.LOGIN); // Redirect to an unauthorized page
            return;
        }

        try {
            int projectId = Integer.parseInt(request.getParameter("id"));
            //Project project = projectServices.getProjectById(projectId);

          //  if (project == null) {
                request.setAttribute("error", "Project not found.");
          //      request.getRequestDispatcher(Pages.PROJECT_LIST).forward(request, response);
                return;
            }

            request.setAttribute("project", project);
            request.getRequestDispatcher(Pages.EDIT_PROJECT).forward(request, response);
        } catch (NumberFormatException e) {
            request.setAttribute("error", "Invalid project ID.");
 //           request.getRequestDispatcher(Pages.PROJECT_LIST).forward(request, response);
        }
    }

    /**
     * Handles POST request to update the project details.
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        
        if (session == null || session.getAttribute("userId") == null) {
            response.sendRedirect(Pages.LOGIN);
            return;
        }

        if (session.getAttribute("role") == null || !session.getAttribute("role").toString().equals(AccountRoles.PROJECT_MANAGER.toString())) {
            response.sendRedirect(Pages.LOGIN);
            return;
        }

        try {
            int projectId = Integer.parseInt(request.getParameter("id"));
            String projectName = request.getParameter("name");
            String description = request.getParameter("desc");

            if (projectName == null || projectName.trim().isEmpty() || description == null || description.trim().isEmpty()) {
                request.setAttribute("error", "Project name and description cannot be empty.");
                request.getRequestDispatcher(Pages.EDIT_PROJECT).forward(request, response);
                return;
            }

            boolean updated = projectServices.updateProject(projectId, projectName, description);

            if (updated) {
                response.sendRedirect(Pages.PROJECT_DETAILS + "?id=" + projectId);
            } else {
                request.setAttribute("error", "Failed to update project.");
                request.getRequestDispatcher(Pages.EDIT_PROJECT).forward(request, response);
            }
        } catch (NumberFormatException e) {
            request.setAttribute("error", "Invalid project ID.");
            request.getRequestDispatcher(Pages.EDIT_PROJECT).forward(request, response);
        }
    }

    @Override
    public String getServletInfo() {
        return "Edit Project Controller";
    }
}
