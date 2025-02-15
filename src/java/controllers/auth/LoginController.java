package controllers.auth;

import common.constants.Pages;
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

@WebServlet(name = "LoginController", urlPatterns =
{
    "/LoginController"
})
public class LoginController extends HttpServlet
{

    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        response.setContentType("text/html;charset=UTF-8");
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        IUserServices userServices = new UserServices();
        User user = userServices.login(username, password);
        if (user != null)
        {
            HttpSession session = request.getSession();
            session.setAttribute("user", user);
            session.setMaxInactiveInterval(1800);
//            request.getRequestDispatcher("MainController?action=list").forward(request, response);
        } else
        {
            request.setAttribute("error", "Wrong email or password");
            request.getRequestDispatcher(Pages.LOGIN).forward(request, response);
        }
        
    }

    @Override
    public String getServletInfo()
    {
        return "LoginController";
    }
}
