package ua.rud.testingsystem.entities.utils;

import ua.rud.testingsystem.dao.DaoFactory;
import ua.rud.testingsystem.dao.UserDao;
import ua.rud.testingsystem.dao.jdbc.JdbcFactory;
import ua.rud.testingsystem.entities.user.User;

public final class UserUtils {

    private UserUtils() {
    }

    public static boolean isFilled(String... values) {
        for (String value : values) {
            if (value == null || value.isEmpty()) {
                return false;
            }
        }
        return true;
    }

    public static boolean isPasswordsMatch(String password0, String password1) {
        return password0.equals(password1);
    }

    public static boolean isLoginUnique(String login) {
        boolean unique = false;
        JdbcFactory factory = JdbcFactory.getInstance();
        UserDao dao = factory.getUserDao();
        unique = !dao.loginExists(login);
        return unique;
    }

    public static boolean isEmailUnique(String email) {
        boolean unique = false;
        JdbcFactory factory = JdbcFactory.getInstance();
        UserDao dao = factory.getUserDao();
        unique = !dao.emailExists(email);
        return unique;
    }

    public static boolean isEmailValid(String email) {
        final String EMAIL_PATTERN = "^[a-zA-Z0-9_!#$%&’*+/=?`{|}~^.-]+@[a-zA-Z0-9.-]+$";
        return email.matches(EMAIL_PATTERN);
    }


    public static void saveUser(User user, String password) {
        DaoFactory factory = JdbcFactory.getInstance();
        UserDao dao = factory.getUserDao();
        dao.addUser(user, password);
    }

    /**
     * Extracts {@link User} from database
     *
     * @param login    user's login
     * @param password user's password
     * @return extracted {@link User} if user with provided login/password pair exists in database
     * or {@code null} if login or password is incorrect
     */
    public static User getUser(String login, String password) {
        User user = null;

        DaoFactory factory = JdbcFactory.getInstance();
        UserDao dao = factory.getUserDao();
        user = dao.getUser(login, password);

        return user;
    }
}
