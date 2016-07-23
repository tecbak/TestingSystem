package ua.rud.testingsystem.dao.jdbc;

import ua.rud.testingsystem.dao.ConnectorMySQL;
import ua.rud.testingsystem.dao.MenuDao;
import ua.rud.testingsystem.model.test.Answer;
import ua.rud.testingsystem.model.test.Question;
import ua.rud.testingsystem.model.test.Subject;
import ua.rud.testingsystem.model.test.Test;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MenuJdbc implements MenuDao {

    private final static String SQL_GET_SUBJECTS =
            "SELECT subjectId AS id, name FROM subjects;";
    private final static String SQL_GET_TEST_CAPTIONS_BY_SUBJECT_ID =
            "SELECT testId AS id, caption FROM tests WHERE subjectId = ?";

    public MenuJdbc() {
    }

    @Override
    public List<Subject> getSubjects() {
        List<Subject> list = new ArrayList<>();
        try (Connection connection = ConnectorMySQL.getConnection();
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

        try (PreparedStatement statement = connection.prepareStatement(SQL_GET_TEST_CAPTIONS_BY_SUBJECT_ID)) {
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









}