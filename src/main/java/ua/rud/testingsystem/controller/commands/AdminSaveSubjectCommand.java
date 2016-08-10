package ua.rud.testingsystem.controller.commands;

import ua.rud.testingsystem.controller.Command;
import ua.rud.testingsystem.controller.RequestWrapper;
import ua.rud.testingsystem.entities.subject.Subject;
import ua.rud.testingsystem.entities.subject.SubjectUtils;
import ua.rud.testingsystem.managers.PageManager;
import ua.rud.testingsystem.managers.MessageManager;

import javax.servlet.ServletException;
import java.util.List;
import java.util.Locale;

/**
 * Command to save a a new subject
 */
public class AdminSaveSubjectCommand implements Command {
    @Override
    public String execute(RequestWrapper wrapper) throws ServletException {
        String name = wrapper.getRequestParameter("name");
        Locale locale = wrapper.getSessionLanguage();

        /*Check if subject's name is entered*/
        if (name == null || name.isEmpty()) {
            wrapper.setRequestAttribute("addSubjectMessage", MessageManager.getProperty("editSubjects.nameEmpty", locale));

            /*Check if such a subjects isn't already exists*/
        } else if (SubjectUtils.subjectExists(name)) {
            wrapper.setRequestAttribute("addSubjectMessage", MessageManager.getProperty("editSubjects.subjectExists", locale));

            /*Save subject if everything's ok*/
        } else {
            Subject subject = new Subject();
            subject.setName(name);
            SubjectUtils.addSubject(subject);
        }

        /*Update list of subjects*/
        List<Subject> subjects = SubjectUtils.getSubjects();
        wrapper.setSessionAttribute("subjects", subjects);

        return PageManager.getProperty("path.page.editSubjects");
    }
}
