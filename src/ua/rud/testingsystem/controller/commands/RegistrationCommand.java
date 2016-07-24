package ua.rud.testingsystem.controller.commands;

import ua.rud.testingsystem.controller.Command;
import ua.rud.testingsystem.controller.RequestWrapper;
import ua.rud.testingsystem.entities.utils.UserUtils;
import ua.rud.testingsystem.entities.user.User;
import ua.rud.testingsystem.resource.ConfigurationManager;
import ua.rud.testingsystem.resource.MessageManager;

import javax.servlet.ServletException;
import java.util.Locale;

public class RegistrationCommand implements Command {

    @Override
    public String execute(RequestWrapper wrapper) throws ServletException {
        /*Get data from request*/
        String login = wrapper.getRequestParameter("login");
        String password0 = wrapper.getRequestParameter("password0");
        String password1 = wrapper.getRequestParameter("password1");
        String firstName = wrapper.getRequestParameter("firstName");
        String lastName = wrapper.getRequestParameter("lastName");
        String email = wrapper.getRequestParameter("email");
        Locale locale = wrapper.getSessionLanguage();

        /*Message of result*/
        String message;

        /*Check if all fields are filed*/
        if (!UserUtils.isFilled(login, password0, password1, firstName, lastName, email)) {
            message = MessageManager.getProperty("register.emptyFields", locale);

            /*Check if passwords are equal*/
        } else if (!UserUtils.isPasswordsMatch(password0, password1)) {
            message = MessageManager.getProperty("register.passwordsMismatch", locale);

            /*Check if login doesn't already exist*/
        } else if (!UserUtils.isLoginUnique(login)) {
            message = MessageManager.getProperty("register.loginExists", locale);

            /*Check if email doesn't already exist*/
        } else if (!UserUtils.isEmailUnique(email)) {
            message = MessageManager.getProperty("register.emailExists", locale);

            /*Check if email is valid*/
        } else if (!UserUtils.isEmailValid(email)) {
            message = MessageManager.getProperty("register.emailInvalid", locale);

            /*If everything's OK*/
        } else {

            /*Creating new user and registration in database*/
            User user = new User(login, firstName, lastName, email);
            UserUtils.saveUser(user, password0);

            message = MessageManager.getProperty("register.success", locale);
        }

        /*Set message of registration result*/
        wrapper.setRequestAttribute("registrationMessage", message);

        /*Return registration page again*/
        return ConfigurationManager.getProperty("path.page.register");
    }
}
