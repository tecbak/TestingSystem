package ua.rud.testingsystem.controller.commands;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;
import ua.rud.testingsystem.controller.RequestWrapper;
import ua.rud.testingsystem.entities.test.TestUtils;
import ua.rud.testingsystem.entities.user.User;
import ua.rud.testingsystem.managers.PageManager;

import javax.servlet.ServletException;

@RunWith(PowerMockRunner.class)
@PrepareForTest(TestUtils.class)
public class CompleteCommandTest {
    private RequestWrapper wrapper;


    @Before
    public void setUp()  {
        /*Wrapper standard behaviour*/
        wrapper = Mockito.mock(RequestWrapper.class);
        Mockito.when(wrapper.getSessionAttribute("test")).thenReturn(new ua.rud.testingsystem.entities.test.Test());
        Mockito.when(wrapper.getRequestParameterValues("id")).thenReturn(new String[]{"100"});
        Mockito.when(wrapper.getSessionAttribute("user")).thenReturn(new User());

        /*Do nothing on invoked TestUtils's methods */
        PowerMockito.spy(TestUtils.class);
        PowerMockito.doNothing().when(TestUtils.class);
        TestUtils.applyAnswers(Mockito.any(), Mockito.any());

        PowerMockito.doNothing().when(TestUtils.class);
        TestUtils.addResult(Mockito.any(), Mockito.any());
    }

    @Test
    public void onTestNull_returnToMenu() throws ServletException {
        Mockito.when(wrapper.getSessionAttribute("test")).thenReturn(null);

        String expectedPage = PageManager.getProperty("path.page.menu");
        String actualPage = new CompleteCommand().execute(wrapper);

        Assert.assertEquals(expectedPage, actualPage);
    }

    @Test
    public void onAnswerIdsNull_returnToTest() throws ServletException {
        Mockito.when(wrapper.getSessionAttribute("id")).thenReturn(null);

        String expectedPage = PageManager.getProperty("path.page.test");
        String actualPage = new CompleteCommand().execute(wrapper);

        Assert.assertEquals(expectedPage, actualPage);
    }

    @Test
    public void onUserNull_returnToTest() throws ServletException {
        Mockito.when(wrapper.getRequestParameterValues("user")).thenReturn(null);

        String expectedPage = PageManager.getProperty("path.page.test");
        String actualPage = new CompleteCommand().execute(wrapper);

        Assert.assertEquals(expectedPage, actualPage);
    }

    @Test
    public void executeTest() throws ServletException {
        String expectedPage = PageManager.getProperty("path.page.test");
        String actualPage = new CompleteCommand().execute(wrapper);

        // TODO: 03.08.2016 verify invoking static method
        /*Verify that method TestUtils.addResult is invoked*/
        PowerMockito.verifyStatic(Mockito.times(1));
        TestUtils.addResult(Mockito.any(), Mockito.any());

        Assert.assertEquals(expectedPage, actualPage);
    }

}