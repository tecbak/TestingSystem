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

        HttpServletRequestWrapper wrapper = new HttpServletRequestWrapper(request);

        CommandFactory factory = CommandFactory.getInstance();
        Command command = factory.getCommand(wrapper);

        /*
         * Execute command or redirect to error page in case of command is null
         */
        String page;
        if (command == null || (command instanceof CsrfUnsafe && !isCsrfVerified(wrapper))) {
            page = PageManager.getProperty("path.page.error");
        } else {
            page = command.execute(wrapper);
        }

//        if (command != null) {
//            page = command.execute(wrapper);
//        } else {
//            page = PageManager.getProperty("path.page.error");
//        }
        request.getSession().setAttribute("page", page);

        RequestDispatcher dispatcher = request.getRequestDispatcher(frame);
        dispatcher.forward(request, response);
    }

    private boolean isCsrfVerified(RequestWrapper wrapper) {
        String receivedToken = wrapper.getRequestParameter("token");
        String expectedToken = wrapper.getSessionAttribute("token") == null ?
                "" : wrapper.getSessionAttribute("token").toString();

        /*Continue if received token matches received one*/
        return expectedToken.equals(receivedToken);

//        String receivedToken = httpRequest.getParameter("token");
//        String expectedToken = session.getAttribute("token") == null ? "" : session.getAttribute("token").toString();

//        if (expectedToken.equals(receivedToken)) {
////            session.setAttribute("token", getRandomLong());
//            chain.doFilter(request, response);
//        } else {
//            RequestDispatcher dispatcher = request.getRequestDispatcher(errorHandlerJsp);
//            dispatcher.forward(request, response);
//        }
    }
}


