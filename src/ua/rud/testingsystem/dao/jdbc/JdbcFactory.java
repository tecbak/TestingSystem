package ua.rud.testingsystem.dao.jdbc;

import ua.rud.testingsystem.dao.*;

public class JdbcFactory implements DaoFactory {
    private static JdbcFactory instance = new JdbcFactory();

    private AuthorizationDao authorizationDao = new AuthorizationJdbc();
    private RegistrationDao registrationDao = new RegistrationJdbc();
    private MenuDao menuDao = new MenuJdbc();
    private TestDao testDao = new TestsJdbc();

    /*Constructor*/
    private JdbcFactory() {
    }

    /*Methods*/
    public static JdbcFactory getInstance() {
        return instance;
    }

    @Override
    public AuthorizationDao getAuthorizationDao() {
        return authorizationDao;
    }

    @Override
    public RegistrationDao getRegistrationDao() {
        return registrationDao;
    }

    @Override
    public MenuDao getMenuDao() {
        return menuDao;
    }

    @Override
    public TestDao getTestDao() {
        return testDao;
    }

}
