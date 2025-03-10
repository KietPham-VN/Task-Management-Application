/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers.project_manager;

import common.constants.Pages;
import entities.AuthenticatedUser;
import entities.Project;
import entities.Tasks;
import entities.User;
import java.io.IOException;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import services.implementations.ProjectServices;
import services.implementations.TaskServices;

/**
 *
 * @author NGHIA
 */
@WebServlet(name = "ProjectManagerTaskDetail", urlPatterns = {"/project-manager/project-detail"})
public class ProjectManagerTaskDetail extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
    }

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
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        AuthenticatedUser user = (AuthenticatedUser) session.getAttribute("authenticated-user");

        
        String projectIdString =  request.getParameter("id");
        Integer projectId = null;
        String searchName =  (request.getParameter("searchName")!=null)?request.getParameter("searchName"):"";
        String sortBy =  (request.getParameter("sortBy")!=null)?request.getParameter("sortBy"):"";
        
        TaskServices taskService = new TaskServices();
        ProjectServices projectService = new ProjectServices();
        try{
            projectId = Integer.parseInt(projectIdString);
        }
        catch(NumberFormatException e){
            System.out.println(e);
        }
        
        if(projectId!=null) {
            Project project = projectService.getProjectById(projectId);
            if(project.getCreateBy()!=user.getId()) {
                response.sendRedirect(request.getContextPath());
                return;
            }
            ArrayList<Tasks> tasks = taskService.getTasksByProjectIdWithMembers(projectId, searchName, sortBy);
            ArrayList<User> availableUsers = projectService.getUserNotInProject(projectId);
            ArrayList<User> teamMembers = projectService.getUserInProject(projectId);
            request.setAttribute("task-list", tasks);
            request.setAttribute("project", project);
            request.setAttribute("member-list", teamMembers);
            request.setAttribute("available-users",availableUsers);
        }
        request.getRequestDispatcher(Pages.PROJECT_MANAGER_TASK_DETAILS).forward(request,response);
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
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
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
