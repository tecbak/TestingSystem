package ua.rud.testingsystem.controller.commands;

import ua.rud.testingsystem.controller.Command;
import ua.rud.testingsystem.controller.RequestWrapper;
import ua.rud.testingsystem.managers.PageManager;

import javax.servlet.ServletException;

/**
 * Refresh page
 */
public class AllRefreshCommand implements Command {
    @Override
    public String execute(RequestWrapper wrapper) throws ServletException {
        Object page = wrapper.getSessionAttribute("page");

        /*
         * Return error page if there is no current page
         * or current page otherwise
         */
        if (page == null || !(page instanceof String) || ((String) page).isEmpty()) {
            return PageManager.getProperty("path.page.error");
        } else {
            return (String) page;
        }
    }
}
