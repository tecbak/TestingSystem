package ua.rud.testingsystem.dao.jdbc;

import ua.rud.testingsystem.dao.UserDao;
import ua.rud.testingsystem.entities.user.User;
import ua.rud.testingsystem.entities.user.UserRole;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserJdbc implements UserDao {

//            private static final String SQL_IS_LOGIN_EXISTS =
//            "SELECT ? IN (SELECT login FROM users) AS exs;";
//    private static final String SQL_IS_EMAIL_EXISTS =
//            "SELECT ? IN (SELECT email FROM users) AS exs;";
//    private static final String SQL_REGISTER_USER =
//            "INSERT INTO users (login, password, firstName, lastName, email) VALUES (?, MD5(?), ?, ?, ?);";
//
//    public static final String SQL_AUTHORIZE =
//            "SELECT userId AS id, email, firstName, lastName, role " +
//                    "FROM users WHERE login = ? AND password = md5(?)";

    public UserJdbc() {
    }

    @Override
    public boolean loginExists(String login) {
        final String SQL_IS_LOGIN_EXISTS = "SELECT ? IN (SELECT login FROM users) AS exs;";
        return exists(login, SQL_IS_LOGIN_EXISTS);
    }

    @Override
    public boolean emailExists(String email) {
        final String SQL_IS_EMAIL_EXISTS = "SELECT ? IN (SELECT email FROM users) AS exs;";
        return exists(email, SQL_IS_EMAIL_EXISTS);
    }

    private boolean exists(String element, String sql) {
        boolean exists = true;
        try (Connection connection = JdbcFactory.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, element);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    exists = resultSet.getBoolean("exs");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return exists;
    }

    @Override
    public void addUser(User user, String password) {
        final String SQL_ADD_USER =
                "INSERT INTO users (login, password, firstName, lastName, email) VALUES (?, MD5(?), ?, ?, ?);";
        try (Connection connection = JdbcFactory.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement(SQL_ADD_USER)) {
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

    @Override
    public User getUser(String login, String password) {
        final String SQL_AUTHORIZE =
                "SELECT userId AS id, email, firstName, lastName, role " +
                        "FROM users WHERE login = ? AND password = md5(?)";
        User user = null;

        try (Connection connection = JdbcFactory.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement(SQL_AUTHORIZE)) {
            statement.setString(1, login);
            statement.setString(2, password);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    int id = resultSet.getInt("id");
                    String email = resultSet.getString("email");
                    String firstName = resultSet.getString("firstName");
                    String lastName = resultSet.getString("lastName");
                    String stringRole = resultSet.getString("role");
                    UserRole role = UserRole.valueOf(stringRole.toUpperCase());

                    user = new User();
                    user.setId(id);
                    user.setLogin(login);
                    user.setEmail(email);
                    user.setFirstName(firstName);
                    user.setLastName(lastName);
                    user.setRole(role);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return user;
    }
}
