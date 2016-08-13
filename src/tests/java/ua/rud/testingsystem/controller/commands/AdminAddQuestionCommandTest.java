package ua.rud.testingsystem.controller.commands;

import org.junit.After;
import org.junit.Before;
import org.mockito.ArgumentCaptor;
import ua.rud.testingsystem.controller.RequestWrapper;
import ua.rud.testingsystem.entities.test.Test;
import ua.rud.testingsystem.managers.MessageManager;
import ua.rud.testingsystem.managers.PageManager;

import javax.servlet.ServletException;
import java.util.Locale;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

public class AdminAddQuestionCommandTest {
    private RequestWrapper wrapper;
    private Test test;
    private String page;

    @Before
    public void init() {
        /*Create mocks*/
        wrapper = mock(RequestWrapper.class);
        test = mock(Test.class);

        /*Wrapper standard behaviour*/
        when(wrapper.getSessionAttribute("newTest")).thenReturn(test);
        when(wrapper.getRequestParameter("task")).thenReturn("task");
        when(wrapper.getRequestParameterValues("text")).thenReturn(new String[]{"text"});
        when(wrapper.getRequestParameterValues("answerId")).thenReturn(new String[]{"1"});
        when(wrapper.getSessionLanguage()).thenReturn(Locale.ENGLISH);
    }

    @org.junit.Test
    public void onTestNull_DoNothing() throws ServletException {
        when(wrapper.getSessionAttribute("newTest")).thenReturn(null);

        page = new AdminAddQuestionCommand().execute(wrapper);
    }

    @org.junit.Test
    public void onTaskEmpty_reportAboutIt() throws ServletException {
        when(wrapper.getRequestParameter("task")).thenReturn("");

        ArgumentCaptor<String> s = ArgumentCaptor.forClass(String.class);
        ArgumentCaptor<String> o = ArgumentCaptor.forClass(String.class);

        page = new AdminAddQuestionCommand().execute(wrapper);

        Locale locale = wrapper.getSessionLanguage();
        String message = MessageManager.getProperty("addTest.emptyTask", locale);
        verify(wrapper).setRequestAttribute(s.capture(), o.capture());

        /*Verify message*/
        assertEquals("addQuestionMessage", s.getValue());
        assertEquals(message, o.getValue());
    }

    @org.junit.Test
    public void onTextsNull_reportAboutIt() throws ServletException {
        when(wrapper.getRequestParameterValues("text")).thenReturn(null);

        ArgumentCaptor<String> s = ArgumentCaptor.forClass(String.class);
        ArgumentCaptor<String> o = ArgumentCaptor.forClass(String.class);

        page = new AdminAddQuestionCommand().execute(wrapper);

        Locale locale = wrapper.getSessionLanguage();
        String message = MessageManager.getProperty("addTest.emptyAnswers", locale);
        verify(wrapper).setRequestAttribute(s.capture(), o.capture());

        /*Verify message*/
        assertEquals("addQuestionMessage", s.getValue());
        assertEquals(message, o.getValue());
    }

    @org.junit.Test
    public void onAnswerIdsNull_reportAboutIt() throws ServletException {
        when(wrapper.getRequestParameterValues("answerId")).thenReturn(null);

        ArgumentCaptor<String> s = ArgumentCaptor.forClass(String.class);
        ArgumentCaptor<String> o = ArgumentCaptor.forClass(String.class);

        page = new AdminAddQuestionCommand().execute(wrapper);

        Locale locale = wrapper.getSessionLanguage();
        String message = MessageManager.getProperty("addTest.emptyRightAnswers", locale);
        verify(wrapper).setRequestAttribute(s.capture(), o.capture());

        /*Verify message*/
        assertEquals("addQuestionMessage", s.getValue());
        assertEquals(message, o.getValue());
    }

    @org.junit.Test
    public void addQuestionTest() throws ServletException {
        page = new AdminAddQuestionCommand().execute(wrapper);

        /*Verify addQuestion() is invoked*/
        verify(test).addQuestion(any());
    }

    @After
    public void end() {
        String testPage = PageManager.getProperty("path.page.addTest");
        assertEquals(page, testPage);
    }
}