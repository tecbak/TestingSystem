package ua.rud.testingsystem.controller;

import javax.servlet.ServletException;

/**
 * User's command
 */
public interface Command {
    String execute(RequestWrapper wrapper) throws ServletException;
}
