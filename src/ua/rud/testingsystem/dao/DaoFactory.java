package ua.rud.testingsystem.dao;

public interface DaoFactory {

    UserDao getUserDao();

    SubjectDao getSubjectDao();

    TestDao getTestDao();
}
