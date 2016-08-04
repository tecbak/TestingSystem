package ua.rud.testingsystem.controller.commands;

import ua.rud.testingsystem.controller.Command;
import ua.rud.testingsystem.controller.RequestWrapper;
import ua.rud.testingsystem.entities.subject.Subject;
import ua.rud.testingsystem.entities.subject.SubjectUtils;
import ua.rud.testingsystem.entities.user.User;
import ua.rud.testingsystem.resource.PageManager;

import javax.servlet.ServletException;
import java.util.List;
import java.util.Map;

/**
 * Command to enter to main menu
 */
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
        Map<Integer, List<Integer>> resultMap = SubjectUtils.getResultsForSubjects(subjects, userId);
//        for (Subject subject : subjects) {
//            resultMap = SubjectUtils.getResultsForSubject(subject, userId);

//            for (int testId : subject.getTests().keySet()) {
//                List<Integer> results = TestUtils.getResults(userId, testId);
//                resultMap.put(testId, results);
//            }
//        }

        /*Put extracted subjects and results to session*/
        wrapper.setSessionAttribute("subjects", subjects);
        wrapper.setSessionAttribute("results", resultMap);

        /*Return menu page*/
        return PageManager.getProperty("path.page.menu");
    }
}
