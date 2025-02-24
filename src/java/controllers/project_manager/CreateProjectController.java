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
import services.implementations.ProjectServices;

/**
 *
 * @author anhki
 */
@WebServlet(name = "CreateProjectController", urlPatterns = {"/CreateProject"})
public class CreateProjectController extends HttpServlet {

    private final ProjectServices projectServices = new ProjectServices();

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher(Pages.CREATE_PROJECT).forward(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            HttpSession session = request.getSession(false); // Do not create a new session
            if (session == null) {
                request.setAttribute("error", "User not logged in.");
                request.getRequestDispatcher(Pages.LOGIN).forward(request, response);
                return;
            }
            
//            if (session.getAttribute("role") == null || !session.getAttribute("role").equals("Project Manager"))  {
//                response.sendRedirect(Pages.LOGIN);
//                return;
//            }

            // Get the user from session
            int userId = (int) session.getAttribute("userId");

            // Get project details from request
            String projectName = request.getParameter("name");
            String description = request.getParameter("desc");

            // Validate inputs
            if (projectName == null || projectName.trim().isEmpty() || description == null || description.trim().isEmpty()) {
                request.setAttribute("error", "Project name and description are required.");
                request.getRequestDispatcher(Pages.CREATE_PROJECT).forward(request, response);
                return;
            }

            // Call the service to create the project
            ProjectServices projectService = new ProjectServices();
            boolean success = projectService.createProject(projectName, description, userId);

            if (success) {
                response.sendRedirect("MainController?action=viewManagerProjects");
            } else {
                request.setAttribute("error", "Failed to create project.");
                request.getRequestDispatcher(Pages.CREATE_PROJECT).forward(request, response);
            }
        } catch (Exception e) {
            request.setAttribute("error", "An error occurred: " + e.getMessage());
            request.getRequestDispatcher(Pages.CREATE_PROJECT).forward(request, response);
        }
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
