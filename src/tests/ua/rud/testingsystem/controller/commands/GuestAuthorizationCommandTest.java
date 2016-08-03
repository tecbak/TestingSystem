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
import ua.rud.testingsystem.entities.SubjectUtils;
import ua.rud.testingsystem.entities.test.TestUtils;
import ua.rud.testingsystem.entities.user.User;
import ua.rud.testingsystem.entities.user.UserUtils;
import ua.rud.testingsystem.resource.PageManager;

import javax.servlet.ServletException;
import java.util.ArrayList;
import java.util.Locale;

@RunWith(PowerMockRunner.class)
@PrepareForTest({TestUtils.class, SubjectUtils.class, UserUtils.class})
public class GuestAuthorizationCommandTest {
    private RequestWrapper wrapper;

    @Before
    public void setUp() {
        /*Wrapper standard behaviour*/
        wrapper = Mockito.mock(RequestWrapper.class);
        Mockito.when(wrapper.getRequestParameter("login")).thenReturn("login");
        Mockito.when(wrapper.getRequestParameter("password")).thenReturn("password");
        Mockito.when(wrapper.getSessionLanguage()).thenReturn(Locale.ENGLISH);
        Mockito.doNothing().when(wrapper).setRequestAttribute(Mockito.any(), Mockito.any());
        Mockito.doNothing().when(wrapper).setSessionAttribute(Mockito.any(), Mockito.any());

        /*Return new User on UserUtils.getUser*/
        PowerMockito.spy(UserUtils.class);
        PowerMockito.doReturn(new User()).when(UserUtils.class);
        UserUtils.getUser(Mockito.any(), Mockito.any());

        /*Return empty ArrayList on UserUtils.getResults*/
        PowerMockito.spy(TestUtils.class);
        PowerMockito.doReturn(new ArrayList()).when(TestUtils.class);
        TestUtils.getResults(Mockito.anyInt(), Mockito.anyInt());

        /*Return empty ArrayList on SubjectUtils.getSubjects()*/
        PowerMockito.spy(SubjectUtils.class);
        PowerMockito.doReturn(new ArrayList()).when(SubjectUtils.class);
        SubjectUtils.getSubjects();

    }

    @Test
    public void onUserNull_redirectToLoginPage() throws ServletException {
        PowerMockito.doReturn(null).when(UserUtils.class);
        UserUtils.getUser(Mockito.any(), Mockito.any());


        String expectedPage = PageManager.getProperty("path.page.login");
        String actualPage = new GuestAuthorizationCommand().execute(wrapper);

        Assert.assertEquals(expectedPage, actualPage);
    }

    @Test
    public void executeTest() throws ServletException {
        String expectedPage = PageManager.getProperty("path.page.menu");
        String actualPage = new GuestAuthorizationCommand().execute(wrapper);

        Assert.assertEquals(expectedPage, actualPage);
    }

}