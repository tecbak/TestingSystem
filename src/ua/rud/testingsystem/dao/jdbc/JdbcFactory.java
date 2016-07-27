package ua.rud.testingsystem.dao.jdbc;

import ua.rud.testingsystem.dao.DaoFactory;
import ua.rud.testingsystem.dao.SubjectDao;
import ua.rud.testingsystem.dao.TestDao;
import ua.rud.testingsystem.dao.UserDao;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

public class JdbcFactory implements DaoFactory {
    private static JdbcFactory instance = new JdbcFactory();

    private DataSource dataSource;

    private UserDao userDao = new UserJdbc();
    private SubjectDao subjectDao = new SubjectJdbc();
    private TestDao testDao = new TestsJdbc();

    /*Constructor*/
    private JdbcFactory() {
        try {
            Context envCtx = (Context) (new InitialContext().lookup("java:comp/env"));
            dataSource = (DataSource) envCtx.lookup("jdbc/testingsystem");
        } catch (NamingException e) {
            e.printStackTrace();
            // TODO: 23.07.2016 insert log here
        }
    }

    /*Methods*/
    public static JdbcFactory getInstance() {
        return instance;
    }

    /**
     * Get connection with database
     *
     * @return connection with database
     * @throws SQLException
     */
    public synchronized Connection getConnection() throws SQLException {
        return dataSource.getConnection();
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
