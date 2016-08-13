package ua.rud.testingsystem.controller.commands;

import ua.rud.testingsystem.controller.Command;
import ua.rud.testingsystem.controller.CsrfUnsafe;
import ua.rud.testingsystem.controller.RequestWrapper;
import ua.rud.testingsystem.entities.test.TestUtils;
import ua.rud.testingsystem.entities.test.Test;
import ua.rud.testingsystem.entities.user.User;
import ua.rud.testingsystem.managers.PageManager;

import javax.servlet.ServletException;

/**
 * Command to submit answers for a test
 */
public class UserCompleteCommand implements Command, CsrfUnsafe {
    @Override
    public String execute(RequestWrapper wrapper) throws ServletException {
        Object testObj = wrapper.getSessionAttribute("test");
        String[] answerIds = wrapper.getRequestParameterValues("id");

        /*Check if any test is selected*/
        if (testObj != null && testObj instanceof Test) {

            /*Check if answers aren't empty*/
            if (answerIds != null) {
                Test test = (Test) testObj;

                TestUtils.applyAnswers(test, answerIds);

                /*Save results if user is authorized*/
                Object userObj = wrapper.getSessionAttribute("user");
                if (userObj != null && userObj instanceof User) {
                    User user = (User) userObj;
                    TestUtils.addResult(user, test);
                }
            }
            return PageManager.getProperty("path.page.test");
        } else {
            return PageManager.getProperty("path.page.menu");
        }

    }
}
