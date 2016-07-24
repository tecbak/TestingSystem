package ua.rud.testingsystem.entities.utils;

import ua.rud.testingsystem.dao.DaoFactory;
import ua.rud.testingsystem.dao.TestDao;
import ua.rud.testingsystem.dao.jdbc.JdbcFactory;
import ua.rud.testingsystem.entities.test.Test;
import ua.rud.testingsystem.entities.user.User;

import java.util.ArrayList;
import java.util.List;

public final class TestUtils {

    private TestUtils() {
    }

    public static Test getTest(int id) {
        DaoFactory factory = JdbcFactory.getInstance();
        TestDao dao = factory.getTestDao();
        return dao.getTestById(id);
    }

    // TODO: 23.07.2016 complete this doc

    /**
     * @param test
     * @param stringAnswers
     * @throws NumberFormatException    if the string array contains an invalid item
     * @throws IllegalArgumentException if string array or test is {@code null}
     */
    public static void applyAnswers(Test test, String[] stringAnswers) {
        if (test == null || stringAnswers == null) {
            throw new IllegalArgumentException("argument cannot be null");
        }

        List<Integer> answerList = stringArrayToIntList(stringAnswers);
        test.applyAnswers(answerList);
    }

    private static List<Integer> stringArrayToIntList(String[] stringArray) {
        List<Integer> array = new ArrayList<>();

        for (String s : stringArray) {
            array.add(Integer.parseInt(s));
        }

        return array;
    }

    public static void addResult(User user, Test test) {
        DaoFactory factory = JdbcFactory.getInstance();
        TestDao dao = factory.getTestDao();
        int userId = user.getId();
        int testId = test.getId();
        int rate = test.getRate();
        dao.addResult(userId, testId, rate);
    }
}
