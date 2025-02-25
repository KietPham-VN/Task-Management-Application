package services.implementations;

import dao.implementations.ProjectDAO;
import dao.interfaces.IProjectDAO;
import dto.ProjectDTO;
import entities.Project;
import entities.User;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import services.interfaces.IProjectServices;

public class ProjectServices implements IProjectServices {
    private final ProjectDAO projectDao = new ProjectDAO();

    @Override
    public boolean createProject(String name, String description, int userId) {
        ProjectDAO projectDAO = new ProjectDAO();
        if (name == null || name.isEmpty() || description == null || description.isEmpty()) {
            return false; // Kiểm tra dữ liệu đầu vào
        }
        ProjectDTO project = new ProjectDTO(name, description, userId);
        return projectDAO.add(project);
    }

    @Override
    public ArrayList<Project> getProjectsByUser(int userId) {
        IProjectDAO projectDAO = new ProjectDAO();
        return projectDAO.getProjectsByUser(userId);
    }
    
    @Override
    public Project getProjectById(int projectId){
        IProjectDAO projectDAO = new ProjectDAO();
        return projectDAO.getProjectById(projectId);
    }

    @Override
    public ArrayList<User> getUserInProject(int projectId) {
        IProjectDAO projectDAO = new ProjectDAO();
        return projectDAO.getUserInProject(projectId);
    }

    @Override
    public ArrayList<User> getUserNotInProject(int projectId) {
        IProjectDAO projectDAO = new ProjectDAO();
        return projectDAO.getUserNotInProject(projectId);    
    }
}
