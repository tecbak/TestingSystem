package ua.rud.testingsystem.dao.jdbc;

import ua.rud.testingsystem.dao.AuthorizationDao;
import ua.rud.testingsystem.dao.DaoFactory;
import ua.rud.testingsystem.dao.MenuDao;
import ua.rud.testingsystem.dao.RegistrationDao;

public class JdbcFactory implements DaoFactory {
    private static JdbcFactory instance = new JdbcFactory();

    private AuthorizationDao authorizationDao = new AuthorizationJdbc();
    private RegistrationDao registrationDao = new RegistrationJdbc();
    private MenuDao testDao = new MenuJdbc();

    /*Constructor*/
    private JdbcFactory() {
    }

    /*Methods*/
    public static JdbcFactory getInstance() {
        return instance;
    }

    public AuthorizationDao getAuthorizationDao() {
        return authorizationDao;
    }

    public RegistrationDao getRegistrationDao() {
        return registrationDao;
    }

    public MenuDao getTestDao() {
        return testDao;
    }

}
