package controllers.team_member;

import common.enums.TaskStatus;
import entities.Tasks;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import services.implementations.TaskServices;
import services.interfaces.ITaskServices;

@WebServlet(name = "UpdateTaskStatusController", urlPatterns =
{
    "/team-member/update-task-status"
})

public class UpdateTaskStatusController extends HttpServlet
{
    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        response.setContentType("text/html;charset=UTF-8");
        try
        {

            int taskId = Integer.parseInt(request.getParameter("taskId"));
            String statusString = request.getParameter("status");

            TaskStatus status = TaskStatus.valueOf(statusString);

            Tasks task = new Tasks();
            task.setId(taskId);
            task.setStatus(status);

            ITaskServices taskServices = new TaskServices();
            boolean isUpdated = taskServices.updateTaskStatus(task);

            if (isUpdated)
            {
                int projectId = taskServices.getProjectIdByTaskId(taskId);
                response.sendRedirect(request.getContextPath() + "/team-member/project-detail?id=" + projectId);
            } else
            {
                request.setAttribute("errorMessage", "Failed to update task status.");
                request.getRequestDispatcher("/views/error.jsp").forward(request, response);
            }
        } catch (IOException | NumberFormatException | ServletException e)
        {
            Logger.getLogger(UpdateTaskStatusController.class.getName()).log(Level.SEVERE, "Invalid input", e);
            response.sendRedirect(request.getContextPath() + "/views/error.jsp");
        }
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
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        processRequest(request, response);
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
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo()
    {
        return "Short description";
    }// </editor-fold>

}
