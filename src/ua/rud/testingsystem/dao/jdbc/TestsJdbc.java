package ua.rud.testingsystem.dao.jdbc;

import ua.rud.testingsystem.dao.Connector;
import ua.rud.testingsystem.dao.TestDao;
import ua.rud.testingsystem.entities.test.Answer;
import ua.rud.testingsystem.entities.test.Question;
import ua.rud.testingsystem.entities.test.Test;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TestsJdbc implements TestDao {
    private static final String SQL_LAST_INSERT_ID = "SELECT LAST_INSERT_ID()";

//    private static final String SQL_SELECT_TESTS_BY_SUBJECT_ID =
//            "SELECT testId AS id, caption " +
//                    "FROM tests WHERE subjectId = ?";


//    private static final String SQL_SELECT_TEST_BY_ID =
//            "SELECT caption FROM tests WHERE testId = ?";


//    private static final String SQL_SELECT_QUESTIONS_BY_TEST_ID =
//            "SELECT questionId AS id, task " +
//                    "FROM questions WHERE testId = ?";

//    private static final String SQL_SELECT_ANSWERS_BY_QUESTION_ID =
//            "SELECT answerId AS id, text, correct " +
//                    "FROM answers WHERE questionId = ?";

//    private static final String SQL_INSERT_RESULT =
//            "INSERT INTO results (userId, testId, rate) VALUES (?, ?, ?)";


//    public static final String SQL_INSERT_TEST_SELECT_TEST_ID =
//            "INSERT INTO tests (subjectId, caption) VALUES (?, ?); ";

//    public static final String SQL_INSERT_QUESTION_SELECT_QUESTION_ID =
//            "INSERT INTO questions (testId, task) VALUES (?, ?); " +
//                    "SELECT LAST_INSERT_ID();";

//    public static final String SQL_INSERT_ANSWER =
//            "INSERT INTO answers (questionId, text, correct) VALUES (?, ?, ?);";


//    /**
//     * Creates tests concerning particular subject
//     *
//     * @param subjectId id number of the subject
//     * @return list of tests
//     */
////    @Override
//    public List<Test> getTestsBySubjectId(int subjectId, Connection connection) throws SQLException {
//        final String SQL_SELECT_TESTS_BY_SUBJECT_ID = "SELECT testId AS id, caption FROM tests WHERE subjectId = ?";
//
//        List<Test> tests = new ArrayList<>();
//
//        try (PreparedStatement statement = connection.prepareStatement(SQL_SELECT_TESTS_BY_SUBJECT_ID)) {
//            statement.setInt(1, subjectId);
//            try (ResultSet resultSet = statement.executeQuery()) {
//                while (resultSet.next()) {
//                    int id = resultSet.getInt("id");
//                    String caption = resultSet.getString("caption");
//                    Test test = new Test();
//                    test.setId(id);
//                    test.setCaption(caption);
//                    tests.add(test);
//                }
//            }
//        }
//        return tests;
//    }
//    /**
//     * Extract test with particular ID from database
//     *
//     * @param testId test ID
//     * @return test with particular ID or {@code null} if there's no test with such ID
//     */
//    @Override
//    public Test getTestById(int testId) {
//        Test test = null;
//
//        try (Connection connection = JdbcFactory.getConnection();
//             PreparedStatement statement = connection.prepareStatement(SQL_SELECT_TEST_BY_ID)) {
//            statement.setInt(1, testId);
//            ResultSet resultSet = statement.executeQuery();
//            if (resultSet.next()) {
//                String caption = resultSet.getString("caption");
//                List<Question> questions = getQuestions(connection, testId);
//                test = new Test(testId, caption, questions);
//            }
//
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//
//        return test;
//    }
//
//    private List<Question> getQuestions(Connection connection, int testId) throws SQLException {
//        List<Question> questions = new ArrayList<>();
//
//        try (PreparedStatement statement = connection.prepareStatement(SQL_SELECT_QUESTIONS_BY_TEST_ID)) {
//            statement.setInt(1, testId);
//            try (ResultSet resultSet = statement.executeQuery()) {
//                while (resultSet.next()) {
//                    int id = resultSet.getInt("id");
//                    String task = resultSet.getString("task");
//                    List<Answer> answers = getAnswers(connection, id);
//
//                    Question question = new Question(id, task, answers);
//                    questions.add(question);
//                }
//            }
//        }
//        return questions;
//    }

//    private List<Answer> getAnswers(Connection connection, int questionId) throws SQLException {
//        List<Answer> answers = new ArrayList<>();
//
//        try (PreparedStatement statement = connection.prepareStatement(SQL_SELECT_ANSWERS_BY_QUESTION_ID)) {
//            statement.setInt(1, questionId);
//            try (ResultSet resultSet = statement.executeQuery()) {
//                while (resultSet.next()) {
//                    int id = resultSet.getInt("id");
//                    String text = resultSet.getString("text");
//                    Boolean correct = resultSet.getBoolean("correct");
//
//                    Answer answer = new Answer(id, text, correct);
//                    answers.add(answer);
//                }
//            }
//        }
//        return answers;
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
        final String SQL_SELECT_TEST_BY_ID = "SELECT caption FROM tests WHERE testId = ?";
        Test test = null;

        try (Connection connection = Connector.getConnection();
             PreparedStatement statement = connection.prepareStatement(SQL_SELECT_TEST_BY_ID)) {
            statement.setInt(1, testId);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                String caption = resultSet.getString("caption");
                List<Question> questions = getQuestions(connection, testId);

                test = new Test();
                test.setId(testId);
                test.setCaption(caption);
                test.setQuestions(questions);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return test;
    }

    private List<Question> getQuestions(Connection connection, int testId) throws SQLException {
        final String SQL_SELECT_QUESTIONS_BY_TEST_ID = "SELECT questionId AS id, task FROM questions WHERE testId = ?";
        List<Question> questions = new ArrayList<>();

        try (PreparedStatement statement = connection.prepareStatement(SQL_SELECT_QUESTIONS_BY_TEST_ID)) {
            statement.setInt(1, testId);
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    int id = resultSet.getInt("id");
                    String task = resultSet.getString("task");
                    List<Answer> answers = getAnswers(connection, id);

                    Question question = new Question();
                    question.setId(id);
                    question.setTask(task);
                    question.setAnswers(answers);

                    questions.add(question);
                }
            }
        }
        return questions;
    }

    private List<Answer> getAnswers(Connection connection, int questionId) throws SQLException {
        final String SQL_SELECT_ANSWERS_BY_QUESTION_ID = "SELECT answerId AS id, text, correct FROM answers WHERE questionId = ?";
        List<Answer> answers = new ArrayList<>();

        try (PreparedStatement statement = connection.prepareStatement(SQL_SELECT_ANSWERS_BY_QUESTION_ID)) {
            statement.setInt(1, questionId);
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    int id = resultSet.getInt("id");
                    String text = resultSet.getString("text");
                    Boolean correct = resultSet.getBoolean("correct");

                    Answer answer = new Answer();
                    answer.setId(id);
                    answer.setText(text);
                    answer.setCorrect(correct);
                    answers.add(answer);
                }
            }
        }
        return answers;
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

        try (Connection connection = Connector.getConnection();
             PreparedStatement statement = connection.prepareStatement(SQL_GET_RESULT)) {
            statement.setInt(1, userId);
            statement.setInt(2, testId);

            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    int rate = resultSet.getInt("rate");
                    rates.add(rate);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
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

        try (Connection connection = Connector.getConnection();
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
            }
            /*End of transaction*/

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

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
                    int questionId = resultSet.getInt("LAST_INSERT_ID()");
                    addAnswers(connection, answers, questionId);
                }
            }
        }
    }

    private void addAnswers(Connection connection, List<Answer> answers, int questionId) throws SQLException {
        final String SQL_INSERT_ANSWER = "INSERT INTO answers (questionId, text, correct) VALUES (?, ?, ?)";
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

    /**
     * Add result to database
     *
     * @param userId user's id
     * @param testId test's id
     * @param rate   percent of right answers
     */
    @Override
    public void addResult(int userId, int testId, int rate) {
        final String SQL_INSERT_RESULT = "INSERT INTO results (userId, testId, rate) VALUES (?, ?, ?)";
        try (Connection connection = Connector.getConnection();
             PreparedStatement statement = connection.prepareStatement(SQL_INSERT_RESULT)) {
            statement.setInt(1, userId);
            statement.setInt(2, testId);
            statement.setInt(3, rate);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
