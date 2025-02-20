package services.interfaces;

import entities.Project;

public interface IProjectServices
{
    boolean createProject(String name, String description, int userId);
}
