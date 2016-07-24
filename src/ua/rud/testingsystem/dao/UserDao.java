package ua.rud.testingsystem.dao;

import ua.rud.testingsystem.entities.user.User;

public interface UserDao {
    boolean loginExists(String login);

    boolean emailExists(String email);

    void addUser(User user, String password);

    User getUser(String login, String password);
}
