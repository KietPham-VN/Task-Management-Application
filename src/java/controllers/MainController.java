package controllers;

import common.constants.Pages;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet(name = "MainController", urlPatterns =
{
    "/MainController"
})
public class MainController extends HttpServlet
{

    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        response.setContentType("text/html;charset=UTF-8");
        String action = request.getParameter("action");
        String url = Pages.LOGIN;
        HttpSession session = request.getSession(false);
        if ((session == null || session.getAttribute("userId") == null) && !"login".equals(action))
        {
            request.getRequestDispatcher(Pages.LOGIN).forward(request, response);
            return;
        }
        switch (action)
        {
            case "login":
            {
                url = "LoginController";
                break;
            }
            case "logout":
            {
                url = "LogoutController";
                break;
            }
            case "viewMemberProjects":
            {
                url = "ViewMemberProjectsController";
                break;
            }
            case "register":
            {
                url = "RegisterController";
                break;
            }
            default:
            {
                break;
            }
        }
        request.getRequestDispatcher(url).forward(request, response);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        processRequest(request, response);
    }

    @Override
    public String getServletInfo()
    {
        return "MainController";
    }
}
