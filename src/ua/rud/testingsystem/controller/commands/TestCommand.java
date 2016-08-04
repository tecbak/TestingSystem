package ua.rud.testingsystem.controller.commands;

import ua.rud.testingsystem.controller.Command;
import ua.rud.testingsystem.controller.RequestWrapper;
import ua.rud.testingsystem.entities.test.Test;
import ua.rud.testingsystem.entities.user.User;
import ua.rud.testingsystem.resource.PageManager;

import javax.servlet.ServletException;

/**
 * Command to start a test
 */
public class TestCommand implements Command {
    @Override
    public String execute(RequestWrapper wrapper) throws ServletException {
        Object test = wrapper.getSessionAttribute("test");
        Object user = wrapper.getSessionAttribute("user");

        /*
         * If user isn't authorized - return login page
         * If test's selected - return menu page
         * Otherwise - return test page
         */
        if (user == null || !(user instanceof User)) {
            return PageManager.getProperty("path.page.login");
        } else if (test == null || !(test instanceof Test)) {
            return PageManager.getProperty("path.page.menu");
        } else {
            return PageManager.getProperty("path.page.test");
        }
    }
}

