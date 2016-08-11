package ua.rud.testingsystem.controller;

import org.apache.log4j.LogManager;
import org.apache.log4j.xml.DOMConfigurator;
import ua.rud.testingsystem.managers.PageManager;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Processes all http request's
 */
public class Controller extends HttpServlet {
    @Override
    public void init() throws ServletException {
        /*Initialization of logger*/
        new DOMConfigurator().doConfigure("log4j.xml", LogManager.getLoggerRepository());
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }

    private void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        final String frame = PageManager.getProperty("path.page.frame");

        System.out.println(request.getSession(false).toString()); // TODO: 11.08.2016 sout

//        if (request.getSession(false) == null) {
//
//            System.out.println("afsdgfsdfgsdgsgs");
//        }

        HttpServletRequestWrapper requestWrapper = new HttpServletRequestWrapper(request);

        CommandFactory factory = CommandFactory.getInstance();
        Command command = factory.getCommand(requestWrapper);

        /*
         * Execute command or redirect to error page in case of command is null
         */
        String page;
        if (command != null) {
            page = command.execute(requestWrapper);
        } else {
            page = PageManager.getProperty("path.page.error");
        }
        request.getSession().setAttribute("page", page);

        RequestDispatcher dispatcher = request.getRequestDispatcher(frame);
        dispatcher.forward(request, response);
    }
}
