/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers.project_manager;

import common.constants.Pages;
import common.utils.Functions;
import dao.implementations.ProjectDAO;
import entities.Project;
import entities.User;
import java.io.IOException;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author NGHIA
 */
@WebServlet(name = "ProjectManagerDashBoard", urlPatterns = {"/ProjectManagerDashBoard"})
public class ProjectManagerDashBoard extends HttpServlet {

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
        if(!Functions.AuthenticatePath(request, "Project Manager")) response.sendRedirect("LoginController");
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        
        try{
            ProjectDAO projectDAO = new ProjectDAO();
            ArrayList<Project> projectList = projectDAO.getListOfProject(user.getId());
            
            request.setAttribute("projects", projectList);
            request.getRequestDispatcher(Pages.PROJECT_MANAGER_DASH_BOARD).forward(request,response);
        }
        catch(Exception e){
            System.out.println(e);
            request.getRequestDispatcher(Pages.LOGIN);
        }
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
