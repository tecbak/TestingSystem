package ua.rud.testingsystem.dao.jdbc;

import ua.rud.testingsystem.dao.ConnectorMySQL;
import ua.rud.testingsystem.dao.TestDao;
import ua.rud.testingsystem.model.test.Answer;
import ua.rud.testingsystem.model.test.Question;
import ua.rud.testingsystem.model.test.Test;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TestsJdbc implements TestDao {

    private static final String SQL_SELECT_TESTS_BY_SUBJECT_ID =
            "SELECT testId AS id, caption " +
                    "FROM tests WHERE subjectId = ?";


    private static final String SQL_SELECT_TEST_BY_ID =
            "SELECT caption FROM tests WHERE testId = ?";


    private static final String SQL_SELECT_QUESTIONS_BY_TEST_ID =
            "SELECT questionId AS id, task " +
                    "FROM questions WHERE testId = ?";

    private static final String SQL_SELECT_ANSWERS_BY_QUESTION_ID =
            "SELECT answerId AS id, text, correct " +
                    "FROM answers WHERE questionId = ?";

    private static final String SQL_INSERT_RESULT =
            "INSERT INTO results (userId, testId, rate) VALUES (?, ?, ?)";


    public static final String SQL_INSERT_TEST_SELECT_TEST_ID =
            "INSERT INTO tests (subjectId, caption) VALUES (?, ?); ";

    public static final String SQL_INSERT_QUESTION_SELECT_QUESTION_ID =
            "INSERT INTO questions (testId, task) VALUES (?, ?); " +
                    "SELECT LAST_INSERT_ID();";

    public static final String SQL_INSERT_ANSWER =
            "INSERT INTO answers (questionId, text, correct) VALUES (?, ?, ?);";

    public static final String SQL_LAST_INSERT_ID =
            "SELECT LAST_INSERT_ID();";


//
//    /**
//     * Creates tests concerning particular subject
//     *
//     * @param subjectId id number of the subject
//     * @return list of tests
//     */
//    public List<Test> getTests(int subjectId) {
//        List<Test> tests = new ArrayList<>();
//
//        try (Connection connection = ConnectorMySQL.getConnection();
//             PreparedStatement statement = connection.prepareStatement(SQL_SELECT_TESTS_BY_SUBJECT_ID)) {
//            statement.setInt(1, subjectId);
//            try (ResultSet resultSet = statement.executeQuery()) {
//                while (resultSet.next()) {
//                    int id = resultSet.getInt("id");
//                    String caption = resultSet.getString("caption");
//                    List<Question> questions = getQuestions(connection, id);
//
//                    Test test = new Test(id, caption, questions);
//                    tests.add(test);
//                }
//            }
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//
//        return tests;
//    }

    /*SELECT QUERIES*/

    /**
     * Extract test with particular ID from database
     *
     * @param testId test ID
     * @return test with particular ID or {@code null} if there's no test with such ID
     */
    @Override
    public Test getTestById(int testId) {
        Test test = null;

        try (Connection connection = ConnectorMySQL.getConnection();
             PreparedStatement statement = connection.prepareStatement(SQL_SELECT_TEST_BY_ID)) {
            statement.setInt(1, testId);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                String caption = resultSet.getString("caption");
                List<Question> questions = getQuestions(connection, testId);
                test = new Test(testId, caption, questions);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return test;
    }

    private List<Question> getQuestions(Connection connection, int testId) throws SQLException {
        List<Question> questions = new ArrayList<>();

        try (PreparedStatement statement = connection.prepareStatement(SQL_SELECT_QUESTIONS_BY_TEST_ID)) {
            statement.setInt(1, testId);
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    int id = resultSet.getInt("id");
                    String task = resultSet.getString("task");
                    List<Answer> answers = getAnswers(connection, id);

                    Question question = new Question(id, task, answers);
                    questions.add(question);
                }
            }
        }
        return questions;
    }

    private List<Answer> getAnswers(Connection connection, int questionId) throws SQLException {
        List<Answer> answers = new ArrayList<>();

        try (PreparedStatement statement = connection.prepareStatement(SQL_SELECT_ANSWERS_BY_QUESTION_ID)) {
            statement.setInt(1, questionId);
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    int id = resultSet.getInt("id");
                    String text = resultSet.getString("text");
                    Boolean correct = resultSet.getBoolean("correct");

                    Answer answer = new Answer(id, text, correct);
                    answers.add(answer);
                }
            }
        }
        return answers;
    }




    /*INSERT QUERIES*/
    @Override
    public void addResult(int userId, int testId, int rate) {
        try (Connection connection = ConnectorMySQL.getConnection();
             PreparedStatement statement = connection.prepareStatement(SQL_INSERT_RESULT)) {
            statement.setInt(1, userId);
            statement.setInt(2, testId);
            statement.setInt(3, rate);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public void addTest(int subjectId, Test test) {
        try (Connection connection = ConnectorMySQL.getConnection();
             PreparedStatement statement = connection.prepareStatement(SQL_INSERT_TEST_SELECT_TEST_ID)) {
            String caption = test.getCaption();
            List<Question> questions = test.getQuestions();

            statement.setInt(1, subjectId);
            statement.setString(2, caption);
            connection.setAutoCommit(false);

            try {
                statement.executeUpdate();
                Statement statement1 = connection.createStatement();
                ResultSet resultSet = statement1.executeQuery(SQL_LAST_INSERT_ID);
//                int testId = resultSet.getInt("LAST_INSERT_ID()");
                int testId;
                if (resultSet.next()) {
                    testId = resultSet.getInt("LAST_INSERT_ID()");
                    addQuestions(connection, questions, testId);
                }
            } catch (SQLException e) {
                connection.rollback();
                e.printStackTrace();
            } finally {
                connection.commit();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void addQuestions(Connection connection, List<Question> questions, int testID) throws SQLException {
        for (Question question : questions) {
            try (PreparedStatement statement = connection.prepareStatement(SQL_INSERT_QUESTION_SELECT_QUESTION_ID)) {
                String task = question.getTask();
                List<Answer> answers = question.getAnswers();

                statement.setInt(1, testID);
                statement.setString(2, task);
                ResultSet resultSet = statement.executeQuery();
                int questionId = resultSet.getInt("LAST_INSERT_ID()");
                addAnswers(connection, answers, questionId);
            }
        }
    }

    private void addAnswers(Connection connection, List<Answer> answers, int questionId) throws SQLException {
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
}
