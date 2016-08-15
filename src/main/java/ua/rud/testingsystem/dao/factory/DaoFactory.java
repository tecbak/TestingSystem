package ua.rud.testingsystem.dao.factory;

import ua.rud.testingsystem.dao.SubjectDao;
import ua.rud.testingsystem.dao.TestDao;
import ua.rud.testingsystem.dao.UserDao;
import ua.rud.testingsystem.entities.subject.Subject;
import ua.rud.testingsystem.entities.test.Test;
import ua.rud.testingsystem.entities.user.User;

/**
 * Factory of Data Access Objects
 */
public interface DaoFactory {

    /**
     * Get DAO to operate with {@link User} entities
     *
     * @return DAO with methods to operate with {@link User}
     */
    UserDao getUserDao();

    /**
     * Get DAO to operate with {@link Subject} entities
     *
     * @return DAO with methods to operate with {@link Subject} entities
     */
    SubjectDao getSubjectDao();

    /**
     * Get DAO to operate with {@link Test} entities
     *
     * @return DAO with methods to operate with {@link Test} entities
     */
    TestDao getTestDao();
}
