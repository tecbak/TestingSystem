package ua.rud.testingsystem.controller.commands;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;
import ua.rud.testingsystem.controller.RequestWrapper;
import ua.rud.testingsystem.entities.subject.Subject;
import ua.rud.testingsystem.entities.subject.SubjectUtils;
import ua.rud.testingsystem.entities.user.User;
import ua.rud.testingsystem.managers.PageManager;

import javax.servlet.ServletException;
import java.util.ArrayList;
import java.util.HashMap;

import static org.mockito.Mockito.*;

@RunWith(PowerMockRunner.class)
@PrepareForTest(SubjectUtils.class)
public class UserMenuCommandTest {
    private RequestWrapper wrapper = mock(RequestWrapper.class);

    @Before
    public void setUp() {

        /*Wrapper standard behaviour*/
        when(wrapper.getSessionAttribute("user")).thenReturn(new User());
        doNothing().when(wrapper).setSessionAttribute(any(), any());

        /*Spy SubjectUtils*/
        PowerMockito.spy(SubjectUtils.class);

        PowerMockito.doReturn(new HashMap()).when(SubjectUtils.class);
        SubjectUtils.getResultsForSubjects(anyListOf(Subject.class), anyInt());

//        ArrayList toBeReturned = new ArrayList() {{         //ArrayList containing one Subject
//            add(new Subject());
//        }};
        PowerMockito.doReturn(new ArrayList()).when(SubjectUtils.class);
        SubjectUtils.getSubjects();

    }

    @Test
    public void executeTest() throws ServletException {
        String expectedPage = PageManager.getProperty("path.page.menu");
        String actualPage = new UserMenuCommand().execute(wrapper);

        /*Verify setSessionAttribute is twice invoked (for subjects and results)*/
        verify(wrapper, times(2)).setSessionAttribute(any(), any());

        Assert.assertEquals(expectedPage, actualPage);
    }

}