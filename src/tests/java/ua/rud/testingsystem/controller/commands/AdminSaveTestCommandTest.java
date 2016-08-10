package ua.rud.testingsystem.controller.commands;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mockito;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;
import ua.rud.testingsystem.controller.RequestWrapper;
import ua.rud.testingsystem.entities.subject.SubjectUtils;
import ua.rud.testingsystem.entities.test.TestUtils;
import ua.rud.testingsystem.managers.MessageManager;
import ua.rud.testingsystem.managers.PageManager;

import javax.servlet.ServletException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

@RunWith(PowerMockRunner.class)
@PrepareForTest({TestUtils.class, SubjectUtils.class})
public class AdminSaveTestCommandTest {
    private RequestWrapper wrapper = Mockito.mock(RequestWrapper.class);
    private ua.rud.testingsystem.entities.test.Test test = Mockito.mock(ua.rud.testingsystem.entities.test.Test.class);
    private List questions = Mockito.mock(ArrayList.class);

    @Before
    public void setUp() throws Exception {

        /*Mocks standard behaviour*/

//        test = new ua.rud.testingsystem.entities.test.Test() {{
//            addQuestion(new Question());
//        }};


        Mockito.when(test.getQuestions()).thenReturn(questions);
        Mockito.when(questions.size()).thenReturn(1);
        Mockito.when(wrapper.getRequestParameter("save")).thenReturn("1");
        Mockito.when(wrapper.getSessionAttribute("newTest")).thenReturn(test);
        Mockito.when(wrapper.getSessionAttribute("subjectId")).thenReturn("100");
        Mockito.doNothing().when(wrapper).setSessionAttribute(Mockito.any(), Mockito.any());
        Mockito.when(wrapper.getSessionLanguage()).thenReturn(Locale.ENGLISH);

        /*Mock static*/
        PowerMockito.mockStatic(TestUtils.class);
        PowerMockito.doNothing().when(TestUtils.class);
        TestUtils.addTest(Mockito.anyInt(), Mockito.any());

        PowerMockito.mockStatic(SubjectUtils.class);
        PowerMockito.doReturn(new ArrayList()).when(SubjectUtils.class);
        SubjectUtils.getSubjects();
    }

    @Test
    public void onSaveNotEqual1_Cancel() throws ServletException {
        Mockito.when(wrapper.getRequestParameter("save")).thenReturn("0");

        /*Verify the returned page*/
        String expectedPage = PageManager.getProperty("path.page.editTests");
        String actualPage = new AdminSaveTestCommand().execute(wrapper);
        Assert.assertEquals(expectedPage, actualPage);

        /*Verify creating is canceled*/
        ArgumentCaptor<String> s = ArgumentCaptor.forClass(String.class);
        ArgumentCaptor<Object> o = ArgumentCaptor.forClass(Object.class);

        Mockito.verify(wrapper, Mockito.times(2)).setSessionAttribute(s.capture(), o.capture());

        Assert.assertEquals("newTest", s.getAllValues().get(0));
        Assert.assertEquals("subjectId", s.getAllValues().get(1));
        Assert.assertNull(o.getAllValues().get(0));
        Assert.assertNull(o.getAllValues().get(1));


    }

    @Test
    public void onTestContainNoQuestions_reportAboutIt() throws ServletException {
        Mockito.when(questions.size()).thenReturn(0);

        /*Verify the returned page*/
        String expectedPage = PageManager.getProperty("path.page.editTests");
        String actualPage = new AdminSaveTestCommand().execute(wrapper);
        Assert.assertEquals(expectedPage, actualPage);

        /*Verify message is shown*/
        ArgumentCaptor<String> s = ArgumentCaptor.forClass(String.class);
        ArgumentCaptor<Object> o = ArgumentCaptor.forClass(Object.class);

        Mockito.verify(wrapper).setRequestAttribute(s.capture(), o.capture());
        Assert.assertEquals("saveTestMessage", s.getValue());

        String message = MessageManager.getProperty("editTests.noQuestions", Locale.ENGLISH);
        Assert.assertEquals(message, o.getValue());


    }


    @Test
    public void executeTest() throws ServletException {
        String expectedPage = PageManager.getProperty("path.page.editTests");
        String actualPage = new AdminSaveTestCommand().execute(wrapper);

        PowerMockito.verifyStatic();
        TestUtils.addTest(Mockito.anyInt(), Mockito.any());

        Assert.assertEquals(expectedPage, actualPage);

    }

}