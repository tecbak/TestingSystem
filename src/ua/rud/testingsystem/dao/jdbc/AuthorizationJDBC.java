package ua.rud.testingsystem.dao.jdbc;

import ua.rud.testingsystem.dao.AuthorizationDAO;
import ua.rud.testingsystem.dao.ConnectorMySQL;
import ua.rud.testingsystem.model.user.User;
import ua.rud.testingsystem.model.user.UserRole;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AuthorizationJDBC implements AuthorizationDAO {
    private static AuthorizationJDBC instance = new AuthorizationJDBC();

    public static final String SQL_AUTHORIZE =
            "SELECT userId AS id, email, firstName, lastName, role " +
                    "FROM users WHERE login = ? AND password = md5(?)";

    private AuthorizationJDBC() {
    }

    public static AuthorizationJDBC getInstance() {
        return instance;
    }

    @Override
    public User getUser(String login, String password) {
        User user = null;

        try (Connection connection = ConnectorMySQL.getConnection();
             PreparedStatement statement = connection.prepareStatement(SQL_AUTHORIZE)) {
            statement.setString(1, login);
            statement.setString(2, password);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                user = new User();
                int id = resultSet.getInt("id");
                String email = resultSet.getString("email");
                String firstName = resultSet.getString("firstName");
                String lastName = resultSet.getString("lastName");
                String stringRole = resultSet.getString("role");
                UserRole role = UserRole.valueOf(stringRole.toUpperCase());
                user.setId(id);
                user.setLogin(login);
                user.setEmail(email);
                user.setFirstName(firstName);
                user.setLastName(lastName);
                user.setRole(role);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return user;
    }
}
