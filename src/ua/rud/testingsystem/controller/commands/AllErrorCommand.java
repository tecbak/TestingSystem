package ua.rud.testingsystem.controller.commands;

import ua.rud.testingsystem.controller.Command;
import ua.rud.testingsystem.controller.RequestWrapper;
import ua.rud.testingsystem.resource.PageManager;

import javax.servlet.ServletException;

/**
 * Command to enter to error page
 */
public class AllErrorCommand implements Command {
    @Override
    public String execute(RequestWrapper wrapper) throws ServletException {
        return PageManager.getProperty("path.page.error");
    }
}
