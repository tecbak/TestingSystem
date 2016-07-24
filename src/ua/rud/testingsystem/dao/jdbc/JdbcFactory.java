package ua.rud.testingsystem.dao.jdbc;

import ua.rud.testingsystem.dao.*;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

public class JdbcFactory implements DaoFactory {
    private static JdbcFactory instance = new JdbcFactory();

    private UserDao userDao = new UserJdbc();
    private SubjectDao subjectDao = new SubjectJdbc();
    private TestDao testDao = new TestsJdbc();

    /*Constructor*/
    private JdbcFactory() {
    }

    /*Methods*/
    public static JdbcFactory getInstance() {
        return instance;
    }

    @Override
    public UserDao getUserDao() {
        return userDao;
    }

    @Override
    public SubjectDao getSubjectDao() {
        return subjectDao;
    }

    @Override
    public TestDao getTestDao() {
        return testDao;
    }
}
