package services.interfaces;

import entities.Project;
import java.util.ArrayList;

public interface IProjectServices
{

    boolean createProject(String name, String description, int userId);

    ArrayList<Project> getProjectsByUser(int userId);
    
    Project getProjectById(int projectId);
}
