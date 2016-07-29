package ua.rud.testingsystem.dao.jdbc;

import ua.rud.testingsystem.dao.SubjectDao;
import ua.rud.testingsystem.entities.Subject;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import static ua.rud.testingsystem.resource.SqlManager.getProperty;

public class SubjectJdbc extends AbstractJdbc implements SubjectDao {

    public SubjectJdbc(DataSource dataSource) {
        super(dataSource);
    }

    @Override
    public void addSubject(Subject subject) {
        final String SQL_ADD_SUBJECT = getProperty("sql.insert.subject");
        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(SQL_ADD_SUBJECT)) {
            String name = subject.getName();
            statement.setString(1, name);
            statement.executeUpdate();
            logger.info(subject + " added");
        } catch (SQLException e) {
            logger.error(e);
        }
    }

    @Override
    public List<Subject> getSubjects() {
        final String SQL_GET_SUBJECTS = getProperty("sql.select.subjects");

        List<Subject> subjects = new ArrayList<>();

        try (Connection connection = dataSource.getConnection();
             Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery(SQL_GET_SUBJECTS);
            logger.info("Get all subjects");
            while (resultSet.next()) {

                /*
                 * If list of already extracted subjects doesn't contain a subject with id,
                 * that is equal to subjectId, - create a new subject
                 */
                int subjectId = resultSet.getInt("subjectId");
                Subject subject = getSubjectFromListById(subjects, subjectId);
                if (subject == null) {
                    subject = new Subject();
                    subject.setId(subjectId);
                    subject.setName(resultSet.getString("name"));
                    subjects.add(subject);
                }

                /*
                 * If subject already doesn't contain a test with id,
                 * that is equal to test id, - create a new test
                 */
                int testId = resultSet.getInt("testId");
                if (testId != 0) {
                    String caption = resultSet.getString("caption");
                    subject.addTest(testId, caption);
                }
            }
        } catch (SQLException e) {
            logger.error(e);
        }

        return subjects;
    }

    /**
     * Find a {@link Subject} with a specific id in a {@link List} of subjects
     *
     * @param subjects  a {@link List} of subjects to find in it
     * @param subjectId a {@link Subject}'s id to find in {@link List}
     * @return a {@link Subject} with a specified id if exists or {@code null} if doesn't
     */
    private Subject getSubjectFromListById(List<Subject> subjects, int subjectId) {
        for (Subject subject : subjects) {
            if (subject.getId() == subjectId) {
                return subject;
            }
        }
        return null;
    }

    @Override
    public boolean subjectExists(String name) {
        final String SQL_SUBJECT_EXISTS = getProperty("sql.exists.subject");
        boolean exists = true;
        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(SQL_SUBJECT_EXISTS)) {
            statement.setString(1, name);
            try (ResultSet resultSet = statement.executeQuery()) {

                logger.info("Query if exists subject " + name);
                if (resultSet.next()) {
                    exists = resultSet.getBoolean("exs");
                }
            }
        } catch (SQLException e) {
            logger.error(e);
        }
        return exists;
    }

    @Override
    public void deleteSubjects(List<Integer> subjectIds) {
        final String SQL_DELETE_SUBJECT = getProperty("sql.delete.subject");

        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(SQL_DELETE_SUBJECT)) {
            for (int subjectId : subjectIds) {
                statement.setInt(1, subjectId);
                statement.executeUpdate();

                logger.info("Deleted subject with id " + subjectId);
            }
        } catch (SQLException e) {
            logger.error(e);
        }
    }
}