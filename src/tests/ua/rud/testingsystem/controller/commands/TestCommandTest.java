package ua.rud.testingsystem.controller.commands;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import ua.rud.testingsystem.controller.RequestWrapper;
import ua.rud.testingsystem.entities.user.User;
import ua.rud.testingsystem.resource.PageManager;

import javax.servlet.ServletException;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class TestCommandTest {
    private RequestWrapper wrapper = mock(RequestWrapper.class);

    @Before
    public void setUp() {
        when(wrapper.getSessionAttribute("test")).thenReturn(new ua.rud.testingsystem.entities.test.Test());
        when(wrapper.getSessionAttribute("user")).thenReturn(new User());
    }

    @Test
    public void onUserNull_returnLoginPage() throws ServletException {
        when(wrapper.getSessionAttribute("user")).thenReturn(null);

        String expectedPage = PageManager.getProperty("path.page.login");
        String actualPage = new TestCommand().execute(wrapper);

        Assert.assertEquals(expectedPage, actualPage);

    }

    @Test
    public void onTestNull_returnMenuPage() throws ServletException {
        when(wrapper.getSessionAttribute("test")).thenReturn(null);

        String expectedPage = PageManager.getProperty("path.page.menu");
        String actualPage = new TestCommand().execute(wrapper);

        Assert.assertEquals(expectedPage, actualPage);

    }

    @Test
    public void testCommandTest() throws ServletException {
        String expectedPage = PageManager.getProperty("path.page.test");
        String actualPage = new TestCommand().execute(wrapper);

        Assert.assertEquals(expectedPage, actualPage);

    }
}