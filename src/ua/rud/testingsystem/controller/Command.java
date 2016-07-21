package ua.rud.testingsystem.controller;

import javax.servlet.ServletException;

public interface Command {
    String execute(RequestWrapper request) throws ServletException;
}
