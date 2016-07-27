package ua.rud.testingsystem.controller.commands;

import ua.rud.testingsystem.controller.Command;
import ua.rud.testingsystem.controller.RequestWrapper;
import ua.rud.testingsystem.entities.Subject;
import ua.rud.testingsystem.entities.SubjectUtils;
import ua.rud.testingsystem.entities.test.Test;
import ua.rud.testingsystem.entities.test.TestUtils;
import ua.rud.testingsystem.resource.ConfigurationManager;
import ua.rud.testingsystem.resource.MessageManager;

import javax.servlet.ServletException;
import java.util.List;
import java.util.Locale;

public class AdminSaveTestCommand implements Command {
    @Override
    public String execute(RequestWrapper wrapper) throws ServletException {
        String save = wrapper.getRequestParameter("save");

        /*
         * Save only of parameter "save" is equal to 1,
         * Otherwise, this is "cancel" operation
         */
        if (save.equals("1")) {
            Object newTestObj = wrapper.getSessionAttribute("newTest");
            Object subjectIdObj = wrapper.getSessionAttribute("subjectId");

            /*Check if there is whole required  data*/
            if (newTestObj != null && newTestObj instanceof Test &&
                    subjectIdObj != null && subjectIdObj instanceof String) {
                Test newTest = (Test) newTestObj;
                int subjectId = Integer.parseInt((String) subjectIdObj);

                /*Save only if there is at least one question*/
                if (newTest.getQuestions().size() > 0) {

                    /*Save to DB*/
                    TestUtils.addTest(subjectId, newTest);

                    /*Update list of subjects*/
                    List<Subject> subjects = SubjectUtils.getSubjects();
                    wrapper.setSessionAttribute("subjects", subjects);

                    /*Delete link to created test from session*/
                    wrapper.setSessionAttribute("newTest", null);
                    wrapper.setSessionAttribute("subjectId", null);
                } else {

                    /*Show message*/
                    Locale locale = wrapper.getSessionLanguage();
                    String message = MessageManager.getProperty("editTests.noQuestions", locale);
                    wrapper.setRequestAttribute("saveTestMessage", message);
                }
            }
        } else {

            /*Cancel creating new test*/
            wrapper.setSessionAttribute("newTest", null);
            wrapper.setSessionAttribute("subjectId", null);
        }

        return ConfigurationManager.getProperty("path.page.editTests");
    }
}
