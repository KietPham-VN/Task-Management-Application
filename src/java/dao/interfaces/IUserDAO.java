package dao.interfaces;

import entities.User;

public interface IUserDAO
{

    User getUserByName(String name);

    User getUserByEmail(String email);

    boolean register(User newUser);
}
