package ua.rud.testingsystem.tags;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpSession;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.PageContext;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.*;


public class LanguageTagTest {
    private LanguageTag tag = new LanguageTag();
    private int actual, expected;
    private String param = new String();
    private String var = new String();
    private PageContext pageContext = mock(PageContext.class);
    private HttpSession session = mock(HttpSession.class);
    private ServletRequest request = mock(ServletRequest.class);

    @Before
    public void setUp() throws Exception {
        when(pageContext.getSession()).thenReturn(session);
        when(pageContext.getRequest()).thenReturn(request);
        tag.setPageContext(pageContext);
        tag.setParam(param);
        tag.setVar(var);
    }

    @Test
    public void onLanguageDefinedInParam_setNewLanguage() throws JspException {
        when(request.getParameter(param)).thenReturn("some language");
        actual = tag.doStartTag();

        /*Verify request's locale is never used*/
        verify(request, never()).getLocale();

        /*Verify new language is set*/
        ArgumentCaptor<String> s = ArgumentCaptor.forClass(String.class);
        verify(session).setAttribute(s.capture(), any());
        Assert.assertEquals(s.getValue(), var);
    }

    @Test
    public void onSessionLanguageNotDefinedYet_setRequestLanguage() throws JspException {
        when(request.getParameter(param)).thenReturn(null);
        when(session.getAttribute(var)).thenReturn(null);
        actual = tag.doStartTag();

        /*Verify request's locale is used*/
        verify(request, times(1)).getLocale();

        /*Verify new language is set*/
        ArgumentCaptor<String> s = ArgumentCaptor.forClass(String.class);
        verify(session).setAttribute(s.capture(), any());
        Assert.assertEquals(s.getValue(), var);
    }

    @Test
    public void onNewLanguageNotDefined_andSessionLanguageDefined_doNothing() {
        when(request.getParameter(param)).thenReturn(null);
        when(session.getAttribute(var)).thenReturn(new Object());

        /*Verify language is not changed*/
        verify(session, never()).setAttribute(any(), any());
    }


    @After
    public void tearDown() throws Exception {
        expected = 0;
        Assert.assertEquals(actual, expected);
    }

}