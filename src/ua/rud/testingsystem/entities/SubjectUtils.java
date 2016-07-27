package ua.rud.testingsystem.entities;


import ua.rud.testingsystem.dao.DaoFactory;
import ua.rud.testingsystem.dao.SubjectDao;
import ua.rud.testingsystem.dao.jdbc.JdbcFactory;
import ua.rud.testingsystem.entities.test.TestUtils;

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

    public static void addSubject(Subject subject) {
        DaoFactory instance = JdbcFactory.getInstance();
        SubjectDao subjectDao = instance.getSubjectDao();
        subjectDao.addSubject(subject);
    }


    public static boolean subjectExists(String name) {
        DaoFactory factory = JdbcFactory.getInstance();
        SubjectDao subjectDao = factory.getSubjectDao();
        boolean exists = subjectDao.subjectExists(name);
        return exists;

    }

    public static void deleteSubjects(String[] stringSubjectIds) {
        List<Integer> subjectIds = TestUtils.stringArrayToIntList(stringSubjectIds); //todo common utils
        DaoFactory factory = JdbcFactory.getInstance();
        SubjectDao subjectDao = factory.getSubjectDao();
        subjectDao.deleteSubjects(subjectIds);
    }
}
