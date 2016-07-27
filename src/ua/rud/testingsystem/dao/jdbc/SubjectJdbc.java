package ua.rud.testingsystem.dao.jdbc;

import ua.rud.testingsystem.dao.SubjectDao;
import ua.rud.testingsystem.entities.Subject;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SubjectJdbc implements SubjectDao {

    //    private final static String SQL_GET_SUBJECTS =
//            "SELECT subjectId AS id, name FROM subjects;";
//    private final static String SQL_GET_TESTS_BY_SUBJECT_ID =
//            "SELECT testId AS id, caption FROM tests WHERE subjectId = ?";


    public SubjectJdbc() {
    }

    @Override
    public List<Subject> getSubjects() {
        final String SQL_GET_SUBJECTS = "SELECT subjectId AS id, name FROM subjects";

        List<Subject> list = new ArrayList<>();
        try (Connection connection = JdbcFactory.getInstance().getConnection();
             Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery(SQL_GET_SUBJECTS);
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                Map<Integer, String> tests = getTestCaptions(connection, id);

                Subject subject = new Subject();
                subject.setId(id);
                subject.setName(name);
                subject.setTests(tests);

                list.add(subject);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return list;
    }

    private Map<Integer, String> getTestCaptions(Connection connection, int subjectId) {
        String SQL_GET_TESTS_BY_SUBJECT_ID = "SELECT testId AS testId, caption FROM tests WHERE subjectId = ?";
        Map<Integer, String> map = new HashMap<>();

        try (PreparedStatement statement = connection.prepareStatement(SQL_GET_TESTS_BY_SUBJECT_ID)) {
            statement.setInt(1, subjectId);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                int id = resultSet.getInt("testId");
                String caption = resultSet.getString("caption");

                map.put(id, caption);
            }
        } catch (SQLException e) {
            // TODO: 26.07.2016 insert log
        }

        return map;
    }

    @Override
    public void addSubject(Subject subject) {
        final String SQL_ADD_SUBJECT = "INSERT INTO subjects (name) VALUES (?)";
        try (Connection connection = JdbcFactory.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement(SQL_ADD_SUBJECT)) {
            String name = subject.getName();
            statement.setString(1, name);
            statement.executeUpdate();
        } catch (SQLException e) {
            // TODO: 26.07.2016 insert log
        }
    }

    @Override
    public boolean subjectExists(String name) {
        final String SQL_SUBJECT_EXISTS = "SELECT ? IN (SELECT name FROM subjects) AS exs";
        boolean exists = true;
        try (Connection connection = JdbcFactory.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement(SQL_SUBJECT_EXISTS)) {
            statement.setString(1, name);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    exists = resultSet.getBoolean("exs");
                }
            }
        } catch (SQLException e) {
            // TODO: 26.07.2016 insert log
        }
        return exists;
    }

    @Override
    public void deleteSubjects(List<Integer> subjectIds) {
        final String SQL_DELETE_SUBJECT = "DELETE FROM subjects WHERE subjectId = ?";
        try (Connection connection = JdbcFactory.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement(SQL_DELETE_SUBJECT)) {
            for (int subjectId : subjectIds) {
                statement.setInt(1, subjectId);
                statement.executeUpdate();
            }
        } catch (SQLException e) {
            // TODO: 27.07.2016 add log
        }
    }


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
}
