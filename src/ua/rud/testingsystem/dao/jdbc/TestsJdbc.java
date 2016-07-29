package ua.rud.testingsystem.dao.jdbc;

import ua.rud.testingsystem.dao.TestDao;
import ua.rud.testingsystem.entities.test.Answer;
import ua.rud.testingsystem.entities.test.Question;
import ua.rud.testingsystem.entities.test.Test;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class TestsJdbc extends AbstractJdbc implements TestDao {
    private static final String SQL_LAST_INSERT_ID = "SELECT LAST_INSERT_ID()";

    public TestsJdbc(DataSource dataSource) {
        super(dataSource);
    }

    /*SELECT QUERIES*/

    /**
     * Extract test with particular ID from database
     *
     * @param testId test ID
     * @return test with particular ID or {@code null} if there's no test with such ID
     */
    @Override
    public Test getTestById(int testId) {
        final String SQL_GET_TEST_BY_ID = "SELECT " +
                "t.testId, " +
                "t.caption, " +
                "q.questionId, " +
                "q.task, " +
                "a.answerId, " +
                "a.text, " +
                "a.correct " +
                "FROM tests AS t " +
                "JOIN questions AS q ON t.testId = q.testId " +
                "JOIN answers AS a ON q.questionId = a.questionId " +
                "WHERE t.testId = ?";

        Test test = null;

        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(SQL_GET_TEST_BY_ID)) {
            statement.setInt(1, testId);

            try (ResultSet resultSet = statement.executeQuery()) {
                logger.info("Get test with id " + testId);

                while (resultSet.next()) {

                    /*Create a new test if it hasn't been created yet*/
                    if (test == null) {
                        test = new Test();
                        test.setId(resultSet.getInt("testId"));
                        test.setCaption(resultSet.getString("caption"));
                    }

                    /*Create question if test doesn't have one with id that is equal to questionId*/
                    int questionId = resultSet.getInt("questionId");
                    Question question = test.getQuestionById(questionId);
                    if (question == null) {
                        question = new Question();
                        question.setId(questionId);
                        question.setTask(resultSet.getString("task"));
                        test.addQuestion(question);
                    }

                    /*Create answer*/
                    Answer answer = new Answer();
                    answer.setId(resultSet.getInt("answerId"));
                    answer.setText(resultSet.getString("text"));
                    answer.setCorrect(resultSet.getBoolean("correct"));
                    question.addAnswer(answer);
                }
            }
        } catch (SQLException e) {
            logger.error(e);
        }

        return test;
    }

    /**
     * Get all results of particular user and test
     *
     * @param userId users's id
     * @param testId test's id
     * @return result as percent of correct answers
     */
    @Override
    public List<Integer> getResults(int userId, int testId) {
        final String SQL_GET_RESULT = "SELECT rate FROM results WHERE userId = ? AND testId = ?";
        List<Integer> rates = new ArrayList<>();

        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(SQL_GET_RESULT)) {
            statement.setInt(1, userId);
            statement.setInt(2, testId);

            try (ResultSet resultSet = statement.executeQuery()) {
                logger.info("Get result of user with id " + userId + " and test id " + testId);

                while (resultSet.next()) {
                    int rate = resultSet.getInt("rate");
                    rates.add(rate);
                }
            }
        } catch (SQLException e) {
            logger.error(e);
        }
        return rates;
    }

    /*INSERT QUERIES*/

    /**
     * Add test to database
     *
     * @param subjectId id of test's subject
     * @param test      test to be added
     */
    @Override
    public void addTest(int subjectId, Test test) {
        final String SQL_INSERT_TEST = "INSERT INTO tests (subjectId, caption) VALUES (?, ?)";

        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement0 = connection.prepareStatement(SQL_INSERT_TEST);
             PreparedStatement statement1 = connection.prepareStatement(SQL_LAST_INSERT_ID)) {

            String caption = test.getCaption();
            List<Question> questions = test.getQuestions();
            statement0.setInt(1, subjectId);
            statement0.setString(2, caption);

            /*Start of transaction*/
            connection.setAutoCommit(false);
            try {
                statement0.executeUpdate();

                try (ResultSet resultSet = statement1.executeQuery()) {
                    if (resultSet.next()) {
                        int testId = resultSet.getInt("LAST_INSERT_ID()");
                        addQuestions(connection, questions, testId);
                    }
                }
            } catch (SQLException e) {
                connection.rollback();
                e.printStackTrace();
            } finally {
                connection.commit();
                logger.info("Add test " + test);
            }
            /*End of transaction*/
        } catch (SQLException e) {
            logger.error(e);
        }
    }

    /**
     * Add {@link Question}s of a specified {@link Test}
     *
     * @param connection {@link Connection} to database
     * @param questions  a {@link List} of {@link Question}s to be saved
     * @param testID     id of {@link Test} the {@link Question}s regard to
     * @throws SQLException in case of problems with database connection
     */
    private void addQuestions(Connection connection, List<Question> questions, int testID) throws SQLException {
        final String SQL_INSERT_QUESTION = "INSERT INTO questions (testId, task) VALUES (?, ?)";

        for (Question question : questions) {
            try (PreparedStatement statement0 = connection.prepareStatement(SQL_INSERT_QUESTION);
                 PreparedStatement statement1 = connection.prepareStatement(SQL_LAST_INSERT_ID)) {

                String task = question.getTask();
                List<Answer> answers = question.getAnswers();

                statement0.setInt(1, testID);
                statement0.setString(2, task);
                statement0.executeUpdate();

                try (ResultSet resultSet = statement1.executeQuery()) {
                    if (resultSet.next()) {
                        int questionId = resultSet.getInt(1);
                        addAnswers(connection, answers, questionId);
                    }
                }
            }
        }
    }

    /**
     * Save {@link Answer}s of the specified {@link Question}
     *
     * @param connection {@link Connection} to database
     * @param answers    a {@link List} of {@link Answer}s to be saved
     * @param questionId id of the {@link Question} these {@link Answer}s regard to
     * @throws SQLException in case of problems with database connection
     */
    private void addAnswers(Connection connection, List<Answer> answers, int questionId) throws SQLException {
        final String SQL_INSERT_ANSWER = "INSERT INTO answers (questionId, text, correct) VALUES (?, ?, ?)";
        // TODO: 28.07.2016 batch
        for (Answer answer : answers) {
            try (PreparedStatement statement = connection.prepareStatement(SQL_INSERT_ANSWER)) {
                String text = answer.getText();
                boolean correct = answer.isCorrect();

                statement.setInt(1, questionId);
                statement.setString(2, text);
                statement.setBoolean(3, correct);
                statement.executeUpdate();
            }
        }
    }

    @Override
    public void addResult(int userId, int testId, int rate) {
        final String SQL_INSERT_RESULT = "INSERT INTO results (userId, testId, rate) VALUES (?, ?, ?)";
        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(SQL_INSERT_RESULT)) {
            statement.setInt(1, userId);
            statement.setInt(2, testId);
            statement.setInt(3, rate);
            statement.executeUpdate();

            logger.info("Add result for user with id " + userId + " and test with id " + testId);
        } catch (SQLException e) {
            logger.error(e);
        }
    }

    @Override
    public void deleteTests(List<Integer> testIds) {
        final String SQL_DELETE_TEST = "DELETE FROM tests WHERE testId = ?";
        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(SQL_DELETE_TEST)) {
            for (Integer testId : testIds) {
                statement.setInt(1, testId);
                statement.executeUpdate();

                logger.info("Test with id " + testId + " deleted");
            }
        } catch (SQLException e) {
            logger.error(e);
        }
    }
}
