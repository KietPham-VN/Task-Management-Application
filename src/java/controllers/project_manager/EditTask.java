/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers.project_manager;

import common.constants.Pages;
import dao.implementations.TaskDAO;
import dto.TaskDTO;
import entities.Project;
import entities.User;
import java.io.IOException;
import java.sql.Date;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import services.implementations.ProjectServices;
import services.implementations.TaskServices;

/**
 *
 * @author Hoang Tran
 */
@WebServlet(name = "EditTask", urlPatterns = {"/project-manager/project-detail/editTask"})
public class EditTask extends HttpServlet {

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
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String taskIdString = request.getParameter("taskId");
        String projectIdString = request.getParameter("projectId");
        try{
            Integer taskId = Integer.parseInt(taskIdString);
            Integer projectId = Integer.parseInt(projectIdString);
            
            TaskServices taskService = new TaskServices();
            ProjectServices projectService = new ProjectServices();
            
            ArrayList<User> teamMembers = projectService.getUserInProject(projectId);
            Project project = projectService.getProjectById(projectId);
            TaskDTO task = taskService.getTaskById(taskId);
            
            request.setAttribute("task", task);
            request.setAttribute("project",project);
            request.setAttribute("teamMembers",teamMembers);
        }
        catch(NumberFormatException ex){
            System.out.println(ex);
        }
        
        request.getRequestDispatcher(Pages.UPDATE_TASK).forward(request, response);
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
        int projectId = Integer.parseInt(request.getParameter("projectId"));
        int taskId = Integer.parseInt(request.getParameter("taskId"));
        String name = request.getParameter("taskName");
        String desc = request.getParameter("description");
        int assignedTo = Integer.parseInt(request.getParameter("assignedTo"));
        String status = request.getParameter("status");
        String priority = request.getParameter("priority");
        Date dueDate = Date.valueOf(request.getParameter("dueDate"));
        
        TaskDTO task = new TaskDTO(taskId, projectId, name, desc, assignedTo, status, priority, dueDate);
        
        TaskDAO taskDAO = new TaskDAO();
        boolean success = taskDAO.update(task);

        if (success) {
            response.sendRedirect(request.getContextPath()+"/project-manager/project-detail?id="+projectId); // Redirect to task list after update
        } else {
            request.setAttribute("errorMessage", "Failed to update task.");
            response.sendRedirect(request.getHeader("Referer"));
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
