package ua.rud.testingsystem.controller.commands;

import ua.rud.testingsystem.controller.Command;
import ua.rud.testingsystem.controller.RequestWrapper;
import ua.rud.testingsystem.resource.PageManager;

import javax.servlet.ServletException;

/**
 * Command to logout
 */
public class LogoutCommand implements Command {

    @Override
    public String execute(RequestWrapper wrapper) throws ServletException {
        /*Invalidate user*/
        wrapper.setSessionAttribute("user", null);

        return PageManager.getProperty("path.page.login");
    }
}
