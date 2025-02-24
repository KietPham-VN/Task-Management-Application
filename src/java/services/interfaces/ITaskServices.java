package services.interfaces;

import entities.Tasks;
import entities.User;
import java.util.ArrayList;

public interface ITaskServices
{
    ArrayList<Tasks> getTasksByProjectId(int projectId, String name, String sortBy);
    ArrayList<Tasks> getTasksByProjectIdWithMembers(int projectId, String name, String sortBy);
    ArrayList<User> getUserFromTasks(ArrayList<Tasks> tasks);
}
