package ua.rud.testingsystem.dao.factory;

import ua.rud.testingsystem.dao.SubjectDao;
import ua.rud.testingsystem.dao.TestDao;
import ua.rud.testingsystem.dao.UserDao;
import ua.rud.testingsystem.entities.subject.Subject;

/**
 * Factory of Data Access Objects
 */
public interface DaoFactory {

    /**
     * Get DAO to operate with {@link ua.rud.testingsystem.entities.user.User} entities
     *
     * @return DAO with methods to operate with {@link ua.rud.testingsystem.entities.user.User}
     */
    UserDao getUserDao();

    /**
     * Get DAO to operate with {@link Subject} entities
     *
     * @return DAO with methods to operate with {@link Subject} entities
     */
    SubjectDao getSubjectDao();

    /**
     * Get DAO to operate with {@link ua.rud.testingsystem.entities.test.Test} entities
     *
     * @return DAO with methods to operate with {@link ua.rud.testingsystem.entities.test.Test} entities
     */
    TestDao getTestDao();
}
