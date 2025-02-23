package services.interfaces;

import entities.User;
import exceptions.ValidationException;

public interface IUserServices
{
    User login(String email, String password);

    User register(String name, String email, String password, String role) throws ValidationException;
}
