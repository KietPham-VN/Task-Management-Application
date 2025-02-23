package dao.interfaces;

import dto.ProjectDTO;
import entities.Project;
import java.util.ArrayList;

public interface IProjectDAO
{
    boolean add(ProjectDTO projectDto);
    boolean update(ProjectDTO projectDto);

    ArrayList<Project> getProjectsByUser(int UserId);

}
