package ua.rud.testingsystem.dao;

import ua.rud.testingsystem.entities.test.Test;

import java.util.List;

/**
 * DAO with methods to operate with {@link ua.rud.testingsystem.entities.test.Test} entities
 */
public interface TestDao {
    /**
     * Extract a {@link Test} with a particular id from database
     *
     * @param testId {@link Test}'s id
     * @return Test according to specified id
     */
    Test getTestById(int testId);

    /**
     * Save {@link Test} to database
     *
     * @param subjectId id of {@link ua.rud.testingsystem.entities.Subject} the {@link Test} to be added refers to
     * @param test      {@link Test} to be added
     */
    void addTest(int subjectId, Test test);

    /**
     * Get results (percents of right answers) for a specified {@link ua.rud.testingsystem.entities.user.User}
     * concerning particular {@link Test}
     *
     * @param userId id of {@link ua.rud.testingsystem.entities.user.User}
     * @param testId id of {@link Test}
     * @return List of results (percents of right answers)
     */
    List<Integer> getResults(int userId, int testId);

    /**
     * Save result to database
     *
     * @param userId id of {@link ua.rud.testingsystem.entities.user.User}
     * @param testId id of {@link Test}
     * @param rate   percent of right answers
     */
    void addResult(int userId, int testId, int rate);

    /**
     *
     * @param testIds
     */
    void deleteTests(List<Integer> testIds);
}
