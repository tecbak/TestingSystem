package ua.rud.testingsystem.controller.commands;

import ua.rud.testingsystem.controller.Command;
import ua.rud.testingsystem.controller.RequestWrapper;
import ua.rud.testingsystem.dao.AuthorizationDAO;
import ua.rud.testingsystem.dao.DAOFactory;
import ua.rud.testingsystem.model.user.User;
import ua.rud.testingsystem.resource.ConfigurationManager;
import ua.rud.testingsystem.resource.MessageManager;

import javax.servlet.ServletException;

public class AuthorizationCommand implements Command {
    @Override
    public String execute(RequestWrapper request) throws ServletException {
        String login = request.getRequestParameter("login");
        String password = request.getRequestParameter("password");

        DAOFactory factory = DAOFactory.getInstance();
        AuthorizationDAO dao = factory.getAuthorizationDAO();
        User user = dao.getUser(login, password);

        request.setSessionAttribute("user", user);

        String page;

        if (user != null) {
            page = ConfigurationManager.getProperty("path.page.menu");
        } else {
            request.setRequestAttribute("errorLoginPassMessage", MessageManager.getProperty("login.error"));
            page = ConfigurationManager.getProperty("path.page.login");
        }

        return page;
    }
}
