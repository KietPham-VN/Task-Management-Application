package dao.interfaces;

import entities.User;

public interface IUserDAO
{
    public User findUserByEmail(String email);
    
    public boolean register(User newUser);
}
