package services.interfaces;

import entities.Project;
import entities.User;
import java.util.ArrayList;

public interface IProjectServices
{

    boolean createProject(String name, String description, int userId);
    ArrayList<Project> getProjectsByUser(int userId);
    Project getProjectById(int projectId);
    ArrayList<User> getUserInProject(int projectId);
    ArrayList<User> getUserNotInProject(int projectId);
    ArrayList<Project> getProjectsUserIsIn(int userId);
}
