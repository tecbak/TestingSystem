package ua.rud.testingsystem.controller.commands;

import ua.rud.testingsystem.controller.Command;
import ua.rud.testingsystem.controller.RequestWrapper;
import ua.rud.testingsystem.resource.PageManager;

import javax.servlet.ServletException;

/**
 * Command to start a test
 */
public class TestCommand implements Command {
    @Override
    public String execute(RequestWrapper wrapper) throws ServletException {
        /*
         * If any test is selected - return test page,
         * If no test is selected and user is authorized - return menu page,
         * Otherwise - return login page
         */
        if (wrapper.getSessionAttribute("test") != null) {
            return PageManager.getProperty("path.page.test");
        } else if (wrapper.getSessionAttribute("user") != null) {
            return PageManager.getProperty("path.page.menu");
        } else {
            return PageManager.getProperty("path.page.login");
        }
    }
}

