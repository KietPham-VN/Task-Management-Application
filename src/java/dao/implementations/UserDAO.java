package dao.implementations;

import common.constants.Queries;
import common.utils.DBUtils;
import dao.interfaces.IUserDAO;
import entities.User;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class UserDAO implements IUserDAO
{

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
