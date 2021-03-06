package ua.rud.testingsystem.controller.commands;

import org.junit.Assert;
import org.junit.Test;
import org.mockito.Mockito;
import ua.rud.testingsystem.controller.RequestWrapper;
import ua.rud.testingsystem.managers.PageManager;

import javax.servlet.ServletException;

public class UserLogoutCommandTest {
    @Test
    public void executeTest() throws ServletException {
        RequestWrapper wrapper = Mockito.mock(RequestWrapper.class);
        Mockito.doNothing().when(wrapper).setSessionAttribute(Mockito.any(), Mockito.any());

        String expectedPage = PageManager.getProperty("path.page.login");
        String actualPage = new UserLogoutCommand().execute(wrapper);

        Assert.assertEquals(expectedPage, actualPage);
    }
}