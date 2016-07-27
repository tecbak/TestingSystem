package ua.rud.testingsystem.dao;

import ua.rud.testingsystem.entities.test.Test;

import java.util.List;

public interface TestDao {

    Test getTestById(int testId);

    void addTest(int subjectId, Test test);

    List<Integer> getResults(int userId, int testId);

    void addResult(int userId, int testId, int rate);

    void deleteTests(List<Integer> testIds);
}
