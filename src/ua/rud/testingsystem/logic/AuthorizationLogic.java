package ua.rud.testingsystem.logic;

import ua.rud.testingsystem.dao.AuthorizationDao;
import ua.rud.testingsystem.dao.DaoFactory;
import ua.rud.testingsystem.dao.jdbc.JdbcFactory;
import ua.rud.testingsystem.model.user.User;

/**
 * Extracts user from Database
 */
public final class AuthorizationLogic {

    private AuthorizationLogic() {
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
        DaoFactory factory = JdbcFactory.getInstance();
        AuthorizationDao dao = factory.getAuthorizationDao();
        User user = dao.getUser(login, password);
        return user;
    }
}
