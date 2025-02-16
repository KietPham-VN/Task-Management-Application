package dao.implementations;

import common.constants.Queries;
import dao.interfaces.IUserDAO;
import entities.User;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import common.utils.DBUtils;
import java.sql.ResultSet;
import java.util.logging.Level;
import java.util.logging.Logger;

public class UserDAO implements IUserDAO
{
    @Override
    public boolean register(User newUser){
        boolean status = false;
        
        try {
            Connection con = DBUtils.getConnection();
            PreparedStatement ps = con.prepareStatement(Queries.REGISTER);
            ps.setString(1, newUser.getName());
            ps.setString(2, newUser.getEmail());
            ps.setString(3, newUser.getPasswordHash());
            ps.setString(4, newUser.getRole());
            ps.setString(5, newUser.getSalt());
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
    
    @Override
    public User findUserByEmail(String username)
    {
        User user = null;
        try (PreparedStatement ps = DBUtils.getConnection().prepareStatement(Queries.LOGIN))
        {
            ps.setString(1, username);
            ResultSet rs = ps.executeQuery();
            
            if (rs != null && rs.next())
            {
                user = new User();
                
                String hashedPassword = rs.getString(1);
                String storedSalt = rs.getString(2);
                String role = rs.getString(3);
                
                user.setEmail(username);
                user.setPasswordHash(hashedPassword);
                user.setSalt(storedSalt);
                user.setRole(role);
            }
        } catch (ClassNotFoundException | SQLException ex)
        {
            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return user;
    }
}
