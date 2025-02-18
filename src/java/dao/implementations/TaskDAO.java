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
        String query = Queries.CREATE_TASK;
        try {
            conn = DBUtils.getConnection();
            ps = conn.prepareStatement(query);
            ps.set
        } catch (Exception e) {
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
