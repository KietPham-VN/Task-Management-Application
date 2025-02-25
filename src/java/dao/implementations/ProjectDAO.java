package dao.implementations;

import common.constants.Queries;
import common.utils.DBUtils;
import dao.interfaces.IProjectDAO;
import dto.ProjectDTO;
import entities.Project;
import entities.User;
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
    public boolean add(ProjectDTO projectDto) {
        boolean sucess = false;
        String query = Queries.CREATE_PROJECT;
        try {
            conn = DBUtils.getConnection();
            ps = conn.prepareStatement(query);
            ps.setString(1, projectDto.getName());
            ps.setString(2, projectDto.getDescription());
            ps.setInt(3, projectDto.getCreatedBy());
            int exe = ps.executeUpdate();
            if (exe > 0)
            {
                sucess = true;
            }
        } catch (ClassNotFoundException | SQLException e)
        {
            System.out.println(e.getMessage());
            System.out.println("Failed added to database");
        }
        return sucess;
    }

    @Override
    public boolean update(ProjectDTO projectDto) {
        boolean success = false;
        return success;
    }
    
    public Project getProjectByName(String name) throws Exception
    {
        Project project = null;
        String query = Queries.GET_PROJECTS_BY_USER;
        conn = DBUtils.getConnection();
        ps = conn.prepareStatement(query);
        rs = ps.executeQuery();
        while (rs.next()) {
            project = new Project();
            project.setProjectId(rs.getInt("id"));
            project.setName(rs.getString("name"));
        }
        return project;
    }
    

    public ArrayList<Project> getProjectsByUser(int userId)
    {
        ArrayList<Project> projects = new ArrayList<>();

        try (Connection connection = DBUtils.getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(Queries.GET_PROJECTS_BY_USER))
        {
            preparedStatement.setInt(1, userId);

            try (ResultSet resultSet = preparedStatement.executeQuery())
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

    @Override
    public Project getProjectById(int projectId) {
        Project project = null;
        try(Connection conn = DBUtils.getConnection()){
            PreparedStatement ps = conn.prepareStatement(Queries.GET_PROJECT_BY_ID);
            ps.setInt(1, projectId);
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                project = new Project(rs.getInt("id"),rs.getString("name"),rs.getString("description"),rs.getInt("createdBy"),rs.getTimestamp("createdAt"));
            }
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ProjectDAO.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(ProjectDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return project;
    }
    
    @Override
    public boolean addUserToProject(int projectId, int userId) {
        boolean status = false;
        
        try {
            conn = DBUtils.getConnection();
            ps = conn.prepareStatement(Queries.ADD_USER_TO_PROJECT);
            ps.setInt(1, projectId);
            ps.setInt(2, userId);
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
    public ArrayList<User> getUserNotInProject(int projectId) {
        ArrayList<User> users = new ArrayList<>();
        
        try {
            conn = DBUtils.getConnection();
            ps = conn.prepareStatement(Queries.GET_USER_NOT_IN_PROJECT);
            ps.setInt(1,projectId);
            rs = ps.executeQuery();
            
            while(rs.next()){
                User user = new User();
                user.setId(rs.getInt("id"));
                user.setName(rs.getString("name"));
                user.setEmail(rs.getString("email"));
                users.add(user);
            }
                    
            conn.close();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ProjectDAO.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(ProjectDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return users;
    }

    @Override
    public ArrayList<User> getUserInProject(int projectId) {
        ArrayList<User> users = new ArrayList<>();
        
        try {
            conn = DBUtils.getConnection();
            ps = conn.prepareStatement(Queries.GET_USER_IN_PROJECT);
            ps.setInt(1,projectId);
            rs = ps.executeQuery();
            
            while(rs.next()){
                User user = new User();
                user.setId(rs.getInt("id"));
                user.setName(rs.getString("name"));
                user.setEmail(rs.getString("email"));
                users.add(user);
            }
                    
            conn.close();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ProjectDAO.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(ProjectDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return users;
    }
    
}
