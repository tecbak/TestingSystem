package ua.rud.testingsystem.controller.commands;

import org.junit.Assert;
import org.junit.Test;
import org.mockito.Mockito;
import ua.rud.testingsystem.controller.RequestWrapper;
import ua.rud.testingsystem.managers.PageManager;

import javax.servlet.ServletException;

public class GuestLoginCommandTest {
    @Test
    public void executeTest() throws ServletException {
        RequestWrapper wrapper = Mockito.mock(RequestWrapper.class);
        String actualPage = new GuestLoginCommand().execute(wrapper);
        String expectedPage = PageManager.getProperty("path.page.login");

        Assert.assertEquals(expectedPage, actualPage);

    }
}