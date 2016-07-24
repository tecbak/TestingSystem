package ua.rud.testingsystem.dao;

import ua.rud.testingsystem.model.test.Test;

public interface TestDao {

    Test getTestById(int testId);

    void addResult(int userId, int testId, int rate);
}
