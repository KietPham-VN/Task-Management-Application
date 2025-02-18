package dao.implementations;

import common.constants.Queries;
import common.utils.DBUtils;
import dao.interfaces.IProjectDAO;
import dto.ProjectDTO;
import entities.Project;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class ProjectDAO implements IProjectDAO {

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
            ps.setInt(3, projectDto.getCreatedBy().getId());
            int exe = ps.executeUpdate();
            if (exe > 0) {
                sucess = true;
            }
        } catch (Exception e) {
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
    
    public Project getProjectByName(String name) {
        Project project = null;
        String query = Queries.GET_PROJECT_BY_NAME;
        try {
            conn = DBUtils.getConnection();
            ps = conn.prepareStatement(query);
            rs = ps.executeQuery();
            while (rs.next()) {
                project = new Project();
                project.setProjectId(rs.getInt("id"));
                project.setName(rs.getString("name"));
            }
        } catch (Exception e) {
        }
        return project;
    }
}
