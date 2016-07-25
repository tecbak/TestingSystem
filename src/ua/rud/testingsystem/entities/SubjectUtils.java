package ua.rud.testingsystem.entities;


import ua.rud.testingsystem.dao.DaoFactory;
import ua.rud.testingsystem.dao.SubjectDao;
import ua.rud.testingsystem.dao.jdbc.JdbcFactory;

import java.util.List;

public final class SubjectUtils {

    private SubjectUtils() {
    }

    public static List<Subject> getSubjects() {
        DaoFactory factory = JdbcFactory.getInstance();
        SubjectDao subjectDao = factory.getSubjectDao();
        List<Subject> subjects = subjectDao.getSubjects();
        return subjects;
    }

}
