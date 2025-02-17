package services.interfaces;

import entities.User;
import exceptions.ValidationException;

public interface IUserServices
{
    public User login(String email, String password);
    
    public User register(String name, String email, String password, String role)  throws ValidationException;
}
