package ua.rud.testingsystem.controller.filters;

import ua.rud.testingsystem.managers.PageManager;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;

import static ua.rud.testingsystem.entities.CommonUtils.getRandomLong;

public class CsrfFilter implements Filter {
    private String errorHandlerJsp;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        errorHandlerJsp = PageManager.getProperty("path.page.errorHandler");
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpSession session = httpRequest.getSession();

        String command = httpRequest.getParameter("command");

        /*Continue without verifying tokens accordance if command is "guest" or "all"*/
        if (command.startsWith("guest") || command.startsWith("all")) {
            chain.doFilter(request, response);
            return;
        }

        String receivedToken = httpRequest.getParameter("token");
        String expectedToken = session.getAttribute("token") == null ? "" : session.getAttribute("token").toString();

        /*Continue if received token matches received one*/
        if (expectedToken.equals(receivedToken)) {
            session.setAttribute("token", getRandomLong());
            chain.doFilter(request, response);
        } else {
            RequestDispatcher dispatcher = request.getRequestDispatcher(errorHandlerJsp);
            dispatcher.forward(request, response);
        }
    }
}
