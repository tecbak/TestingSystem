package ua.rud.testingsystem.controller.commands;

import ua.rud.testingsystem.controller.Command;
import ua.rud.testingsystem.controller.RequestWrapper;
import ua.rud.testingsystem.entities.subject.Subject;
import ua.rud.testingsystem.entities.subject.SubjectUtils;
import ua.rud.testingsystem.entities.user.User;
import ua.rud.testingsystem.entities.user.UserUtils;
import ua.rud.testingsystem.managers.MessageManager;
import ua.rud.testingsystem.managers.PageManager;

import javax.servlet.ServletException;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import static ua.rud.testingsystem.entities.CommonUtils.getRandomString;

/**
 * Command to sign in
 */
public class GuestAuthorizationCommand implements Command {

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
            return PageManager.getProperty("path.page.login");

        } else {

            /*Extract list of subjects from database*/
            List<Subject> subjects = SubjectUtils.getSubjects();

            /*Extract results*/
            Map<Integer, List<Integer>> resultMap = SubjectUtils.getResultsForSubjects(subjects, user.getId());

            /*Put extracted user, subjects and results to session*/
            wrapper.setSessionAttribute("user", user);
            wrapper.setSessionAttribute("subjects", subjects);
            wrapper.setSessionAttribute("results", resultMap);
            wrapper.setSessionAttribute("token", getRandomString(32));

            /*Return menu page*/
            return PageManager.getProperty("path.page.menu");
        }
    }
}
