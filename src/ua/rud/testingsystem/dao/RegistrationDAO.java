package ua.rud.testingsystem.dao;

import ua.rud.testingsystem.model.user.User;

public interface RegistrationDAO {
    boolean loginExists(String login);

    boolean emailExists(String email);

    void registerUser(User user, String password);
}
