package ua.rud.testingsystem.controller.commands;

import ua.rud.testingsystem.controller.Command;
import ua.rud.testingsystem.controller.RequestWrapper;
import ua.rud.testingsystem.managers.PageManager;

import javax.servlet.ServletException;

/**
 * Command to enter to registration page
 */
public class GuestRegisterCommand implements Command {

    @Override
    public String execute(RequestWrapper wrapper) throws ServletException {
        return PageManager.getProperty("path.page.register");
    }
}
