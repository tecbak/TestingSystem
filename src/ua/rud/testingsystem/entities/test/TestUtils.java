package ua.rud.testingsystem.entities.test;

import ua.rud.testingsystem.dao.DaoFactory;
import ua.rud.testingsystem.dao.TestDao;
import ua.rud.testingsystem.dao.jdbc.JdbcFactory;
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

    public static void addResult(User user, Test test) {
        DaoFactory factory = JdbcFactory.getInstance();
        TestDao dao = factory.getTestDao();
        int userId = user.getId();
        int testId = test.getId();
        int rate = test.getRate();
        dao.addResult(userId, testId, rate);
    }

    public static List<Integer> getResults(int userId, int testId) {
        DaoFactory factory = JdbcFactory.getInstance();
        TestDao testDao = factory.getTestDao();
        List<Integer> results = testDao.getResults(userId, testId);
        return results;
    }

    public static Test getNewTest(String caption) {
        Test newTest = new Test();
        List<Question> questions = new ArrayList<>();
        newTest.setCaption(caption);
        newTest.setQuestions(questions);
        return newTest;
    }

    public static Question getNewQuestion(String task) {
        Question question = new Question();
        question.setTask(task);
        return question;
    }

    public static List<Answer> getNewAnswers(String[] texts, String[] answerIds) {
        List<Answer> answers = new ArrayList<>();
        List<Integer> answerIdsList = stringArrayToIntList(answerIds);


        for (int i = 0, n = texts.length; i < n; i++) {
            if (!texts[i].isEmpty()) {
                Answer answer = new Answer();
                String text = texts[i];
                boolean correct = answerIdsList.contains(i);
                answer.setCorrect(correct);
                answer.setText(text);
                answers.add(answer);
            }
        }
        return answers;
    }

    public static List<Integer> stringArrayToIntList(String[] stringArray) {
        List<Integer> array = new ArrayList<>();

        for (String s : stringArray) {
            array.add(Integer.parseInt(s));
        }

        return array;
    }

    public static void addTest(int subjectId, Test newTest) {
        DaoFactory factory = JdbcFactory.getInstance();
        TestDao testDao = factory.getTestDao();
        testDao.addTest(subjectId, newTest);
    }

    public static boolean allEmpty(String[] strings) {
        for (String string : strings) {
            if (!string.isEmpty()) {
                return false;
            }
        }
        return true;
    }

    public static void deleteTests(String[] stringTestIds) {
        List<Integer> testIds = stringArrayToIntList(stringTestIds);
        DaoFactory factory = JdbcFactory.getInstance();
        TestDao testDao = factory.getTestDao();
        testDao.deleteTests(testIds);
    }
}
