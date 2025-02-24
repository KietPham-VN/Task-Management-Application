package services.implementations;

import dao.implementations.TaskDAO;
import entities.Tasks;
import java.util.ArrayList;
import services.interfaces.ITaskServices;

public class TaskServices implements ITaskServices
{

    @Override
    public ArrayList<Tasks> getTasksByProjectId(int projectId, String name, String sortBy) {
        TaskDAO taskDAO = new TaskDAO();
        ArrayList<Tasks> tasks = taskDAO.getTasksByProjectId(projectId,name);
        
        switch(sortBy){
            case "status":
                tasks.sort((t1,t2)->{
                    return t2.getStatus().getValue()-t1.getStatus().getValue();
                });
                break;
            case "priority":
                tasks.sort((t1,t2)->{
                    return t2.getPriority().getValue()-t1.getPriority().getValue();
                });
                break;
            default:
                tasks.sort((t1,t2)->{
                    return t2.getDueDate().compareTo(t1.getDueDate());
                });
                break;
        }
        return tasks;
    }

}
