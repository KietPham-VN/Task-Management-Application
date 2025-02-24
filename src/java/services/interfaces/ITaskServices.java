package services.interfaces;

import entities.Tasks;
import java.util.ArrayList;

public interface ITaskServices
{
    ArrayList<Tasks> getTasksByProjectId(int projectId, String name, String sortBy);
}
