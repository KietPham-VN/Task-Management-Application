package controllers.auth;

import common.constants.Pages;
import common.enums.AccountRoles;
import entities.User;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import services.implementations.UserServices;
import services.interfaces.IUserServices;

@WebServlet(name = "LoginController", urlPatterns
        = {
            "/LoginController"
        })
public class LoginController extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        IUserServices userServices = new UserServices();
        User user = userServices.login(username, password);
                    System.out.println(user.getRole());
        if (user != null) {
            HttpSession session = request.getSession();
            session.setAttribute("userId", user.getId());
            session.setMaxInactiveInterval(1800);

            if(user.getRole().equals(AccountRoles.PROJECT_MANAGER.getRoleName()))
            {
                response.sendRedirect("MainController?action=viewManagerProjects");
            }
            if (user.getRole().equals(AccountRoles.TEAM_MEMBER.getRoleName()))
            {
                response.sendRedirect("MainController?action=viewMemberProjects");
            }
        } else {
            request.setAttribute("error", "Wrong email or password");
            request.getRequestDispatcher(Pages.LOGIN).forward(request, response);
        }

    }

    @Override
    public String getServletInfo() {
        return "LoginController";
    }
}
