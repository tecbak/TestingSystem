package ua.rud.testingsystem.controller.commands;

import ua.rud.testingsystem.controller.Command;
import ua.rud.testingsystem.controller.RequestWrapper;
import ua.rud.testingsystem.entities.test.TestUtils;
import ua.rud.testingsystem.entities.test.Test;
import ua.rud.testingsystem.managers.PageManager;

import javax.servlet.ServletException;

/**
 * Command to start a test
 */
public class UserStartCommand implements Command {
    @Override
    public String execute(RequestWrapper wrapper) throws ServletException {
        String requestParameter = wrapper.getRequestParameter("id");

        /*Check if "id" parameter exists*/
        if (requestParameter != null && !requestParameter.isEmpty()) {
            int id = Integer.parseInt(requestParameter);

            /*Get test from database and add it to session*/
            Test test = TestUtils.getTest(id);
            wrapper.setSessionAttribute("test", test);
            return PageManager.getProperty("path.page.test");
        } else {
            return PageManager.getProperty("path.page.menu");
        }
    }
}
