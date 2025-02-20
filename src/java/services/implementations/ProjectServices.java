package services.implementations;

import dao.implementations.ProjectDAO;
import dao.interfaces.IProjectDAO;
import entities.Project;
import java.util.ArrayList;
import services.interfaces.IProjectServices;

public class ProjectServices implements IProjectServices
{

    @Override
    public ArrayList<Project> getProjectsByUser(int userId)
    {
        IProjectDAO projectDAO = new ProjectDAO();
        return projectDAO.getProjectsByUser(userId);
    }
}
