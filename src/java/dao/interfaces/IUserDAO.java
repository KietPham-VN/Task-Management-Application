package dao.interfaces;
import dto.UserDTO;
import entities.User;

public interface IUserDAO
{
    User getUserByName(String name);
    public User findUserByEmail(String email);
    public boolean register(User newUser);
}
