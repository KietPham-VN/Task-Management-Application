package services.implementations;

import dao.implementations.ProjectDAO;
import dao.interfaces.IProjectDAO;
import dto.ProjectDTO;
import entities.Project;
import java.util.ArrayList;
import services.interfaces.IProjectServices;

public class ProjectServices implements IProjectServices {

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
}
