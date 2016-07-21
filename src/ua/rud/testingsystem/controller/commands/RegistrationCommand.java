package ua.rud.testingsystem.controller.commands;

import ua.rud.testingsystem.controller.Command;
import ua.rud.testingsystem.controller.RequestWrapper;
import ua.rud.testingsystem.dao.DAOFactory;
import ua.rud.testingsystem.dao.RegistrationDAO;
import ua.rud.testingsystem.exceptions.RegistrationException;
import ua.rud.testingsystem.model.user.User;
import ua.rud.testingsystem.resource.ConfigurationManager;
import ua.rud.testingsystem.resource.MessageManager;

import javax.servlet.ServletException;

public class RegistrationCommand implements Command {
    @Override
    public String execute(RequestWrapper request) throws ServletException {
        String login = request.getRequestParameter("login");
        String password0 = request.getRequestParameter("password0");
        String password1 = request.getRequestParameter("password1");
        String firstName = request.getRequestParameter("firstName");
        String lastName = request.getRequestParameter("lastName");
        String email = request.getRequestParameter("email");
        String message = MessageManager.getProperty("register.success");

        try {
            //Data validation
            checkFieldsFilled(login, password0, password1, firstName, lastName, email);
            checkPasswordsMatch(password0, password1);
            checkLoginUnique(login);
            checkEmailUnique(email);
            checkEmailValid(email);

            //Creating new user and registration in DB
            User user = new User(login, firstName, lastName, email);
            DAOFactory factory = DAOFactory.getInstance();
            RegistrationDAO dao = factory.getRegistrationDAO();
            dao.registerUser(user, password0);

        } catch (RegistrationException e) {
            message = e.getMessage();
        }

        request.setRequestAttribute("registrationMessage", message);
        return ConfigurationManager.getProperty("path.page.register");
//        DAOFactory factory = DAOFactory.getInstance();
//        RegistrationDAO dao = factory.getRegistrationDAO();
//
//        //Check if all fields are filled
//        if (isEmpty(login, password0, password1, firstName, lastName, email)) {
//            request.setAttribute("registrationMessage", MessageManager.getProperty("register.emptyFields"));
//            return ConfigurationManager.getProperty("path.page.register");
//        }
//
//        //Check if password and confirmation password are equal
//        if (!password0.equals(password1)) {
//            request.setAttribute("registrationMessage", MessageManager.getProperty("register.passwordsMismatch"));
//            return ConfigurationManager.getProperty("path.page.register");
//        }
//
//        //Check if login is unique
//        if (dao.loginExists(login)) {
//            request.setAttribute("registrationMessage", MessageManager.getProperty("register.loginExists"));
//            return ConfigurationManager.getProperty("path.page.register");
//        }
//
//        //Check if email is unique
//        if (dao.emailExists(email)) {
//            request.setAttribute("registrationMessage", MessageManager.getProperty("register.emailExists"));
//            return ConfigurationManager.getProperty("path.page.register");
//        }
//
//        //Check if email is valid
//        if (!isEmailValid(email)) {
//            request.setAttribute("registrationMessage", MessageManager.getProperty("register.emailInvalid"));
//            return ConfigurationManager.getProperty("path.page.register");
//        }
//
//
//
//        request.setAttribute("registrationMessage", MessageManager.getProperty("register.success"));
    }

    private void checkFieldsFilled(String... values) throws RegistrationException {
        for (String value : values) {
            if (value.isEmpty()) {
                String message = MessageManager.getProperty("register.emptyFields");
                throw new RegistrationException(message);
            }
        }
    }

    private void checkPasswordsMatch(String password0, String password1) throws RegistrationException {
        if (!password0.equals(password1)) {
            String message = MessageManager.getProperty("register.passwordsMismatch");
            throw new RegistrationException(message);
        }
    }

    private void checkLoginUnique(String login) throws RegistrationException {
        DAOFactory factory = DAOFactory.getInstance();
        RegistrationDAO dao = factory.getRegistrationDAO();
        if (dao.loginExists(login)) {
            String message = MessageManager.getProperty("register.loginExists");
            throw new RegistrationException(message);
        }
    }

    private void checkEmailUnique(String email) throws RegistrationException {
        DAOFactory factory = DAOFactory.getInstance();
        RegistrationDAO dao = factory.getRegistrationDAO();
        if (dao.emailExists(email)) {
            String message = MessageManager.getProperty("registrationEmailExists");
            throw new RegistrationException(message);
        }
    }

    private void checkEmailValid(String email) throws RegistrationException {
        final String EMAIL_PATTERN = "^[a-zA-Z0-9_!#$%&’*+/=?`{|}~^.-]+@[a-zA-Z0-9.-]+$";
        if (!email.matches(EMAIL_PATTERN)) {
            String message = MessageManager.getProperty("registrationEmailInvalid");
            throw new RegistrationException(message);
        }
    }
}
