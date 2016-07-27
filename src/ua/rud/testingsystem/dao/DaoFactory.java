package ua.rud.testingsystem.dao;

import java.sql.Connection;
import java.sql.SQLException;

public interface DaoFactory {

    Connection getConnection() throws SQLException;

    UserDao getUserDao();

    SubjectDao getSubjectDao();

    TestDao getTestDao();
}
