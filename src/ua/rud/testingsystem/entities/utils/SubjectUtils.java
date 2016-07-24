package ua.rud.testingsystem.entities.utils;


import ua.rud.testingsystem.dao.DaoFactory;
import ua.rud.testingsystem.dao.SubjectDao;
import ua.rud.testingsystem.dao.jdbc.JdbcFactory;
import ua.rud.testingsystem.entities.sbj.Subject;

import java.util.List;

public final class SubjectUtils {

    private SubjectUtils() {
    }

    public static List<Subject> loadSubjects() {
        DaoFactory factory = JdbcFactory.getInstance();
        SubjectDao subjectDao = factory.getSubjectDao();
        List<Subject> subjects = subjectDao.getSubjects();
        return subjects;
    }
}
