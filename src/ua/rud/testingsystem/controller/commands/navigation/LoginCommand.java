package ua.rud.testingsystem.controller.commands.navigation;

import ua.rud.testingsystem.controller.Command;
import ua.rud.testingsystem.controller.RequestWrapper;
import ua.rud.testingsystem.resource.ConfigurationManager;

import javax.servlet.ServletException;

public class LoginCommand implements Command {
    @Override
    public String execute(RequestWrapper wrapper) throws ServletException {
        return ConfigurationManager.getProperty("path.page.login");
    }
}