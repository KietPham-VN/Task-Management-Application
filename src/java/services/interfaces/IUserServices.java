package services.interfaces;

import entities.User;

public interface IUserServices
{
    public User login(String email, String password);
}
