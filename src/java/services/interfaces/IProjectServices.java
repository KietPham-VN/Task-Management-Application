package services.interfaces;

import entities.Project;
import java.util.ArrayList;

public interface IProjectServices
{

    ArrayList<Project> getProjectsByUser(int userId);
}
