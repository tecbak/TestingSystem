package ua.rud.testingsystem.entities.test;

import ua.rud.testingsystem.dao.factory.DaoFactory;
import ua.rud.testingsystem.dao.TestDao;
import ua.rud.testingsystem.dao.factory.JdbcFactory;
import ua.rud.testingsystem.entities.CommonUtils;
import ua.rud.testingsystem.entities.subject.Subject;
import ua.rud.testingsystem.entities.user.User;

import java.util.ArrayList;
import java.util.List;

/**
 * Utility class for {@link Test}, {@link Question} and {@link Answer}
 */
public final class TestUtils {

    private TestUtils() {
    }

    /**
     * Use DAO to extract {@link Test} from database
     *
     * @param id {@link Test}'s id
     * @return extracted {@link Test}
     */
    public static Test getTest(int id) {
        DaoFactory factory = JdbcFactory.getInstance();
        TestDao dao = factory.getTestDao();
        return dao.getTestById(id);
    }

    /**
     * Convert string array to int list and invoke {@link Test}'s applyAnswers()
     *
     * @param test          {@link Test} for applying answers
     * @param stringAnswers string array with ids of {@link Answer}s
     * @throws NumberFormatException    if the string array contains an invalid item
     * @throws IllegalArgumentException if string array or test is {@code null}
     */
    public static void applyAnswers(Test test, String[] stringAnswers) {
        if (test == null || stringAnswers == null) {
            throw new IllegalArgumentException("argument cannot be null");
        }

        List<Integer> answerList = CommonUtils.stringArrayToIntList(stringAnswers);
        test.applyAnswers(answerList);
    }

    /**
     * Use DAO to save result
     *
     * @param user {@link User} who has completed a test
     * @param test completed {@link Test}
     */
    public static void addResult(User user, Test test) {
        DaoFactory factory = JdbcFactory.getInstance();
        TestDao dao = factory.getTestDao();
        int userId = user.getId();
        int testId = test.getId();
        int rate = test.getRate();
        dao.addResult(userId, testId, rate);
    }

    /**
     * Use DAO to extract results
     *
     * @param userId {@link User}'s id
     * @param testId {@link Test}'s id
     * @return
     */
    public static List<Integer> getResults(int userId, int testId) {
        DaoFactory factory = JdbcFactory.getInstance();
        TestDao testDao = factory.getTestDao();
        List<Integer> results = testDao.getResults(userId, testId);
        return results;
    }

    /**
     * Create a new {@link Test} with a particular caption
     *
     * @param caption {@link Test}'s caption
     * @return a new {@link Test}
     */
    public static Test getNewTest(String caption) {
        Test newTest = new Test();
        newTest.setCaption(caption);
        return newTest;
    }

    /**
     * Create a new {@link Question}
     *
     * @param task {@link Question}'s task
     * @return a new {@link Question}
     */
    public static Question getNewQuestion(String task) {
        Question question = new Question();
        question.setTask(task);
        return question;
    }

    /**
     * Create a {@link List} of {@link Answer}s
     *
     * @param texts     an array with text of {@link Answer}s
     * @param answerIds a string array storing ids of correct {@link Answer}s
     * @return a {@link List} created {@link Answer}s
     */
    public static List<Answer> getNewAnswers(String[] texts, String[] answerIds) {
        List<Answer> answers = new ArrayList<>();
        List<Integer> answerIdsList = CommonUtils.stringArrayToIntList(answerIds);

        /*
         * Create a new answer from the every not empty value of the texts array
         * and make this answer correct if answerId contains an index of texts array's element
         */
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

    /**
     * Use DAO to save a {@link Test}
     *
     * @param subjectId id of {@link Subject} the test belongs to
     * @param newTest   {@link Test} to be saved
     */
    public static void addTest(int subjectId, Test newTest) {
        DaoFactory factory = JdbcFactory.getInstance();
        TestDao testDao = factory.getTestDao();
        testDao.addTest(subjectId, newTest);
    }

    /**
     * Use DAO to delete some tests from database
     *
     * @param stringTestIds id of {@link Test}s to be deleted
     */
    public static void deleteTests(String[] stringTestIds) {
        List<Integer> testIds = CommonUtils.stringArrayToIntList(stringTestIds);
        DaoFactory factory = JdbcFactory.getInstance();
        TestDao testDao = factory.getTestDao();
        testDao.deleteTests(testIds);
    }
}
