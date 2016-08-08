package ua.rud.testingsystem.dao.jdbc;

import ua.rud.testingsystem.dao.UserDao;
import ua.rud.testingsystem.entities.user.User;
import ua.rud.testingsystem.entities.user.UserRole;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import static ua.rud.testingsystem.resource.SqlManager.getProperty;

public class UserJdbc extends AbstractJdbc implements UserDao {

    public UserJdbc(DataSource dataSource) {
        super(dataSource);
    }

    /*Methods*/
    @Override
    public boolean loginExists(String login) {
        final String SQL_IS_LOGIN_EXISTS = getProperty("sql.exists.login");

        return exists(login, SQL_IS_LOGIN_EXISTS);
    }

    @Override
    public boolean emailExists(String email) {
        final String SQL_IS_EMAIL_EXISTS = getProperty("sql.exists.email");
        return exists(email, SQL_IS_EMAIL_EXISTS);
    }

    private boolean exists(String element, String sql) {
        boolean exists = true;
        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, element);
            try (ResultSet resultSet = statement.executeQuery()) {

                logger.info("If exists " + element);
                if (resultSet.next()) {
                    exists = resultSet.getBoolean("exs");
                }
            }
        } catch (SQLException e) {
            logger.error(e);
        }
        return exists;
    }

    @Override
    public void addUser(User user, String password) {
        final String SQL_ADD_USER = getProperty("sql.insert.user");
        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(SQL_ADD_USER)) {
            statement.setString(1, user.getLogin());
            statement.setString(2, password);               //password is saved as MD5 cache
            statement.setString(3, user.getFirstName());
            statement.setString(4, user.getLastName());
            statement.setString(5, user.getEmail());
            statement.executeUpdate();

            logger.info("Add user " + user);
        } catch (SQLException e) {
            logger.error(e);
        }
    }

    @Override
    public User getUser(String login, String password) {
        final String SQL_AUTHORIZE = getProperty("sql.select.user");
        User user = null;

        try (Connection connection = dataSource.getConnection();
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
                if (user != null && user.getRole() == UserRole.ADMIN) {
                    logger.warn("Login admin");
                } else {
                    logger.info("Get user " + login);
                }

            }
        } catch (SQLException e) {
            logger.error(e);
        }
        return user;
    }
}
