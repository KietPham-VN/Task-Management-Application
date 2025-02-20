package dao.implementations;

import common.constants.Queries;
import common.utils.DBUtils;
import dao.interfaces.IProjectDAO;
import dto.ProjectDTO;
import entities.Project;

import java.sql.Connection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ProjectDAO implements IProjectDAO
{

    Connection conn = null;
    PreparedStatement ps = null;
    ResultSet rs = null;

    @Override
    public boolean add(ProjectDTO o)
    {
        boolean sucess = false;
        String query = Queries.CREATE_PROJECT;
        try
        {
            conn = DBUtils.getConnection();
            ps = conn.prepareStatement(query);
            ps.setString(1, o.getName());
            ps.setString(2, o.getDescription());
            ps.setInt(3, o.getCreatedBy().getId());
            int exe = ps.executeUpdate();
            if (exe > 0)
            {
                sucess = true;
            }
        } catch (Exception e)
        {
            System.out.println(e.getMessage());
            System.out.println("Failed added to database");
        }
        return sucess;
    }

    @Override
    public boolean update(ProjectDTO o)
    {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ArrayList<Project> getProjectsByUser(int userId)
    {
        ArrayList<Project> projects = new ArrayList<>();

        try (Connection connection = DBUtils.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(Queries.GET_PROJECTS_BY_USER))
        {
            preparedStatement.setInt(1, userId);

            try (ResultSet resultSet = ps.executeQuery())
            {
                while (resultSet.next())
                {
                    int projectId = resultSet.getInt("id");
                    String name = resultSet.getString("name");
                    String description = resultSet.getString("description");
                    int createdBy = resultSet.getInt("createdBy");
                    Timestamp createdAt = resultSet.getTimestamp("createdAt");

                    projects.add(new Project(projectId, name, description, createdBy, createdAt));
                }
            }
        } catch (SQLException ex)
        {
            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, "SQL Error", ex);
        } catch (ClassNotFoundException ex)
        {
            Logger.getLogger(ProjectDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return projects;
    }
}
