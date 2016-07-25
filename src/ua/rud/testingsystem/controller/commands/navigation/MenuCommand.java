package ua.rud.testingsystem.controller.commands.navigation;

import ua.rud.testingsystem.controller.Command;
import ua.rud.testingsystem.controller.RequestWrapper;
import ua.rud.testingsystem.entities.user.User;
import ua.rud.testingsystem.entities.SubjectUtils;
import ua.rud.testingsystem.entities.Subject;
import ua.rud.testingsystem.entities.test.TestUtils;
import ua.rud.testingsystem.resource.ConfigurationManager;

import javax.servlet.ServletException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MenuCommand implements Command {
    @Override
    public String execute(RequestWrapper wrapper) throws ServletException {
        /*Extract list of subjects from database*/
        List<Subject> subjects = SubjectUtils.getSubjects();

        /*Extract user*/
        Object objUser = wrapper.getSessionAttribute("user");
        int userId = -1;
        if (objUser != null && objUser instanceof User) {
            User user = (User) objUser;
            userId = user.getId();
        }

        /*Extract results for every test*/
        Map<Integer, List<Integer>> resultMap = new HashMap<>();
        for (Subject subject : subjects) {
            for (int testId : subject.getTests().keySet()) {
                List<Integer> results = TestUtils.getResults(userId, testId);
                resultMap.put(testId, results);
            }
        }

        /*Put extracted subjects and results to session*/
        wrapper.setSessionAttribute("subjects", subjects);
        wrapper.setSessionAttribute("results", resultMap);

        /*Return menu page*/
        return ConfigurationManager.getProperty("path.page.menu");
    }
}
