package ua.rud.testingsystem.controller.commands;

import org.junit.Assert;
import org.junit.Test;
import org.mockito.Mockito;
import ua.rud.testingsystem.managers.PageManager;

import javax.servlet.ServletException;

/**
 * Created by Дмитрий on 03.08.2016.
 */
public class AllIndexCommandTest {
    @Test
    public void executeTest() throws ServletException {
        String page = new AllIndexCommand().execute(Mockito.any());
        String expectedPage = PageManager.getProperty("path.page.index");

        Assert.assertEquals(expectedPage, page);
    }

}