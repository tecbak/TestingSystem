package ua.rud.testingsystem.dao;

import ua.rud.testingsystem.entities.Subject;

import java.util.List;

public interface SubjectDao {
    List<Subject> getSubjects();

    void addSubject(Subject subject)  ;

    boolean subjectExists(String name);

    void deleteSubjects(List<Integer> subjectIds);
}
