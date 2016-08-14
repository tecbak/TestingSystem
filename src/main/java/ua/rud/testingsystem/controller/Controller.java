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
         * Assign a value of "error" jsp's path if command is null
         * or CSRF unsafe command hasn't pass verification of tokens.
         * Otherwise, execute command and assign a value returned by command.
         */
        String page;
        if (command == null || (command instanceof CsrfUnsafe && !isCsrfVerified(wrapper))) {
            page = PageManager.getProperty("path.page.error");
        } else {
            page = command.execute(wrapper);
        }

        /* Save path of jsp to session's attribute "page" */
        request.getSession().setAttribute("page", page);

        /* Forward to frame.jsp. It will include jsp from session's attribute "page" */
        RequestDispatcher dispatcher = request.getRequestDispatcher(frame);
        dispatcher.forward(request, response);
    }

    /**
     * Verify whether token received from client matches the one stored at server
     *
     * @param wrapper contains client's request
     * @return {@code true} if tokens are the same and {@code false} otherwise
     */
    private boolean isCsrfVerified(RequestWrapper wrapper) {
        String receivedToken = wrapper.getRequestParameter("token");
        String expectedToken = wrapper.getSessionAttribute("token") == null ?
                "" : wrapper.getSessionAttribute("token").toString();
        return expectedToken.equals(receivedToken);
    }
}


