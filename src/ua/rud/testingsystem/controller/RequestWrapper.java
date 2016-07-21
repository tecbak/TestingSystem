package ua.rud.testingsystem.controller;

/**
 * Provides access to request's parameters and attributes
 * as well as session's attributes
 */
public interface RequestWrapper {
    String getRequestParameter(String s);

    Object getRequestAttribute(String s);

    void setRequestAttribute(String s, Object o);

    Object getSessionAttribute(String s);

    void setSessionAttribute(String s, Object o);
}
