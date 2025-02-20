/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers.auth;

import common.constants.Pages;
import entities.User;
import exceptions.InvalidDataException;
import exceptions.ValidationException;
import java.io.IOException;
import java.util.HashMap;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import services.implementations.UserServices;
import services.interfaces.IUserServices;

/**
 *
 * @author NGHIA
 */
@WebServlet(name = "RegisterController", urlPatterns = {"/register"})
public class RegisterController extends HttpServlet {


    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
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
        processRequest(request, response);
        request.getRequestDispatcher(Pages.REGISTER).forward(request, response);
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
        String name = request.getParameter("name");
        String password = request.getParameter("password");
        String email = request.getParameter("email");
        String role = request.getParameter("role");
        
        // Save user input to persist in form
        HashMap<String, String> formData = new HashMap<>();
        formData.put("name", name);
        formData.put("email", email);
        formData.put("password", password);
        formData.put("role", role);
        
        IUserServices userService = new UserServices();

        
        try {
            User newRegisteredUser = userService.register(name, email, password, role);
            if (newRegisteredUser==null) {
                throw new InvalidDataException("Cannot save product to database!");
            } else {
                HttpSession session = request.getSession();
                session.setAttribute("user", newRegisteredUser);
                session.setMaxInactiveInterval(1800);
                response.sendRedirect(Pages.HOME);
            }
        }
        catch (ValidationException ex) {
            request.setAttribute("formData", formData);
            request.setAttribute("validation-error", ex.getErrors());
            request.getRequestDispatcher(Pages.REGISTER).forward(request, response);
        }
        catch(InvalidDataException ex){
            request.setAttribute("formData", formData);
            request.setAttribute("invalid-data-exception",ex);
            request.getRequestDispatcher(Pages.REGISTER).forward(request, response);
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
