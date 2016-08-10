package ua.rud.testingsystem.entities.test;

import org.junit.Assert;

import java.util.ArrayList;
import java.util.List;

public class TestUtilsTest {
    @org.junit.Test
    public void createNewAnswersList() throws Exception {
        String[] texts = new String[]{"ABC", "XYZ"};
        String[] answerIds = new String[]{"1"};
        List<Answer> expected = new ArrayList<Answer>() {{
            Answer answer = new Answer();
            answer.setText("ABC");
            answer.setCorrect(false);
            add(answer);

            answer = new Answer();
            answer.setText("XYZ");
            answer.setCorrect(true);
            add(answer);
        }};

        List<Answer> actual = TestUtils.createAnswers(texts, answerIds);

        Assert.assertTrue(actual.containsAll(expected) && expected.containsAll(actual));
    }
}