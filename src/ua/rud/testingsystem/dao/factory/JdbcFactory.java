package ua.rud.testingsystem.dao.factory;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import ua.rud.testingsystem.dao.SubjectDao;
import ua.rud.testingsystem.dao.TestDao;
import ua.rud.testingsystem.dao.UserDao;
import ua.rud.testingsystem.dao.jdbc.SubjectJdbc;
import ua.rud.testingsystem.dao.jdbc.TestsJdbc;
import ua.rud.testingsystem.dao.jdbc.UserJdbc;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class JdbcFactory implements DaoFactory {
    private static JdbcFactory instance = new JdbcFactory();

    private DataSource dataSource;
    private UserDao userDao;
    private SubjectDao subjectDao;
    private TestDao testDao;
    private Logger logger;

    /*Constructor*/
    private JdbcFactory() {
        /*Initialization of logger*/
        this.logger = Logger.getLogger(this.getClass());
        this.logger.setLevel(Level.ERROR);

        /*Initialization of connection pool*/
        try {
            Context envCtx = (Context) (new InitialContext().lookup("java:comp/env"));
            dataSource = (DataSource) envCtx.lookup("jdbc/testingsystem");
        } catch (NamingException e) {
            logger.error(e);
        }
    }

    /*Methods*/
    public static JdbcFactory getInstance() {
        return instance;
    }

    @Override
    public UserDao getUserDao() {
        if (userDao == null) {
            userDao = new UserJdbc(dataSource);
        }
        return userDao;
    }

    @Override
    public SubjectDao getSubjectDao() {
        if (subjectDao == null) {
            subjectDao = new SubjectJdbc(dataSource);
        }
        return subjectDao;
    }

    @Override
    public TestDao getTestDao() {
        if (testDao == null) {
            testDao = new TestsJdbc(dataSource);
        }
        return testDao;
    }
}
