package ua.rud.testingsystem.controller.commands;

import ua.rud.testingsystem.controller.Command;
import ua.rud.testingsystem.controller.RequestWrapper;
import ua.rud.testingsystem.entities.Subject;
import ua.rud.testingsystem.entities.SubjectUtils;
import ua.rud.testingsystem.resource.ConfigurationManager;
import ua.rud.testingsystem.resource.MessageManager;

import javax.servlet.ServletException;
import java.util.List;
import java.util.Locale;

public class AdminSaveSubjectCommand implements Command {
    @Override
    public String execute(RequestWrapper wrapper) throws ServletException {
        String name = wrapper.getRequestParameter("name");
        Locale locale = wrapper.getSessionLanguage();

        if (name == null || name.isEmpty()) {
            wrapper.setRequestAttribute("addSubjectMessage", MessageManager.getProperty("editSubjects.nameEmpty", locale));
        } else if (SubjectUtils.subjectExists(name)) {
            wrapper.setRequestAttribute("addSubjectMessage", MessageManager.getProperty("editSubjects.subjectExists", locale));
        } else {
            Subject subject = new Subject();
            subject.setName(name);
            SubjectUtils.addSubject(subject);
        }

        /*Extract list of subjects from database*/
        List<Subject> subjects = SubjectUtils.getSubjects();

        wrapper.setSessionAttribute("subjects", subjects);

        return ConfigurationManager.getProperty("path.page.editSubjects");
    }
}
