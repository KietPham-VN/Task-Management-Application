package controllers;

import common.constants.Pages;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "MainController", urlPatterns =
{
    "/MainController"
})
public class MainController extends HttpServlet
{

    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        response.setContentType("text/html");
        String action = (request.getParameter("action")!=null?request.getParameter("action"):"");
        String url = "";
        
        
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
            case "register":
            {
                url="RegisterController";
                break;
            }
            case "viewTeamMemberTask":
            {
                url = "team-member";
                break;
            }
            case "viewTeamMemberProjects":
            {
                url = "team-member";
                response.sendRedirect(url);
                return;
            }
            case "viewManagerProjects":
            {
                url = "project-manager";
                response.sendRedirect(url);
                return;
            }
            default:
            {
                url = Pages.LOGIN;
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
