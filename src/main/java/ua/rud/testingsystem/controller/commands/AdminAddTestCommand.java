package ua.rud.testingsystem.controller.commands;

import ua.rud.testingsystem.controller.Command;
import ua.rud.testingsystem.controller.CsrfUnsafe;
import ua.rud.testingsystem.controller.RequestWrapper;
import ua.rud.testingsystem.entities.test.Test;
import ua.rud.testingsystem.entities.test.TestUtils;
import ua.rud.testingsystem.managers.PageManager;
import ua.rud.testingsystem.managers.MessageManager;

import javax.servlet.ServletException;
import java.util.Locale;

/**
 * Command to start creating of new test
 */
public class AdminAddTestCommand implements Command, CsrfUnsafe {

    @Override
    public String execute(RequestWrapper wrapper) throws ServletException {

        /*Get parameters from request*/
        String caption = wrapper.getRequestParameter("caption");
        String subjectId = wrapper.getRequestParameter("subjectId");
        Locale locale = wrapper.getSessionLanguage();

        /*If no caption entered*/
        if (caption == null || caption.isEmpty()) {
            String message = MessageManager.getProperty("editTests.emptyCaption", locale);
            wrapper.setRequestAttribute("addTestMessage", message);
            return PageManager.getProperty("path.page.editTests");


            /*If no subject chosen*/
        } else if (subjectId == null || subjectId.isEmpty()) {
            String message = MessageManager.getProperty("editTests.noSubject", locale);
            wrapper.setRequestAttribute("addTestMessage", message);
            return PageManager.getProperty("path.page.editTests");

            /*Everything is ok - construct new test*/
        } else {
            Test newTest = TestUtils.getNewTest(caption);
            wrapper.setSessionAttribute("newTest", newTest);
            wrapper.setSessionAttribute("subjectId", subjectId);
            return PageManager.getProperty("path.page.addTest");
        }
    }
}
