package ua.rud.testingsystem.dao;

import ua.rud.testingsystem.entities.subject.Subject;

import java.util.List;

/**
 * DAO with methods to operate with {@link Subject} entities
 */
public interface SubjectDao {
    /**
     * Get {@link List} of all {@link ua.rud.testingsystem.entities.test.Test}s stored in database
     *
     * @return List of tests
     */
    List<Subject> getSubjects();

    /**
     * Save a {@link Subject} to database
     *
     * @param subject {@link Subject} to save
     */
    void addSubject(Subject subject);

    /**
     * Check if a {@link Subject} with a particular name is already stored in database
     *
     * @param name of {@link Subject}
     * @return
     */
    boolean subjectExists(String name);

    /**
     * Delete {@link Subject}s from database
     *
     * @param subjectIds {@link List} of ids of {@link Subject}s to delete
     */
    void deleteSubjects(List<Integer> subjectIds);
}
