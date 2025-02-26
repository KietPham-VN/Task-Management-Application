package controllers.team_member;

import common.constants.Pages;
import dao.implementations.ProjectDAO;
import entities.AuthenticatedUser;
import entities.Project;
import entities.Tasks;
import entities.User;
import java.io.IOException;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import services.implementations.ProjectServices;
import services.implementations.TaskServices;
import services.interfaces.IProjectServices;
import services.interfaces.ITaskServices;

@WebServlet(name = "ViewTeamMemberTaskController", urlPatterns =
{
    "/team-member/project-detail"
})
public class ViewTeamMemberTaskController extends HttpServlet
{

    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        response.setContentType("text/html;charset=UTF-8");
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {

        try
        {
            int projectId = Integer.parseInt(request.getParameter("id"));
            
            ITaskServices taskService = new TaskServices();
            IProjectServices projectServices = new ProjectServices();
            Project project = projectServices.getProjectById(projectId);
            ArrayList<Tasks> tasks = taskService.getTasksByProjectId(projectId, "", "");
            request.setAttribute("project", project);
            request.setAttribute("task-list", tasks);
            request.getRequestDispatcher(Pages.TEAM_MEMBER_TASK_DETAILS).forward(request, response);
        } catch (NumberFormatException e)
        {
            
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
        return "ViewTeamMemberTaskController";
    }

}
