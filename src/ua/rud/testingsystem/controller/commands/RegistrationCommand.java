package ua.rud.testingsystem.controller.commands;

import ua.rud.testingsystem.controller.Command;
import ua.rud.testingsystem.controller.RequestWrapper;
import ua.rud.testingsystem.logic.RegistrationLogic;
import ua.rud.testingsystem.model.user.User;
import ua.rud.testingsystem.resource.ConfigurationManager;
import ua.rud.testingsystem.resource.MessageManager;

import javax.servlet.ServletException;
import java.util.Locale;

public class RegistrationCommand implements Command {

    @Override
    public String execute(RequestWrapper request) throws ServletException {
        /*Get data from request*/
        String login = request.getRequestParameter("login");
        String password0 = request.getRequestParameter("password0");
        String password1 = request.getRequestParameter("password1");
        String firstName = request.getRequestParameter("firstName");
        String lastName = request.getRequestParameter("lastName");
        String email = request.getRequestParameter("email");
        Locale locale = request.getSessionLanguage();

        /*Message of result*/
        String message;

        /*Check if all fields are filed*/
        if (!RegistrationLogic.isFilled(login, password0, password1, firstName, lastName, email)) {
            message = MessageManager.getProperty("register.emptyFields", locale);

            /*Check if passwords are equal*/
        } else if (!RegistrationLogic.isPasswordsMatch(password0, password1)) {
            message = MessageManager.getProperty("register.passwordsMismatch", locale);

            /*Check if login doesn't already exist*/
        } else if (!RegistrationLogic.isLoginUnique(login)) {
            message = MessageManager.getProperty("register.loginExists", locale);

            /*Check if email doesn't already exist*/
        } else if (!RegistrationLogic.isEmailUnique(email)) {
            message = MessageManager.getProperty("register.emailExists", locale);

            /*Check if email is valid*/
        } else if (!RegistrationLogic.isEmailValid(email)) {
            message = MessageManager.getProperty("register.emailInvalid", locale);

            /*If everything's OK*/
        } else {

            /*Creating new user and registration in database*/
            User user = new User(login, firstName, lastName, email);
            RegistrationLogic.registerUser(user, password0);

            message = MessageManager.getProperty("register.success", locale);
        }

        /*Set message of registration result*/
        request.setRequestAttribute("registrationMessage", message);

        /*Return registration page again*/
        return ConfigurationManager.getProperty("path.page.register");
    }
}
