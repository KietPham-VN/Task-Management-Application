package services.implementations;

import dao.implementations.ProjectDAO;
import dao.interfaces.IProjectDAO;
import dto.ProjectDTO;
import entities.Project;
import java.util.ArrayList;
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

//    public boolean updateProject(int projectId, String name, String description) {
//        try {
//            Project project = projectDao.getProjectById(projectId);
//            if (project == null) {
//                return false; // Project not found
//            }
//            
//            project.setName(name);
//            project.setDescription(description);
//            
//            return projectDao.update(project);
//        } catch (ClassNotFoundException ex) {
//            Logger.getLogger(ProjectServices.class.getName()).log(Level.SEVERE, null, ex);
//        }
//    }

    @Override
    public Project getProjectById(int projectId)
    {
        IProjectDAO projectDAO = new ProjectDAO();
        return projectDAO.getProjectById(projectId);
    }
    
}
