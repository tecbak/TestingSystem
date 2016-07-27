package ua.rud.testingsystem.controller.commands;

import ua.rud.testingsystem.controller.Command;
import ua.rud.testingsystem.controller.RequestWrapper;
import ua.rud.testingsystem.entities.test.Answer;
import ua.rud.testingsystem.entities.test.Question;
import ua.rud.testingsystem.entities.test.Test;
import ua.rud.testingsystem.entities.test.TestUtils;
import ua.rud.testingsystem.resource.ConfigurationManager;
import ua.rud.testingsystem.resource.MessageManager;

import javax.servlet.ServletException;
import java.util.List;
import java.util.Locale;

public class AdminAddQuestionCommand implements Command {
    @Override
    public String execute(RequestWrapper wrapper) throws ServletException {
        Object newTestObj = wrapper.getSessionAttribute("newTest");

        /*Check if there is a new test in the session*/
        if (newTestObj != null && newTestObj instanceof Test) {
            Test newTest = (Test) newTestObj;

            String task = wrapper.getRequestParameter("task");
            String[] texts = wrapper.getRequestParameterValues("text");
            String[] answerIds = wrapper.getRequestParameterValues("answerId");

            /*Check if task is entered*/
            if (task == null || task.isEmpty()) {
                Locale locale = wrapper.getSessionLanguage();
                String message = MessageManager.getProperty("editTests.emptyTask", locale);
                wrapper.setRequestAttribute("addQuestionMessage", message);

            /*Check if at least one answer is entered*/
            } else if (texts == null || TestUtils.allEmpty(texts)) {
                Locale locale = wrapper.getSessionLanguage();
                String message = MessageManager.getProperty("editTests.emptyAnswers", locale);
                wrapper.setRequestAttribute("addQuestionMessage", message);

            /*Check if at least one right answer*/
            }else if(answerIds == null || TestUtils.allEmpty(answerIds)){
                Locale locale = wrapper.getSessionLanguage();
                String message = MessageManager.getProperty("editTests.emptyRightAnswers", locale);
                wrapper.setRequestAttribute("addQuestionMessage", message);

            /*If everything's OK - create a new question and add to the new test*/
            } else {
                Question question = TestUtils.getNewQuestion(task);
                List<Answer> answers = TestUtils.getNewAnswers(texts, answerIds);
                question.setAnswers(answers);
                newTest.addQuestion(question);
            }
        }

        return ConfigurationManager.getProperty("path.page.editTests");
    }

}
