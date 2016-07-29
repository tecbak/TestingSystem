package ua.rud.testingsystem.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Locale;

/**
 * Encapsulates {@link HttpServletRequest}
 * and provides access to its parameters and attributes
 * as well as {@link javax.servlet.http.HttpSession}'s attributes
 */
public class HttpServletRequestWrapper implements RequestWrapper {
    private HttpServletRequest request;
    private HttpSession session;

    /*Constructor*/
    public HttpServletRequestWrapper(HttpServletRequest request) {
        this.request = request;
        this.session = request.getSession();
    }

    /*Methods*/
    @Override
    public String getRequestParameter(String s) {
        return request.getParameter(s);
    }

    @Override
    public Object getRequestAttribute(String s) {
        return request.getAttribute(s);
    }

    @Override
    public void setRequestAttribute(String s, Object o) {
        request.setAttribute(s, o);
    }

    @Override
    public Object getSessionAttribute(String s) {
        return session.getAttribute(s);
    }

    @Override
    public void setSessionAttribute(String s, Object o) {
        session.setAttribute(s, o);
    }

    @Override
    public Locale getSessionLanguage() {
        Object language = session.getAttribute("language");

        if (language != null && language instanceof Locale) {
            return (Locale) language;
        } else {
            return new Locale("en");
        }
    }

    @Override
    public String[] getRequestParameterValues(String s) {
        return request.getParameterValues(s);
    }
}
