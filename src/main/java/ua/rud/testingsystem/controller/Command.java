package ua.rud.testingsystem.controller;

import ua.rud.testingsystem.controller.RequestWrapper;

import javax.servlet.ServletException;

/**
 * User's command
 */
public interface Command {
    String execute(RequestWrapper wrapper) throws ServletException;
}
