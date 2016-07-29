package ua.rud.testingsystem.dao;

import ua.rud.testingsystem.entities.user.User;

/**
 * DAO with methods to operate with {@link ua.rud.testingsystem.entities.user.User}
 */
public interface UserDao {

    /**
     * Check whether there is the same user's login in database
     *
     * @param login login to check
     * @return {@code true} if login exists, {@code false} if it doesn't
     */
    boolean loginExists(String login);

    /**
     * Check whether there is the same email in database
     *
     * @param email email to check
     * @return {@code true} if email exists, {@code false} if it doesn't
     */
    boolean emailExists(String email);

    /**
     * Save new {@link User} to database
     *
     * @param user     {@link User} to save
     * @param password {@link User}'s password
     */
    void addUser(User user, String password);

    /**
     * Extract {@link User} from database
     *
     * @param login    username
     * @param password {@link User}'s password
     * @return if there is a particular login/password pair in database - {@link User},
     * otherwise - {@code null}
     */
    User getUser(String login, String password);
}
