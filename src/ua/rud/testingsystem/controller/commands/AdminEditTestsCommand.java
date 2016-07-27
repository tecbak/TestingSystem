package ua.rud.testingsystem.controller.commands;

import ua.rud.testingsystem.controller.Command;
import ua.rud.testingsystem.controller.RequestWrapper;
import ua.rud.testingsystem.entities.Subject;
import ua.rud.testingsystem.entities.SubjectUtils;
import ua.rud.testingsystem.resource.ConfigurationManager;

import javax.servlet.ServletException;
import java.util.List;

public class AdminEditTestsCommand implements Command {
    @Override
    public String execute(RequestWrapper wrapper) throws ServletException {
        /*Extract list of subjects from database*/
        List<Subject> subjects = SubjectUtils.getSubjects();

        /*Put extracted subjects and results to session*/
        wrapper.setSessionAttribute("subjects", subjects);


        return ConfigurationManager.getProperty("path.page.editTests");
    }
}
