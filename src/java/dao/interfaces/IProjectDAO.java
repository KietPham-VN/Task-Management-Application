package dao.interfaces;

import dto.ProjectDTO;
import entities.Project;
import entities.User;
import java.util.ArrayList;

public interface IProjectDAO
{
    boolean add(ProjectDTO projectDto);
  //  boolean update(ProjectDTO projectDto);

    ArrayList<Project> getProjectsByUser(int UserId);
    Project getProjectById(int projectId);
    boolean addUserToProject(int projectId,int userId);
    ArrayList<User> getUserNotInProject(int projectId);
    ArrayList<User> getUserInProject(int projectId);
    ArrayList<Project> getProjectUserIsIn(int userId);
}
