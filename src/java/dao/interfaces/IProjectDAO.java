package dao.interfaces;

import dto.ProjectDTO;
import entities.Project;
import java.util.ArrayList;

public interface IProjectDAO
{
    boolean add(ProjectDTO o);
    boolean update(ProjectDTO o);
    
    ArrayList<Project> getListOfProject(int ownerId);
}
