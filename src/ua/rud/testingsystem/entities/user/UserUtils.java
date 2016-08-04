package ua.rud.testingsystem.entities.user;

import ua.rud.testingsystem.dao.UserDao;
import ua.rud.testingsystem.dao.factory.DaoFactory;
import ua.rud.testingsystem.dao.factory.JdbcFactory;

/**
 * Utility class for {@link User}
 */
public final class UserUtils {

    private UserUtils() {
    }

    /**
     * Check if all strings aren't empty
     *
     * @param values array of strings to check
     * @return {@code true} if there isn't any empty string, and {@code false} otherwise
     */
    // TODO: 29.07.2016 common utils
    public static boolean isFilled(String... values) {
        if (values == null || values.length == 0) {
            return false;
        }

        for (String value : values) {
            if (value == null || value.isEmpty()) {
                return false;
            }
        }
        return true;
    }

    /**
     * Check wether two passwords are equal
     *
     * @param password0
     * @param password1
     * @return {@code true} if passwords are equal and {@code false} otherwise
     */
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

    /**
     * Check if there is no such a email in database
     *
     * @param email email to be verified
     * @return {@code true} if email is unique and {@code false} otherwise
     */
    public static boolean isEmailUnique(String email) {
        boolean unique = false;
        JdbcFactory factory = JdbcFactory.getInstance();
        UserDao dao = factory.getUserDao();
        unique = !dao.emailExists(email);
        return unique;
    }

    /**
     * Check a validity of email
     *
     * @param email email to be checked
     * @return {@code true} if email is valid and {@code false} otherwise
     */
    public static boolean isEmailValid(String email) {
        final String EMAIL_PATTERN = "^[a-zA-Z0-9_!#$%&’*+/=?`{|}~^.-]+@[a-zA-Z0-9.-]+$";
        return email.matches(EMAIL_PATTERN);
    }


    /**
     * Use DAO to save user in database
     *
     * @param user     {@link User} to be saved
     * @param password user's password
     */
    public static void saveUser(User user, String password) {
        DaoFactory factory = JdbcFactory.getInstance();
        UserDao dao = factory.getUserDao();
        dao.addUser(user, password);
    }

    /**
     * Extract {@link User} from database
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
