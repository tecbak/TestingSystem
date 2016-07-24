package ua.rud.testingsystem.dao;

import ua.rud.testingsystem.entities.test.Test;

public interface TestDao {

    Test getTestById(int testId);

    void addTest(int subjectId, Test test);

    void addResult(int userId, int testId, int rate);

//    List<Test> getTestsBySubjectId(int subjectId, Connection connection) throws SQLException;
}
