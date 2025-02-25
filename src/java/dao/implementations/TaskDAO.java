package dao.implementations;

import common.constants.Queries;
import common.enums.TaskPriority;
import common.enums.TaskStatus;
import common.utils.DBUtils;
import dao.interfaces.ITaskDAO;
import dto.TaskDTO;
import entities.Tasks;
import entities.User;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

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
            ps.setInt(5, TaskStatus.valueOf(taskDto.getStatus().toUpperCase()).getValue());
            ps.setInt(6, TaskPriority.valueOf(taskDto.getPriority().toUpperCase()).getValue());
            ps.setDate(7, taskDto.getDueDate());
            int exe = ps.executeUpdate();
            if (exe > 0) {
                success = true;
                System.out.println("chac add dc");
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
    public boolean delete(int taskId) {
        boolean status = false;
        try {
            conn = DBUtils.getConnection();
            ps = conn.prepareCall(Queries.DELETE_TASK_BY_ID);
            ps.setInt(1, taskId);
            status = ps.executeUpdate()>0;
            conn.close();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(TaskDAO.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(TaskDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return status;
    }

    @Override
    public ArrayList<Tasks> getTasksByProjectId(int projectId,String name) {
        String query = Queries.GET_TASKS_BY_PROJECT;
        if(!name.isEmpty())query+=" AND name LIKE ?";
        ArrayList<Tasks> tasks = new ArrayList<>();
        try{
            conn = DBUtils.getConnection();
            ps = conn.prepareCall(Queries.GET_TASKS_BY_PROJECT);
            ps.setInt(1, projectId);
            if(!name.isEmpty()) ps.setString(2, name);
            rs = ps.executeQuery();
            while(rs.next()){
                Tasks task = new Tasks(rs.getInt("id"),rs.getInt("projectId"),
                rs.getString("name"),rs.getString("description"),
                rs.getInt("assignedTo"),rs.getInt("status"),
                rs.getInt("priority"),rs.getTimestamp("dueDate"),
                rs.getTimestamp("createdAt"));
                tasks.add(task);
            }
            conn.close();
        }
        catch (ClassNotFoundException | SQLException e)
        {
            System.out.println(e.getMessage());
            System.out.println("Failed get tasks from database");
        }
        return tasks;
    }

    @Override
    public ArrayList<Tasks> getTasksByProjectIdWithMembers(int projectId, String name) {
       String query = Queries.GET_TASKS_BY_PROJECT;
        if(!name.isEmpty())query+=" AND name LIKE ?";
        ArrayList<Tasks> tasks = new ArrayList<>();
        try{
            conn = DBUtils.getConnection();
            ps = conn.prepareCall(Queries.GET_TASK_WITH_ASSIGN_USER);
            ps.setInt(1, projectId);
            if(!name.isEmpty()) ps.setString(2, name);
            rs = ps.executeQuery();
            while(rs.next()){
                Tasks task = new Tasks(rs.getInt("id"),rs.getInt("projectId"),
                rs.getString("name"),rs.getString("description"),
                rs.getInt("assignedTo"),rs.getInt("status"),
                rs.getInt("priority"),rs.getTimestamp("dueDate"),
                rs.getTimestamp("createdAt"));
                
                User assignedUser = new User();
                assignedUser.setId(rs.getInt("userId"));
                assignedUser.setName(rs.getString("userName"));
                assignedUser.setEmail(rs.getString("userEmail"));
                task.setAssignedToUser(assignedUser);
                
                tasks.add(task);
            }
            conn.close();
        }
        catch (ClassNotFoundException | SQLException e)
        {
            System.out.println(e.getMessage());
            System.out.println("Failed get tasks from database");
        }
        return tasks;
    }


}
