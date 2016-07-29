package ua.rud.testingsystem.controller;

import java.util.Locale;

/**
 * Provides access to {@link javax.servlet.http.HttpServletRequest}'s
 * parameters and attributes as well as session's attributes
 */
public interface RequestWrapper {
    /**
     * Extract parameter from {@link javax.servlet.http.HttpServletRequest}
     *
     * @param s parameter's name
     * @return parameter's  value or {@code null} of such a parameter doesn't exist
     */
    String getRequestParameter(String s);

    /**
     * Extract attribute from {@link javax.servlet.http.HttpServletRequest}
     *
     * @param s attribute's name
     * @return attribute's  value or {@code null} of such an attribute doesn't exist
     */
    Object getRequestAttribute(String s);

    /**
     * Set attribute to {@link javax.servlet.http.HttpServletRequest}
     *
     * @param s attribute's name
     * @param o attribute's value
     */
    void setRequestAttribute(String s, Object o);

    /**
     * Extract attribute from {@link javax.servlet.http.HttpSession}
     *
     * @param s attribute's name
     * @return attribute's  value or {@code null} of such an attribute doesn't exist
     */
    Object getSessionAttribute(String s);

    /**
     * Set attribute to {@link javax.servlet.http.HttpSession}
     *
     * @param s attribute's name
     * @param o attribute's value
     */
    void setSessionAttribute(String s, Object o);

    /**
     * Extract a language data, stored in {@link javax.servlet.http.HttpSession}
     *
     * @return {@link Locale} of {@link javax.servlet.http.HttpSession}
     */
    Locale getSessionLanguage();

    /**
     * Extract a values of {@link javax.servlet.http.HttpServletRequest}'s parameter
     *
     * @param s parameter's name
     * @return paramter's values
     */
    String[] getRequestParameterValues(String s);
}
