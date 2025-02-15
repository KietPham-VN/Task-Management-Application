package services.implementations;

import common.utils.PasswordUtils;
import dao.implementations.UserDAO;
import dao.interfaces.IUserDAO;
import dto.UserDTO;
import entities.User;
import services.interfaces.IUserServices;

public class UserServices implements IUserServices
{

    @Override
    public User login(String email, String password)
    {
        UserDTO.verifyLogin(email, password);
        IUserDAO userDAO = new UserDAO();
        User user = userDAO.findUserByEmail(email);
        if (user == null) {
            return null;
        }
        
        String salt = user.getSalt(); 
        String hashedPassword = PasswordUtils.hashPassword(password, salt); 

        if (hashedPassword.equals(user.getPasswordHash())) {
            return user;
        }
        return null;
    }

}
