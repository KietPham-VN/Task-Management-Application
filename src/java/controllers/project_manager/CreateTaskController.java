/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers.project_manager;

import common.constants.Pages;
import dao.implementations.TaskDAO;
import dto.TaskDTO;
import java.io.IOException;
import java.sql.Date;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Hoang Tran
 */
@WebServlet(name = "CreateTaskController", urlPatterns = {"/project-manager/project-detail/createTask"})

public class CreateTaskController extends HttpServlet {

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
        request.getRequestDispatcher(Pages.CREATE_TASK).forward(request, response);
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
        TaskDAO taskDAO = new TaskDAO();
        try {
            HttpSession session = request.getSession(false);
            if (session ==  null) {
                response.sendRedirect(Pages.LOGIN);
                return;
            }

            int projectId =Integer.parseInt(request.getParameter("projectId"));
            int assignedTo = (int) session.getAttribute("userId");
            String name = request.getParameter("name");
            String description = request.getParameter("desc");
            String status = request.getParameter("status");
            String priority = request.getParameter("priority");
            Date dueDate = (Date.valueOf(request.getParameter("dueDate")));
            
            boolean success = taskDAO.add(new TaskDTO(projectId, name, 
                    description, assignedTo, status, priority,dueDate));
            if (success) {
                response.sendRedirect(Pages.PROJECT_MANAGER_DASH_BOARD);
            } else {
                request.setAttribute("Error", "Failed");
                request.getRequestDispatcher(Pages.CREATE_TASK).forward(request, response);
            }
            
        } catch (Exception e) {
            System.out.println("Cannot add task" + e);
            request.getRequestDispatcher(Pages.CREATE_TASK).forward(request, response);
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
