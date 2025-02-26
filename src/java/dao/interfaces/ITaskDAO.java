package dao.interfaces;

import dto.TaskDTO;
import entities.Tasks;
import java.util.ArrayList;

public interface ITaskDAO
{

    boolean add(TaskDTO taskDto);

    boolean update(TaskDTO taskDto);

    boolean delete(int TaskId);

    boolean updateStatus(int taskId, int status);

    ArrayList<Tasks> getTasksByProjectId(int projectId, String name);

    ArrayList<Tasks> getTasksByProjectIdWithMembers(int projectId, String name);

    int getProjectIdByTaskId(int taskId);
    
    TaskDTO getTaskById(int taskId);
}
