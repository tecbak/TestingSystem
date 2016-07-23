package ua.rud.testingsystem.controller.commands;

import ua.rud.testingsystem.controller.Command;
import ua.rud.testingsystem.controller.RequestWrapper;

import javax.servlet.ServletException;

public class StartCommand implements Command {
    @Override
    public String execute(RequestWrapper request) throws ServletException {
        return null;
    }
}
