package ua.rud.testingsystem.dao;

import ua.rud.testingsystem.entities.subject.Subject;
import ua.rud.testingsystem.entities.test.Test;
import ua.rud.testingsystem.entities.user.User;

import java.util.List;

/**
 * DAO with methods to operate with {@link Test} entities
 */
public interface TestDao {
    /**
     * Extract a {@link Test} with a particular id from database
     *
     * @param testId {@link Test}'s id
     * @return {@link Test} according to specified id or {@code null} if there's no test with such ID
     */
    Test getTestById(int testId);

    /**
     * Save {@link Test} to database
     *
     * @param subjectId id of {@link Subject} the {@link Test} to be added refers to
     * @param test      {@link Test} to be added
     */
    void addTest(int subjectId, Test test);

    /**
     * Get results (percents of right answers) for a specified {@link User}
     * concerning particular {@link Test}
     *
     * @param userId id of {@link User}
     * @param testId id of {@link Test}
     * @return List of results (percents of right answers)
     */
    List<Integer> getResults(int userId, int testId);

    /**
     * Save result to database
     *
     * @param userId id of {@link User}
     * @param testId id of {@link Test}
     * @param rate   percent of right answers
     */
    void addResult(int userId, int testId, int rate);

    /**
     * Delete specified {@link Test} from database
     *
     * @param testIds ids of {@link Test} to be deleted
     */
    void deleteTests(List<Integer> testIds);
}
