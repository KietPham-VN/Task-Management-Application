package controllers;

import common.constants.Pages;
import common.utils.Functions;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet(name = "MainController", urlPatterns =
{
    "/home"
})
public class MainController extends HttpServlet
{

    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        response.setContentType("text/html;charset=UTF-8");
        String action = (request.getParameter("action")!=null?request.getParameter("action"):"");
        String url = Pages.LOGIN;
        
        if(Functions.AuthenticatePath(request, "Project Manager")){
            request.getRequestDispatcher(Pages.PROJECT_MANAGER_DASH_BOARD).forward(request,response);
            return;
        }
        
        if(Functions.AuthenticatePath(request, "Team Member")){
            //Routed to team member
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
            case "register":
            {
                url="RegisterController";
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
