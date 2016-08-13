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

import static ua.rud.testingsystem.managers.SqlManager.getProperty;

public class TestsJdbc extends AbstractJdbc implements TestDao {
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
        final String SQL_GET_TEST_BY_ID = getProperty("sql.select.test");

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
        final String SQL_GET_RESULT = getProperty("sql.select.result");

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
        final String SQL_INSERT_TEST = getProperty("sql.insert.test");

        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement =
                     connection.prepareStatement(SQL_INSERT_TEST, PreparedStatement.RETURN_GENERATED_KEYS)) {

            String caption = test.getCaption();
            List<Question> questions = test.getQuestions();
            statement.setInt(1, subjectId);
            statement.setString(2, caption);

            /*Start of transaction*/
            connection.setAutoCommit(false);
            try {
                statement.executeUpdate();
                try (ResultSet generatedKeys = statement.getGeneratedKeys()) {
                    if (generatedKeys.next()) {
                        int testId = generatedKeys.getInt(1);
                        addQuestions(connection, questions, testId);
                    }
                }

                logger.info("Add test " + test);
            } catch (SQLException e) {
                connection.rollback();
                e.printStackTrace();
            } finally {
                connection.commit();
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
        final String SQL_INSERT_QUESTION = getProperty("sql.insert.question");

        for (Question question : questions) {
            try (PreparedStatement statement =
                         connection.prepareStatement(SQL_INSERT_QUESTION, PreparedStatement.RETURN_GENERATED_KEYS)) {

                String task = question.getTask();
                List<Answer> answers = question.getAnswers();

                statement.setInt(1, testID);
                statement.setString(2, task);
                statement.executeUpdate();

                try (ResultSet resultSet = statement.getGeneratedKeys()) {
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
        final String SQL_INSERT_ANSWER = getProperty("sql.insert.answer");

        try (PreparedStatement statement = connection.prepareStatement(SQL_INSERT_ANSWER)) {
            for (Answer answer : answers) {
                String text = answer.getText();
                boolean correct = answer.isCorrect();

                statement.setInt(1, questionId);
                statement.setString(2, text);
                statement.setBoolean(3, correct);

                statement.addBatch();
            }
            statement.executeBatch();
        }
    }

    @Override
    public void addResult(int userId, int testId, int rate) {
        final String SQL_INSERT_RESULT = getProperty("sql.insert.result");

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
        final String SQL_DELETE_TEST = getProperty("sql.delete.test");

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
