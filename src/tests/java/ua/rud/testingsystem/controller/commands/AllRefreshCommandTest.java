package ua.rud.testingsystem.controller.commands;

import org.junit.Assert;
import org.junit.Test;
import ua.rud.testingsystem.controller.RequestWrapper;
import ua.rud.testingsystem.managers.PageManager;

import javax.servlet.ServletException;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class AllRefreshCommandTest {
    private RequestWrapper wrapper = mock(RequestWrapper.class);

    @Test
    public void onPageNull_returnError() throws ServletException {
        when(wrapper.getSessionAttribute("page")).thenReturn(null);

        String expectedPage = PageManager.getProperty("path.page.error");
        String actualPage = new AllRefreshCommand().execute(wrapper);

        Assert.assertEquals(expectedPage, actualPage);
    }

    @Test
    public void onPageNotString_returnError() throws ServletException {
        when(wrapper.getSessionAttribute("page")).thenReturn(new Object());

        String expectedPage = PageManager.getProperty("path.page.error");
        String actualPage = new AllRefreshCommand().execute(wrapper);

        Assert.assertEquals(expectedPage, actualPage);
    }

    @Test
    public void onPageEmpty_returnError() throws ServletException {
        when(wrapper.getSessionAttribute("page")).thenReturn("");

        String expectedPage = PageManager.getProperty("path.page.error");
        String actualPage = new AllRefreshCommand().execute(wrapper);

        Assert.assertEquals(expectedPage, actualPage);
    }

    @Test
    public void executeTest() throws ServletException {
        String page = "some page";
        when(wrapper.getSessionAttribute("page")).thenReturn(page);

        String expectedPage = page;
        String actualPage = new AllRefreshCommand().execute(wrapper);

        Assert.assertEquals(expectedPage, actualPage);
    }
}