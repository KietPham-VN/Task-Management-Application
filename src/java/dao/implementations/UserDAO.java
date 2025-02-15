package dao.implementations;

import dao.interfaces.IUserDAO;
import entities.User;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import common.utils.DBUtils;

public class UserDAO implements IUserDAO
{
    public static boolean register(User newUser){
        boolean status = false;
        String sqlQuery = "INSERT INTO Users(name,email,passwordHash,role) VALUES(?,?,?,?)";
        
        try {
            Connection con = DBUtils.getConnection();
            PreparedStatement ps = con.prepareStatement(sqlQuery);
            ps.setString(1, newUser.getName());
            ps.setString(2, newUser.getEmail());
            ps.setString(3, newUser.getPasswordHash());
            ps.setString(4, newUser.getRole());
            int rows = ps.executeUpdate();
            status = rows>0;
            
            con.close();
        } catch (ClassNotFoundException ex) {
            System.out.println(ex);
            System.out.println("DBUtils not found!");
        } catch (SQLException ex) {
            System.out.println(ex);
            System.out.println("SQL Exception in inserting new product. Details: ");
            ex.printStackTrace();
        }
        return (status);
    }
}
