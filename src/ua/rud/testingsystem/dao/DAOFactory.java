package ua.rud.testingsystem.dao;

import ua.rud.testingsystem.dao.jdbc.AuthorizationJDBC;
import ua.rud.testingsystem.dao.jdbc.RegistrationJDBC;

public class DAOFactory {
    private static DAOFactory instance = new DAOFactory();

    private DAOFactory() {
    }

    public static DAOFactory getInstance() {
        return instance;
    }

    public AuthorizationDAO getAuthorizationDAO() {
        return AuthorizationJDBC.getInstance();
    }

    public RegistrationDAO getRegistrationDAO() {
        return RegistrationJDBC.getInstance();
    }

}
