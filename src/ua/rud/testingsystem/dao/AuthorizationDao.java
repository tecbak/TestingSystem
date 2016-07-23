package ua.rud.testingsystem.dao;

import ua.rud.testingsystem.model.user.User;

public interface AuthorizationDao {
    User getUser(String login, String password);
}
