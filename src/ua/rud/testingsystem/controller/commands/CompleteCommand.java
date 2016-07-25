package ua.rud.testingsystem.controller.commands;

import ua.rud.testingsystem.controller.Command;
import ua.rud.testingsystem.controller.RequestWrapper;
import ua.rud.testingsystem.entities.test.TestUtils;
import ua.rud.testingsystem.entities.test.Test;
import ua.rud.testingsystem.entities.user.User;
import ua.rud.testingsystem.resource.ConfigurationManager;

import javax.servlet.ServletException;

public class CompleteCommand implements Command {
    @Override
    public String execute(RequestWrapper wrapper) throws ServletException {
        Object testObj = wrapper.getSessionAttribute("test");
        String[] answerIds = wrapper.getRequestParameterValues("id");

        if (testObj != null && testObj instanceof Test) {
            if (answerIds != null) {
                Test test = (Test) testObj;
                TestUtils.applyAnswers(test, answerIds);

                Object userObj = wrapper.getSessionAttribute("user");
                if (userObj != null && userObj instanceof User) {
                    User user = (User) userObj;
                    TestUtils.addResult(user, test);
                }
            }
            return ConfigurationManager.getProperty("path.page.test");
        } else {
            return ConfigurationManager.getProperty("path.page.menu");
        }

    }
}
