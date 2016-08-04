package ua.rud.testingsystem.controller.commands;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;
import ua.rud.testingsystem.controller.RequestWrapper;
import ua.rud.testingsystem.entities.Subject;
import ua.rud.testingsystem.entities.SubjectUtils;
import ua.rud.testingsystem.entities.test.TestUtils;
import ua.rud.testingsystem.entities.user.User;
import ua.rud.testingsystem.entities.user.UserUtils;
import ua.rud.testingsystem.resource.PageManager;

import javax.servlet.ServletException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Locale;

import static org.mockito.Mockito.*;

@RunWith(PowerMockRunner.class)
@PrepareForTest({TestUtils.class, SubjectUtils.class, UserUtils.class})
public class GuestAuthorizationCommandTest {
    private RequestWrapper wrapper;

    @Before
    public void setUp() {
        /*Wrapper standard behaviour*/
        wrapper = mock(RequestWrapper.class);
        when(wrapper.getRequestParameter("login")).thenReturn("login");
        when(wrapper.getRequestParameter("password")).thenReturn("password");
        when(wrapper.getSessionLanguage()).thenReturn(Locale.ENGLISH);
        doNothing().when(wrapper).setRequestAttribute(any(), any());
        doNothing().when(wrapper).setSessionAttribute(any(), any());

        /*Return new User on UserUtils.getUser*/
        PowerMockito.spy(UserUtils.class);
        PowerMockito.doReturn(new User()).when(UserUtils.class);
        UserUtils.getUser(any(), any());

        /*Return empty ArrayList on SubjectUtils.getResultsForSubjects*/
        PowerMockito.spy(SubjectUtils.class);
        PowerMockito.doReturn(new HashMap()).when(SubjectUtils.class);
        SubjectUtils.getResultsForSubjects(anyListOf(Subject.class), anyInt());

        /*Return empty ArrayList on SubjectUtils.getSubjects()*/
        PowerMockito.doReturn(new ArrayList()).when(SubjectUtils.class);
        SubjectUtils.getSubjects();

    }

    @Test
    public void onUserNull_redirectToLoginPage() throws ServletException {
        PowerMockito.doReturn(null).when(UserUtils.class);
        UserUtils.getUser(any(), any());


        String expectedPage = PageManager.getProperty("path.page.login");
        String actualPage = new GuestAuthorizationCommand().execute(wrapper);

        Assert.assertEquals(expectedPage, actualPage);
    }

    @Test
    public void executeTest() throws ServletException {
        /*Verify page to be returned*/
        String expectedPage = PageManager.getProperty("path.page.menu");
        String actualPage = new GuestAuthorizationCommand().execute(wrapper);
        Assert.assertEquals(expectedPage, actualPage);

        /*Verify SubjectUtils.getSubjects is invoked*/
        PowerMockito.verifyStatic(times(1));
        SubjectUtils.getSubjects();

        /*Verify wrapper.setSessionAttribute is invoked 3 tomes*/
        verify(wrapper, times(3)).setSessionAttribute(any(), any());
    }

}