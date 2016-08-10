package ua.rud.testingsystem.controller.commands;

import org.junit.Assert;
import org.junit.Test;
import org.mockito.Mockito;
import ua.rud.testingsystem.controller.RequestWrapper;
import ua.rud.testingsystem.managers.PageManager;


public class AdminMenuCommandTest {
    @Test
    public void executeTest() throws Exception {
        RequestWrapper wrapper = Mockito.mock(RequestWrapper.class);

        String expectedPage = new AdminMenuCommand().execute(wrapper);
        String actualPage = PageManager.getProperty("path.page.adminMenu");

        Assert.assertEquals(expectedPage, actualPage);
    }

}