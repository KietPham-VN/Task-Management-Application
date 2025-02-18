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

    Connection conn = null;
    PreparedStatement ps = null;
    ResultSet rs = null;

    @Override
    public boolean register(User newUser)
    {
        boolean status = false;

        try
        {
            conn = DBUtils.getConnection();
            ps = conn.prepareStatement(Queries.REGISTER);
            ps.setString(1, newUser.getName());
            ps.setString(2, newUser.getEmail());
            ps.setString(3, newUser.getPasswordHash());
            ps.setString(4, newUser.getRole());
            ps.setString(5, newUser.getSalt());
            int rows = ps.executeUpdate();
            status = rows > 0;

            conn.close();
        } catch (ClassNotFoundException ex)
        {
            System.out.println(ex);
            System.out.println("DBUtils not found!");
        } catch (SQLException ex)
        {
            System.out.println(ex);
            System.out.println("SQL Exception in inserting new product. Details: ");
        }
        return (status);
    }

    @Override
    public User getUserByEmail(String username)
    {
        User user = null;
        try 
        {
            PreparedStatement ps = DBUtils.getConnection().prepareStatement(Queries.LOGIN);
            ps.setString(1, username);
            ResultSet rs = ps.executeQuery();

            if (rs != null && rs.next())
            {
                user = new User();
                int id = rs.getInt(1);
                String hashedPassword = rs.getString(2);
                String storedSalt = rs.getString(3);
                String role = rs.getString(4);

                user.setId(id);
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

    @Override
    public User getUserByName(String name)
    {
        User user = null;
        String query = Queries.GET_USER_BY_NAME;

        try
        {
            conn = DBUtils.getConnection();
            ps = conn.prepareStatement(query);
            ps.setString(1, name);
            rs = ps.executeQuery();
            while (rs.next())
            {
                user = new User();
                user.setId(rs.getInt("id"));
                user.setId(rs.getInt("id"));
                user.setName(rs.getString("username"));
                user.setEmail(rs.getString("email"));
                user.setRole(rs.getString("role"));
            }
        } catch (ClassNotFoundException | SQLException e)
        {
            System.out.println("Cannot add to database" + e);
        }
        return user;
    }
        }
        return user;
    }
}
