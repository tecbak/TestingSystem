package ua.rud.testingsystem.controller.commands;

import ua.rud.testingsystem.controller.Command;
import ua.rud.testingsystem.controller.RequestWrapper;
import ua.rud.testingsystem.entities.Subject;
import ua.rud.testingsystem.entities.SubjectUtils;
import ua.rud.testingsystem.entities.test.TestUtils;
import ua.rud.testingsystem.resource.PageManager;
import ua.rud.testingsystem.resource.MessageManager;

import javax.servlet.ServletException;
import java.util.List;
import java.util.Locale;
/**
 * Command to delete a subject
 */
public class AdminDeleteSubjectCommand implements Command {
    @Override
    public String execute(RequestWrapper wrapper) throws ServletException {
        String[] stringSubjectIds = wrapper.getRequestParameterValues("subjectId");

        /*Check if there is at least one selected subject*/
        if (stringSubjectIds != null && !TestUtils.allEmpty(stringSubjectIds)) {
            SubjectUtils.deleteSubjects(stringSubjectIds);

        /*Message about no selection */
        } else {
            Locale locale = wrapper.getSessionLanguage();
            String message = MessageManager.getProperty("editSubjects.noSubjectSelected", locale);
            wrapper.setRequestAttribute("deleteSubjectMessage", message);
        }

        /*Update list of subjects*/
        List<Subject> subjects = SubjectUtils.getSubjects();
        wrapper.setSessionAttribute("subjects", subjects);

        return PageManager.getProperty("path.page.editSubjects");
    }
}
