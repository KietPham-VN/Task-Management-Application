package services.implementations;

import common.utils.PasswordUtils;
import dao.implementations.UserDAO;
import dao.interfaces.IUserDAO;
import dto.UserDTO;
import entities.User;
import exceptions.InvalidDataException;
import exceptions.ValidationException;
import services.interfaces.IUserServices;

public class UserServices implements IUserServices
{
    

    @Override
    public User login(String email, String password)
    {
        boolean isVerified = UserDTO.verifyLogin(email, password);
        IUserDAO userDAO = new UserDAO();
        User user = userDAO.findUserByEmail(email);
        if (user == null || !isVerified)
        {
            return null;
        }

        String salt = user.getSalt();
        String hashedPassword = PasswordUtils.hashPassword(password, salt);

        if (hashedPassword.equals(user.getPasswordHash()))
        {
            return user;
        }
        return null;
    }

    @Override
    public User register(String name, String email, String password, String role) throws ValidationException {
        IUserDAO userDAO = new UserDAO();
        if(userDAO.findUserByEmail(email)==null){
            throw new InvalidDataException("Email has already been registered");
        }
        
        UserDTO.validateRegister(name, email, password, role);
        String salt = PasswordUtils.generateSalt();
        String passwordHashed = PasswordUtils.hashPassword(password, salt);
        
        User newUser = new User(name,email,passwordHashed,role,salt);
        if(!userDAO.register(newUser)) return null;
        
        return userDAO.findUserByEmail(email);
    }
}
