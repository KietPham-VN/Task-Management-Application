package services.implementations;

import dao.implementations.TaskDAO;
import dao.interfaces.ITaskDAO;
import dto.TaskDTO;
import entities.Tasks;
import entities.User;
import java.util.ArrayList;
import java.util.HashSet;
import services.interfaces.ITaskServices;

public class TaskServices implements ITaskServices
{

    private ArrayList<Tasks> sortProject(ArrayList<Tasks> tasks, String sortBy)
    {
        switch (sortBy)
        {
            case "status":
                tasks.sort((t1, t2) ->
                {
                    return t2.getStatus().getValue() - t1.getStatus().getValue();
                });
                break;
            case "priority":
                tasks.sort((t1, t2) ->
                {
                    return t2.getPriority().getValue() - t1.getPriority().getValue();
                });
                break;
            default:
                tasks.sort((t1, t2) ->
                {
                    return t2.getDueDate().compareTo(t1.getDueDate());
                });
                break;
        }
        return tasks;
    }

    @Override
    public ArrayList<Tasks> getTasksByProjectId(int projectId, String name, String sortBy)
    {
        TaskDAO taskDAO = new TaskDAO();
        ArrayList<Tasks> tasks = taskDAO.getTasksByProjectId(projectId, name);

        return sortProject(tasks, sortBy);
    }

    @Override
    public ArrayList<Tasks> getTasksByProjectIdWithMembers(int projectId, String name, String sortBy)
    {
        TaskDAO taskDAO = new TaskDAO();
        ArrayList<Tasks> tasks = taskDAO.getTasksByProjectIdWithMembers(projectId, name);

        return sortProject(tasks, sortBy);
    }

    @Override
    public ArrayList<User> getUserFromTasks(ArrayList<Tasks> tasks)
    {
        HashSet<Integer> hash = new HashSet<>();
        ArrayList<User> users = new ArrayList<>();

        for (Tasks task : tasks)
        {
            if (task.getAssignedToUser() != null)
            {
                if (!hash.contains(task.getAssidnedTo()))
                {
                    hash.add(task.getAssidnedTo());
                    users.add(task.getAssignedToUser());
                }
            }
        }
        return users;
    }

    @Override
    public boolean deleteTask(int taskId)
    {
        TaskDAO taskDAO = new TaskDAO();
        boolean hasDeleted = taskDAO.delete(taskId);

        return hasDeleted;
    }

    @Override
    public boolean updateTaskStatus(Tasks task)
    {
        ITaskDAO taskDAO = new TaskDAO();
        return taskDAO.updateStatus(task.getId(), task.getStatus().getValue());
    }
    
    public int getProjectIdByTaskId(int taskId) {
        ITaskDAO taskDAO = new TaskDAO();
        return taskDAO.getProjectIdByTaskId(taskId);
    }

    @Override
    public TaskDTO getTaskById(int taskId) {
        ITaskDAO taskDAO = new TaskDAO();
        return taskDAO.getTaskById(taskId);
    }

}
