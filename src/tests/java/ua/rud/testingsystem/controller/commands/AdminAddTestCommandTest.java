package ua.rud.testingsystem.controller.commands;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;
import ua.rud.testingsystem.controller.RequestWrapper;
import ua.rud.testingsystem.entities.test.Test;
import ua.rud.testingsystem.entities.test.TestUtils;
import ua.rud.testingsystem.managers.MessageManager;
import ua.rud.testingsystem.managers.PageManager;

import javax.servlet.ServletException;
import java.util.Locale;

import static org.mockito.Matchers.any;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.*;

@RunWith(PowerMockRunner.class)
@PrepareForTest(TestUtils.class)
public class AdminAddTestCommandTest {
    private final RequestWrapper wrapper = mock(RequestWrapper.class);
    private final ArgumentCaptor<Object> o = ArgumentCaptor.forClass(Object.class);
    private String expectedPage;
    private String actualPage;

//    @Test(expected = NullPointerException.class)
//    public void shouldThrowNullPointerExceptionWithNullParameter() throws Exception {
//        String name = new AdminAddTestCommand().execute(null);
//    }
//
//    @Test
//    public void onCaptionNull_reportsAboutIt() throws Exception {
//
//        RequestWrapper rw = mock(RequestWrapper.class);
//        when(rw.getRequestParameter(eq("caption"))).thenReturn("");
//        when(rw.getSessionLanguage()).thenReturn(Locale.ENGLISH);
//
//        ArgumentCaptor<String> key = ArgumentCaptor.forClass(String.class);
//        ArgumentCaptor<String> value = ArgumentCaptor.forClass(String.class);
//
//        String response = new AdminAddTestCommand().execute(rw);
//
//        verify(rw, times(1)).getRequestParameter(eq("caption"));
//        verify(rw).setRequestAttribute(key.capture(), value.capture());
//
//        assertEquals("addTestMessage", key.getValue());
//        assertEquals(MessageManager.getProperty("editTests.emptyCaption", Locale.ENGLISH), value.getValue());
//
//    }

    @Before
    public void setUp() {
        /*Wrapper standard behaviour*/
        when(wrapper.getRequestParameter("caption")).thenReturn("caption");
        when(wrapper.getRequestParameter("subjectId")).thenReturn("subjectId");
        when(wrapper.getSessionLanguage()).thenReturn(Locale.ENGLISH);

        /*Spy TestUtils*/
        PowerMockito.spy(TestUtils.class);
        PowerMockito.doReturn(new Test()).when(TestUtils.class);
        TestUtils.getNewTest(any());
    }

    @org.junit.Test
    public void onCaptionNull_reportAboutIt() throws ServletException {
        when(wrapper.getRequestParameter("caption")).thenReturn("");

        actualPage = new AdminAddTestCommand().execute(wrapper);

        /*Verify message text*/
        verify(wrapper).setRequestAttribute(eq("addTestMessage"), o.capture());
        String expectedMessage = MessageManager.getProperty("editTests.emptyCaption", Locale.ENGLISH);
        Assert.assertEquals(expectedMessage, o.getValue());

        expectedPage = PageManager.getProperty("path.page.editTests");

    }

    @org.junit.Test
    public void onSubjectNull_reportAboutIt() throws ServletException {
        when(wrapper.getRequestParameter("subjectId")).thenReturn("");

        actualPage = new AdminAddTestCommand().execute(wrapper);

        /*Verify message text*/
        verify(wrapper).setRequestAttribute(eq("addTestMessage"), o.capture());
        String expectedMessage = MessageManager.getProperty("editTests.noSubject", Locale.ENGLISH);
        Assert.assertEquals(expectedMessage, o.getValue());

        expectedPage = PageManager.getProperty("path.page.editTests");

    }

    @org.junit.Test
    public void executeTest() throws ServletException {
        actualPage = new AdminAddTestCommand().execute(wrapper);

        /*Verify TestUtils.getNewTest is invoked*/
        PowerMockito.verifyStatic();
        TestUtils.getNewTest(any());

        /*Verify wrapper.setSessionAttribute is invoked two times*/
        verify(wrapper, times(2)).setSessionAttribute(any(), any());

        expectedPage = PageManager.getProperty("path.page.addTest");

    }

    @After
    public void tearDown() {
        /*Verify returned page*/
        Assert.assertEquals(expectedPage, actualPage);
    }

}