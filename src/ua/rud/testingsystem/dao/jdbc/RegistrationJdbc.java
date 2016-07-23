package ua.rud.testingsystem.dao.jdbc;

import ua.rud.testingsystem.dao.ConnectorMySQL;
import ua.rud.testingsystem.dao.RegistrationDao;
import ua.rud.testingsystem.model.user.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class RegistrationJdbc implements RegistrationDao {

    private static final String SQL_IS_LOGIN_EXISTS =
            "SELECT ? IN (SELECT login FROM users) AS exs;";
    private static final String SQL_IS_EMAIL_EXISTS =
            "SELECT ? IN (SELECT email FROM users) AS exs;";
    private static final String SQL_REGISTER_USER =
            "INSERT INTO users (login, password, firstName, lastName, email) VALUES (?, MD5(?), ?, ?, ?);";

    public RegistrationJdbc() {
    }

    @Override
    public boolean loginExists(String login) {
        return exists(login, SQL_IS_LOGIN_EXISTS);
    }

    @Override
    public boolean emailExists(String email) {
        return exists(email, SQL_IS_EMAIL_EXISTS);
    }

    private boolean exists(String element, String sql) {
        boolean exists = true;
        try (Connection connection = ConnectorMySQL.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, element);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                exists = resultSet.getBoolean("exs");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return exists;
    }

    @Override
    public void registerUser(User user, String password) {
        try (Connection connection = ConnectorMySQL.getConnection();
             PreparedStatement statement = connection.prepareStatement(SQL_REGISTER_USER)) {
            statement.setString(1, user.getLogin());
            statement.setString(2, password);
            statement.setString(3, user.getFirstName());
            statement.setString(4, user.getLastName());
            statement.setString(5, user.getEmail());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
