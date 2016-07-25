package ua.rud.testingsystem.controller.commands;

import ua.rud.testingsystem.controller.Command;
import ua.rud.testingsystem.controller.RequestWrapper;
import ua.rud.testingsystem.entities.test.TestUtils;
import ua.rud.testingsystem.entities.test.Test;
import ua.rud.testingsystem.resource.ConfigurationManager;

import javax.servlet.ServletException;

public class StartCommand implements Command {
    @Override
    public String execute(RequestWrapper wrapper) throws ServletException {
        String requestParameter = wrapper.getRequestParameter("id");
        if (requestParameter != null && !requestParameter.isEmpty()) {
            int id = Integer.parseInt(requestParameter);
            Test test = TestUtils.getTest(id);
            wrapper.setSessionAttribute("test", test);
            return ConfigurationManager.getProperty("path.page.test");
        } else {
            return ConfigurationManager.getProperty("path.page.menu");
        }
    }
}
