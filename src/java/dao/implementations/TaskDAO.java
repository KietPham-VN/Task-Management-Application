package dao.implementations;

import common.constants.Queries;
import common.utils.DBUtils;
import dao.interfaces.ITaskDAO;
import dto.TaskDTO;
import entities.Tasks;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class TaskDAO implements ITaskDAO
{
    Connection conn = null;
    PreparedStatement ps = null;
    ResultSet rs = null;

    @Override
    public boolean add(TaskDTO taskDto) {
        boolean success = false;
        String query = Queries.CREATE_TASK; //projectId, name, description, assignedTo, status, priority, dueDate
        try {
            conn = DBUtils.getConnection();
            ps = conn.prepareStatement(query);
            ps.setInt(1, taskDto.getProjectId());
            ps.setString(2, taskDto.getName());
            ps.setString(3, taskDto.getDescription());
            ps.setInt(4, taskDto.getAssignedTo());
            ps.setString(5, taskDto.getStatus());
            ps.setString(6, taskDto.getPriority());
            ps.setDate(7, taskDto.getDueDate());
            int exe = ps.executeUpdate();
            if (exe > 0) {
                success = true;
            }
        } catch (Exception e) {
            System.out.println("Failed added to database" + e);
        }
        return success;
    }

    @Override
    public boolean update(TaskDTO taskDto) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean delete(TaskDTO taskDto) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ArrayList<Tasks> getTasksByProjectId(int projectId) {
        ArrayList<Tasks> tasks = new ArrayList<>();
        try{
            conn = DBUtils.getConnection();
            ps = conn.prepareCall(Queries.GET_TASKS_BY_PROJECT);
            ps.setInt(1, projectId);
            rs = ps.executeQuery();
            while(rs.next()){
                Tasks task = new Tasks(rs.getInt("id"),rs.getInt("projectId"),
                rs.getString("name"),rs.getString("description"),
                rs.getInt("assignedTo"),rs.getInt("status"),
                rs.getInt("priority"),rs.getTimestamp("dueDate"),
                rs.getTimestamp("createdAt"));
                tasks.add(task);
            }
        }
        catch (ClassNotFoundException | SQLException e)
        {
            System.out.println(e.getMessage());
            System.out.println("Failed added to database");
        }
        return tasks;
    }

}
