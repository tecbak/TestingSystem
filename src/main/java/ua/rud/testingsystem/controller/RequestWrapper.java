package ua.rud.testingsystem.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Locale;

/**
 * Provides access to {@link HttpServletRequest}'s
 * parameters and attributes as well as session's attributes
 */
public interface RequestWrapper {
    /**
     * Extract parameter from {@link HttpServletRequest}
     *
     * @param s parameter's name
     * @return parameter's  value or {@code null} of such a parameter doesn't exist
     */
    String getRequestParameter(String s);

    /**
     * Extract attribute from {@link HttpServletRequest}
     *
     * @param s attribute's name
     * @return attribute's  value or {@code null} of such an attribute doesn't exist
     */
    Object getRequestAttribute(String s);

    /**
     * Set attribute to {@link HttpServletRequest}
     *
     * @param s attribute's name
     * @param o attribute's value
     */
    void setRequestAttribute(String s, Object o);

    /**
     * Extract attribute from {@link HttpSession}
     *
     * @param s attribute's name
     * @return attribute's  value or {@code null} of such an attribute doesn't exist
     */
    Object getSessionAttribute(String s);

    /**
     * Set attribute to {@link HttpSession}
     *
     * @param s attribute's name
     * @param o attribute's value
     */
    void setSessionAttribute(String s, Object o);

    /**
     * Extract a language data, stored in {@link HttpSession}
     *
     * @return {@link Locale} of {@link HttpSession}
     */
    Locale getSessionLanguage();

    /**
     * Extract a values of {@link HttpServletRequest}'s parameter
     *
     * @param s parameter's name
     * @return parameter's values
     */
    String[] getRequestParameterValues(String s);
}
