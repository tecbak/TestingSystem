package ua.rud.testingsystem.controller;

import javax.servlet.ServletException;

public interface Command {
    String execute(RequestWrapper wrapper) throws ServletException;
}
