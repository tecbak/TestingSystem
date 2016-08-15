package ua.rud.testingsystem.controller.commands;

import ua.rud.testingsystem.controller.Command;
import ua.rud.testingsystem.controller.RequestWrapper;
import ua.rud.testingsystem.entities.subject.Subject;
import ua.rud.testingsystem.entities.subject.SubjectUtils;
import ua.rud.testingsystem.managers.PageManager;

import javax.servlet.ServletException;
import java.util.List;

/**
 * Command to get to editing tests menu
 */
public class AdminEditTestsCommand implements Command {
    @Override
    public String execute(RequestWrapper wrapper) throws ServletException {

        /*Extract list of subjects from database*/
        List<Subject> subjects = SubjectUtils.getSubjects();

        /*Put extracted subjects and results to session*/
        wrapper.setSessionAttribute("subjects", subjects);

        return PageManager.getProperty("path.page.editTests");
    }
}
