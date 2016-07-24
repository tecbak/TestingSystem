package ua.rud.testingsystem.dao.jdbc;

import ua.rud.testingsystem.dao.Connector;
import ua.rud.testingsystem.dao.SubjectDao;
import ua.rud.testingsystem.entities.sbj.Subject;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SubjectJdbc implements SubjectDao {

    private final static String SQL_GET_SUBJECTS =
            "SELECT subjectId AS id, name FROM subjects;";
    private final static String SQL_GET_TESTS_BY_SUBJECT_ID =
            "SELECT testId AS id, caption FROM tests WHERE subjectId = ?";


    public SubjectJdbc() {
    }

    @Override
    public List<Subject> getSubjects() {
        List<Subject> list = new ArrayList<>();
        try (Connection connection = Connector.getConnection();
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
        Map<Integer, String> map = new HashMap<>();

        try (PreparedStatement statement = connection.prepareStatement(SQL_GET_TESTS_BY_SUBJECT_ID)) {
            statement.setInt(1, subjectId);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String caption = resultSet.getString("caption");

                map.put(id, caption);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return map;
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
