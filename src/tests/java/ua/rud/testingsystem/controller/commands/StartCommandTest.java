package ua.rud.testingsystem.controller.commands;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;
import ua.rud.testingsystem.controller.RequestWrapper;
import ua.rud.testingsystem.entities.test.TestUtils;
import ua.rud.testingsystem.managers.PageManager;

import javax.servlet.ServletException;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.*;

@RunWith(PowerMockRunner.class)
@PrepareForTest(TestUtils.class)
public class StartCommandTest {

    private RequestWrapper wrapper = mock(RequestWrapper.class);

    @Before
    public void setUp() throws Exception {

    }

    @Test
    public void onIdNull_returnToMenu() throws ServletException {
        /*Return null on wrapper.getRequestParameter*/
        when(wrapper.getRequestParameter("id")).thenReturn(null);

        /*Verify page to be returned*/
        String expectedPage = PageManager.getProperty("path.page.menu");
        String actualPage = new StartCommand().execute(wrapper);
        Assert.assertEquals(expectedPage, actualPage);
    }

    @Test
    public void executeTest() throws ServletException {

        /*Return "1" on wrapper.getRequestParameter*/
        when(wrapper.getRequestParameter("id")).thenReturn("1");

        /*Do nothing on wrapper.setSessionAttribute*/
        doNothing().when(wrapper).setSessionAttribute(any(), any());

        /*Return new Test on TestUtils.getTest*/
        PowerMockito.mockStatic(TestUtils.class);
        PowerMockito.doReturn(new ua.rud.testingsystem.entities.test.Test()).when(TestUtils.class);
        TestUtils.getTest(anyInt());

        /*Verify page to be returned*/
        String expectedPage = PageManager.getProperty("path.page.test");
        String actualPage = new StartCommand().execute(wrapper);
        Assert.assertEquals(expectedPage, actualPage);

        /*Verify TestUtils.getTest is invoked*/
        PowerMockito.verifyStatic(times(1));
        TestUtils.getTest(anyInt());

        /*Verify wrapper.setSessionAttribute("test", test) is invoked*/
        verify(wrapper, times(1)).setSessionAttribute(eq("test"), any());
    }

}