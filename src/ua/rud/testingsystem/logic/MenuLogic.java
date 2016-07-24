package ua.rud.testingsystem.logic;


import ua.rud.testingsystem.dao.DaoFactory;
import ua.rud.testingsystem.dao.MenuDao;
import ua.rud.testingsystem.dao.jdbc.JdbcFactory;
import ua.rud.testingsystem.model.menu.Subject;

import java.util.List;

public final class MenuLogic {

    private MenuLogic() {
    }

    public static List<Subject> loadSubjects() {
        DaoFactory factory = JdbcFactory.getInstance();
        MenuDao menuDao = factory.getMenuDao();
        List<Subject> subjects = menuDao.getSubjects();
        return subjects;
    }
}
