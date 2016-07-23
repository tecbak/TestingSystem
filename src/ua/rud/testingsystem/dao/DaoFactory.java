package ua.rud.testingsystem.dao;

public interface DaoFactory {
    AuthorizationDao getAuthorizationDao();

    RegistrationDao getRegistrationDao();

    MenuDao getTestDao();
}
