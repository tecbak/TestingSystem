package ua.rud.testingsystem.logic;

import ua.rud.testingsystem.dao.DaoFactory;
import ua.rud.testingsystem.dao.RegistrationDao;
import ua.rud.testingsystem.dao.jdbc.JdbcFactory;
import ua.rud.testingsystem.model.user.User;

public final class RegistrationLogic {

    private RegistrationLogic() {
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
        JdbcFactory factory = JdbcFactory.getInstance();
        RegistrationDao dao = factory.getRegistrationDao();
        return !dao.loginExists(login);
    }

    public static boolean isEmailUnique(String email) {
        JdbcFactory factory = JdbcFactory.getInstance();
        RegistrationDao dao = factory.getRegistrationDao();
        return !dao.emailExists(email);
    }

    public static boolean isEmailValid(String email) {
        final String EMAIL_PATTERN = "^[a-zA-Z0-9_!#$%&’*+/=?`{|}~^.-]+@[a-zA-Z0-9.-]+$";
        return email.matches(EMAIL_PATTERN);
    }

    public static void registerUser(User user, String password) {
        DaoFactory factory = JdbcFactory.getInstance();
        RegistrationDao dao = factory.getRegistrationDao();
        dao.registerUser(user, password);
    }
}
