package dao.interfaces;

import dto.TaskDTO;
import entities.Tasks;
import java.util.ArrayList;

public interface ITaskDAO
{
    boolean add(TaskDTO taskDto);
    boolean update(TaskDTO taskDto);
    boolean delete(TaskDTO taskDto);
    ArrayList<Tasks> getTasksByProjectId(int projectId,String name);
}
