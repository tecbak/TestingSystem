package ua.rud.testingsystem.controller.commands;

import ua.rud.testingsystem.controller.Command;
import ua.rud.testingsystem.controller.RequestWrapper;
import ua.rud.testingsystem.entities.CommonUtils;
import ua.rud.testingsystem.entities.subject.Subject;
import ua.rud.testingsystem.entities.subject.SubjectUtils;
import ua.rud.testingsystem.entities.test.TestUtils;
import ua.rud.testingsystem.resource.PageManager;
import ua.rud.testingsystem.resource.MessageManager;

import javax.servlet.ServletException;
import java.util.List;
import java.util.Locale;

/**
 * Command to delete a test
 */
public class AdminDeleteTestCommand implements Command {
    @Override
    public String execute(RequestWrapper wrapper) throws ServletException {
        String[] testIds = wrapper.getRequestParameterValues("testId");

        /*Check if at least one test is selected*/
        if (testIds != null && !CommonUtils.allEmpty(testIds)) {
            TestUtils.deleteTests(testIds);

        /*Message about no selection */
        } else {
            Locale locale = wrapper.getSessionLanguage();
            String message = MessageManager.getProperty("editTests.noTestSelected", locale);
            wrapper.setRequestAttribute("deleteTestMessage", message);
        }

        /*Update list of subjects*/
        List<Subject> subjects = SubjectUtils.getSubjects();
        wrapper.setSessionAttribute("subjects", subjects);

        return PageManager.getProperty("path.page.editTests");
    }
}
