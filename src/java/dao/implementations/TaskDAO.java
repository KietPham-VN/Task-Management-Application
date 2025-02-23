package dao.implementations;

import common.constants.Queries;
import common.utils.DBUtils;
import dao.interfaces.ITaskDAO;
import dto.TaskDTO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

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
    public boolean delete(TaskDTO taskDto) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
