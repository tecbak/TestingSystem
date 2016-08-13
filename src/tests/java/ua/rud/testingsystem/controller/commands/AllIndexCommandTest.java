package ua.rud.testingsystem.controller.commands;

import org.junit.Assert;
import org.junit.Test;
import org.mockito.Mockito;
import ua.rud.testingsystem.controller.RequestWrapper;
import ua.rud.testingsystem.managers.PageManager;

import javax.servlet.ServletException;

public class AllIndexCommandTest {
    @Test
    public void executeTest() throws ServletException {
        RequestWrapper wrapper = Mockito.mock(RequestWrapper.class);
        String page = new AllIndexCommand().execute(wrapper);
        String expectedPage = PageManager.getProperty("path.page.index");

        Assert.assertEquals(expectedPage, page);
    }
}