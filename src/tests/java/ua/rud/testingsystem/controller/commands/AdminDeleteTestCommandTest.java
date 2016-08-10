package ua.rud.testingsystem.controller.commands;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;
import ua.rud.testingsystem.controller.RequestWrapper;
import ua.rud.testingsystem.entities.subject.SubjectUtils;
import ua.rud.testingsystem.entities.test.TestUtils;
import ua.rud.testingsystem.managers.MessageManager;
import ua.rud.testingsystem.managers.PageManager;

import javax.servlet.ServletException;
import java.util.ArrayList;
import java.util.Locale;

import static org.mockito.Mockito.*;

@RunWith(PowerMockRunner.class)
@PrepareForTest({TestUtils.class, SubjectUtils.class})
public class AdminDeleteTestCommandTest {
    private RequestWrapper wrapper = mock(RequestWrapper.class);


    @Before
    public void setUp() throws Exception {
        /*Standard wrapper behaviour*/
        when(wrapper.getRequestParameterValues("testId")).thenReturn(new String[]{"100"});
        when(wrapper.getSessionLanguage()).thenReturn(Locale.ENGLISH);
        doNothing().when(wrapper).setSessionAttribute(any(), any());

        /*Spy TestUtils*/
        PowerMockito.spy(TestUtils.class);
        PowerMockito.doNothing().when(TestUtils.class);
        TestUtils.deleteTests(any());

        /*Spy SubjectUtils*/
        PowerMockito.spy(SubjectUtils.class);
        PowerMockito.doReturn(new ArrayList()).when(SubjectUtils.class);
        SubjectUtils.getSubjects();


    }

    @Test
    public void onTestIdsNull_reportAboutIt() throws ServletException {
        when(wrapper.getRequestParameterValues("testId")).thenReturn(null);

        /*Verify returned page*/
        String expectedPage = PageManager.getProperty("path.page.editTests");
        String actualPage = new AdminDeleteTestCommand().execute(wrapper);
        Assert.assertEquals(expectedPage, actualPage);

        /*Verify updating list of subjects*/
        verify(wrapper).setSessionAttribute(eq("subjects"), any());

        /*Verify setting message attribute*/
        ArgumentCaptor<Object> o = ArgumentCaptor.forClass(Object.class);
        verify(wrapper, times(1)).setRequestAttribute(eq("deleteTestMessage"), o.capture());
        String message = MessageManager.getProperty("editTests.noTestSelected", Locale.ENGLISH);
        Assert.assertEquals(message, o.getValue());
    }

    @Test
    public void executeTest() throws ServletException {
        /*Verify returned page*/
        String expectedPage = PageManager.getProperty("path.page.editTests");
        String actualPage = new AdminDeleteTestCommand().execute(wrapper);
        Assert.assertEquals(expectedPage, actualPage);

        /*Verify updating list of subjects*/
        verify(wrapper).setSessionAttribute(eq("subjects"), any());

        /*Verify deleting TestUtils.deleteTests is invoked*/
        PowerMockito.verifyStatic();
        TestUtils.deleteTests(any());
    }

    @After
    public void teardown() {

    }

}