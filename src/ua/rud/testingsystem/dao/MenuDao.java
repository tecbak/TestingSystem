package ua.rud.testingsystem.dao;

import ua.rud.testingsystem.model.menu.Subject;

import java.util.List;

public interface MenuDao {
    List<Subject> getSubjects();
}
