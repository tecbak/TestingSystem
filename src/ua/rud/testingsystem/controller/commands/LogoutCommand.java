package ua.rud.testingsystem.controller.commands;

import ua.rud.testingsystem.controller.Command;
import ua.rud.testingsystem.controller.RequestWrapper;
import ua.rud.testingsystem.resource.ConfigurationManager;

import javax.servlet.ServletException;

public class LogoutCommand implements Command {

    @Override
    public String execute(RequestWrapper wrapper) throws ServletException {
        /*Invalidate user*/
        wrapper.setSessionAttribute("user", null);

        /*Return login page*/
        return ConfigurationManager.getProperty("path.page.login");
    }
}
