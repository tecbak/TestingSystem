package ua.rud.testingsystem.controller.commands.navigation;

import ua.rud.testingsystem.controller.Command;
import ua.rud.testingsystem.controller.RequestWrapper;
import ua.rud.testingsystem.entities.utils.SubjectUtils;
import ua.rud.testingsystem.entities.sbj.Subject;
import ua.rud.testingsystem.resource.ConfigurationManager;

import javax.servlet.ServletException;
import java.util.List;

public class MenuCommand implements Command {
    @Override
    public String execute(RequestWrapper wrapper) throws ServletException {
        /*Extract list of subjects from database*/
        List<Subject> subjects = SubjectUtils.loadSubjects();

        /*Put extracted user and subjects to session*/
        wrapper.setSessionAttribute("subjects", subjects);

        /*Return menu page*/
        return ConfigurationManager.getProperty("path.page.menu");
    }
}
