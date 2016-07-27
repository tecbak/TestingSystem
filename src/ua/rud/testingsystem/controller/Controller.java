package ua.rud.testingsystem.controller;

import ua.rud.testingsystem.resource.ConfigurationManager;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class Controller extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }

    private void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpServletRequestWrapper requestWrapper = new HttpServletRequestWrapper(request);

        CommandFactory factory = CommandFactory.getInstance();
        Command command = factory.getCommand(requestWrapper);

        /*
         * Execute command or redirect to error page in case of command is {@code null}
         */
        String page;
        if (command != null) {
            page = command.execute(requestWrapper);
        } else {
            page = ConfigurationManager.getProperty("path.page.error");
        }

        RequestDispatcher dispatcher = request.getRequestDispatcher(page);
        dispatcher.forward(request, response);
    }
}
