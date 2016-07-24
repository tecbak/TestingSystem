package ua.rud.testingsystem.controller.commands;

import ua.rud.testingsystem.controller.Command;
import ua.rud.testingsystem.controller.RequestWrapper;
import ua.rud.testingsystem.entities.utils.SubjectUtils;
import ua.rud.testingsystem.entities.utils.UserUtils;
import ua.rud.testingsystem.entities.sbj.Subject;
import ua.rud.testingsystem.entities.user.User;
import ua.rud.testingsystem.resource.ConfigurationManager;
import ua.rud.testingsystem.resource.MessageManager;

import javax.servlet.ServletException;
import java.util.List;
import java.util.Locale;

public class AuthorizationCommand implements Command {

    @Override
    public String execute(RequestWrapper wrapper) throws ServletException {

        /*Get login and password from request*/
        String login = wrapper.getRequestParameter("login");
        String password = wrapper.getRequestParameter("password");

        /*Extract user from database*/
        User user = UserUtils.getUser(login, password);

        /*
         * If user is null - there's no such a login/password pair in database,
         * otherwise - authorization completed successfully
         */
        if (user == null) {

            /*Prepare login error message to show on login page*/
            Locale locale = wrapper.getSessionLanguage();
            String message = MessageManager.getProperty("login.error", locale);
            wrapper.setRequestAttribute("errorLoginPassMessage", message);

            /*Return login page again*/
            return ConfigurationManager.getProperty("path.page.login");

        } else {

            /*Extract list of subjects from database*/
            List<Subject> subjects = SubjectUtils.loadSubjects();

            /*Put extracted user and subjects to session*/
            wrapper.setSessionAttribute("user", user);
            wrapper.setSessionAttribute("subjects", subjects);

            /*Return menu page*/
            return ConfigurationManager.getProperty("path.page.menu");
        }
    }
}
