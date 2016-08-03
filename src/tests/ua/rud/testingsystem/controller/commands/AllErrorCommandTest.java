package ua.rud.testingsystem.controller.commands;

import org.junit.Assert;
import org.junit.Test;
import org.mockito.Mockito;
import ua.rud.testingsystem.resource.PageManager;

import javax.servlet.ServletException;

/**
 * Created by Дмитрий on 03.08.2016.
 */
public class AllErrorCommandTest {

    @Test
    public void execute() throws ServletException {
        String page = new AllErrorCommand().execute(Mockito.any());
        String expectedPage = PageManager.getProperty("path.page.error");
        Assert.assertEquals(expectedPage, page);
    }
}