package ua.rud.testingsystem.controller.commands;

import ua.rud.testingsystem.controller.Command;
import ua.rud.testingsystem.controller.RequestWrapper;
import ua.rud.testingsystem.entities.test.Test;
import ua.rud.testingsystem.entities.test.TestUtils;
import ua.rud.testingsystem.resource.ConfigurationManager;
import ua.rud.testingsystem.resource.MessageManager;

import javax.servlet.ServletException;
import java.util.Locale;

public class AdminAddTestCommand implements Command {

    @Override
    public String execute(RequestWrapper wrapper) throws ServletException {
        /*
         *Get parameters from request
         */
        String caption = wrapper.getRequestParameter("caption");
        String subjectId = wrapper.getRequestParameter("subjectId");
        Locale locale = wrapper.getSessionLanguage();

        if (caption == null || caption.isEmpty()) {
            String message = MessageManager.getProperty("editTests.emptyCaption", locale);
            wrapper.setRequestAttribute("addTestMessage", message);
        } else if (subjectId == null || subjectId.isEmpty()) {
            String message = MessageManager.getProperty("editTests.noSubject", locale);
            wrapper.setRequestAttribute("addTestMessage", message);
        } else {
            Test newTest = TestUtils.getNewTest(caption);
            wrapper.setSessionAttribute("newTest", newTest);
            wrapper.setSessionAttribute("subjectId", subjectId);
        }

        return ConfigurationManager.getProperty("path.page.editTests");
    }

}
