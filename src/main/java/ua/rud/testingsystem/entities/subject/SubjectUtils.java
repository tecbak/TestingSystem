package ua.rud.testingsystem.entities.subject;


import ua.rud.testingsystem.dao.factory.DaoFactory;
import ua.rud.testingsystem.dao.SubjectDao;
import ua.rud.testingsystem.dao.factory.JdbcFactory;
import ua.rud.testingsystem.entities.CommonUtils;
import ua.rud.testingsystem.entities.test.TestUtils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Utility class for {@link Subject}
 */
public final class SubjectUtils {

    private SubjectUtils() {
    }

    /**
     * Extract {@link List} of {@link Subject}s frin database
     *
     * @return List of {@link Subject}s
     */
    public static List<Subject> getSubjects() {
        DaoFactory factory = JdbcFactory.getInstance();
        SubjectDao subjectDao = factory.getSubjectDao();
        List<Subject> subjects = subjectDao.getSubjects();
        return subjects;
    }

    /**
     * Add {@link Subject} to database
     *
     * @param subject a {@link Subject} to be added
     */
    public static void addSubject(Subject subject) {
        DaoFactory instance = JdbcFactory.getInstance();
        SubjectDao subjectDao = instance.getSubjectDao();
        subjectDao.addSubject(subject);
    }


    /**
     * Check if a {@link Subject} with a particular name exists in database
     *
     * @param name name of {@link Subject} to check
     * @return {@code true} if subject exists or {@code false} otherwise
     */
    public static boolean subjectExists(String name) {
        DaoFactory factory = JdbcFactory.getInstance();
        SubjectDao subjectDao = factory.getSubjectDao();
        boolean exists = subjectDao.subjectExists(name);
        return exists;

    }

    /**
     * Delete {@link Subject}s from database
     *
     * @param stringSubjectIds ids of subjects to be deleted
     */
    public static void deleteSubjects(String[] stringSubjectIds) {
        List<Integer> subjectIds = CommonUtils.stringArrayToIntList(stringSubjectIds);
        DaoFactory factory = JdbcFactory.getInstance();
        SubjectDao subjectDao = factory.getSubjectDao();
        subjectDao.deleteSubjects(subjectIds);
    }

    /**
     * Get results for all tests for a specific user
     *
     * @param subjects
     * @param userId
     * @return map with keys of testIds and values if lists of results
     * @throws NullPointerException if subjects is {@code null}
     */
    public static Map<Integer, List<Integer>> getResultsForSubjects(List<Subject> subjects, int userId) {
        Map<Integer, List<Integer>> resultMap = new HashMap<>();
        for (Subject subject : subjects) {
            for (int testId : subject.getTests().keySet()) {
                List<Integer> results = TestUtils.getResults(userId, testId);
                resultMap.put(testId, results);
            }
        }
        return resultMap;
    }
}
