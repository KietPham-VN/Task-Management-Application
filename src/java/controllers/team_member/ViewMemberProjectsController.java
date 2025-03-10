package controllers.team_member;

import common.constants.Pages;
import entities.AuthenticatedUser;
import entities.Project;
import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import services.implementations.ProjectServices;
import services.interfaces.IProjectServices;

@WebServlet(name = "ViewMemberProjectsController", urlPatterns =
{
    "/team-member"
})
public class ViewMemberProjectsController extends HttpServlet
{

    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        response.setContentType("text/html;charset=UTF-8");

    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        HttpSession session = request.getSession(false);
        if (session != null && session.getAttribute("authenticated-user") != null)
        {
            AuthenticatedUser authedUser = (AuthenticatedUser) session.getAttribute("authenticated-user");
            IProjectServices projectServices = new ProjectServices();
            ArrayList<Project> projects = projectServices.getProjectsUserIsIn(authedUser.getId());
            request.setAttribute("project-list", projects);
            request.getRequestDispatcher(Pages.VIEWS_MEMBER_PROJECTS).forward(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        processRequest(request, response);
    }

    @Override
    public String getServletInfo()
    {
        return "View Member's Projects Controller";
    }
}
