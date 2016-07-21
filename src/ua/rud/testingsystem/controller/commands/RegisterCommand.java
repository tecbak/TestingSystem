package ua.rud.testingsystem.controller.commands;

import ua.rud.testingsystem.controller.Command;
import ua.rud.testingsystem.controller.RequestWrapper;
import ua.rud.testingsystem.resource.ConfigurationManager;

import javax.servlet.ServletException;

public class RegisterCommand implements Command{

    @Override
    public String execute(RequestWrapper request) throws ServletException {
        return ConfigurationManager.getProperty("path.page.register");


    }
}
