package dao.implementations;

import common.constants.Queries;
import common.utils.DBUtils;
import dao.interfaces.IProjectDAO;
import dto.ProjectDTO;
import entities.Project;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class ProjectDAO implements IProjectDAO {

    Connection conn = null;
    PreparedStatement ps = null;
    ResultSet rs = null;

    @Override
    public boolean add(ProjectDTO o) {
        boolean sucess = false;
        String query = Queries.createProject();
        try {
            conn = DBUtils.getConnection();
            ps = conn.prepareStatement(query);
            ps.setString(1, o.getName());
            ps.setString(2, o.getDescription());
            ps.setInt(3, o.getCreatedBy().getId());
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
    public boolean update(ProjectDTO o) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ArrayList<Project> getListOfProject(int ownerId) {
        ArrayList<Project> list = new ArrayList<>();
        
        try {
            conn = DBUtils.getConnection();
            ps = conn.prepareStatement(Queries.GET_PROJECT_LIST);
            ps.setInt(1, ownerId);
            rs = ps.executeQuery();
            while (rs.next()) {
                Project project = new Project();
                project.setProjectId(rs.getInt("id"));
                project.setName(rs.getString("name"));
                project.setDescription(rs.getString("description"));
                project.setCreateAt(rs.getDate("createdAt"));
                list.add(project);
            }
        } catch (Exception e) {
            System.out.println("Cannot add to database" + e);
        }
        return list;
    }

}
