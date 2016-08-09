package ua.rud.testingsystem.controller.commands;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import ua.rud.testingsystem.controller.RequestWrapper;
import ua.rud.testingsystem.resource.MessageManager;
import ua.rud.testingsystem.resource.PageManager;

import javax.servlet.ServletException;
import java.util.Locale;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

public class AdminAddQuestionCommandTest {
    RequestWrapper wrapper;
    ua.rud.testingsystem.entities.test.Test test;
    String page;

    @Before
    public void init() {
        wrapper = mock(RequestWrapper.class);
        test = mock(ua.rud.testingsystem.entities.test.Test.class);

        when(wrapper.getSessionAttribute("newTest")).thenReturn(test);
        when(wrapper.getRequestParameter("task")).thenReturn("task");
        when(wrapper.getRequestParameterValues("text")).thenReturn(new String[]{"text"});
        when(wrapper.getRequestParameterValues("answerId")).thenReturn(new String[]{"1"});

        when(wrapper.getSessionLanguage()).thenReturn(Locale.ENGLISH);
    }

    @Test
    public void onTestNull_DoNothing() throws ServletException {
        when(wrapper.getSessionAttribute("newTest")).thenReturn(null);

        page = new AdminAddQuestionCommand().execute(wrapper);
    }

    @Test
    public void onTaskEmpty_reportAboutIt() throws ServletException {
        when(wrapper.getRequestParameter("task")).thenReturn("");

        ArgumentCaptor<String> s = ArgumentCaptor.forClass(String.class);
        ArgumentCaptor<String> o = ArgumentCaptor.forClass(String.class);

        page = new AdminAddQuestionCommand().execute(wrapper);

        Locale locale = wrapper.getSessionLanguage();
        String message = MessageManager.getProperty("addTest.emptyTask", locale);
        verify(wrapper).setRequestAttribute(s.capture(), o.capture());

        assertEquals("addQuestionMessage", s.getValue());
        assertEquals(message, o.getValue());
    }

    @Test
    public void onTextsNull_reportAboutIt() throws ServletException {
        when(wrapper.getRequestParameterValues("text")).thenReturn(null);

        ArgumentCaptor<String> s = ArgumentCaptor.forClass(String.class);
        ArgumentCaptor<String> o = ArgumentCaptor.forClass(String.class);

        page = new AdminAddQuestionCommand().execute(wrapper);

        Locale locale = wrapper.getSessionLanguage();
        String message = MessageManager.getProperty("addTest.emptyAnswers", locale);
        verify(wrapper).setRequestAttribute(s.capture(), o.capture());

        assertEquals("addQuestionMessage", s.getValue());
        assertEquals(message, o.getValue());
    }

    @Test
    public void onAnswerIdsNull_reportAboutIt() throws ServletException {
        when(wrapper.getRequestParameterValues("answerId")).thenReturn(null);

        ArgumentCaptor<String> s = ArgumentCaptor.forClass(String.class);
        ArgumentCaptor<String> o = ArgumentCaptor.forClass(String.class);

        page = new AdminAddQuestionCommand().execute(wrapper);

        Locale locale = wrapper.getSessionLanguage();
        String message = MessageManager.getProperty("addTest.emptyRightAnswers", locale);
        verify(wrapper).setRequestAttribute(s.capture(), o.capture());

        assertEquals("addQuestionMessage", s.getValue());
        assertEquals(message, o.getValue());

    }

    @Test
    public void addQuestion() throws ServletException {
        page = new AdminAddQuestionCommand().execute(wrapper);
        verify(test).addQuestion(any());
    }


    @After
    public void end() {
        String testPage = PageManager.getProperty("path.page.addTest");
        assertEquals(page, testPage);
    }


//    @Test
//    public void execute() throws Exception {
//        RequestWrapper requestWrapper = mock(RequestWrapper.class);
//        ua.rud.testingsystem.entities.test.Test test = mock(ua.rud.testingsystem.entities.test.Test.class);
//
//        when(requestWrapper.getSessionAttribute(eq("newTest"))).thenReturn(test);
//        when(requestWrapper.getSessionLanguage()).thenReturn(Locale.ENGLISH);
//
//        String page = new AdminAddQuestionCommand().execute(requestWrapper);
//        verify(requestWrapper).getRequestParameter("task");
//
////        verify(PageManager).getProperty("path.page.editTests");
//
//        assertEquals(page, PageManager.getProperty("path.page.editTests"));
//    }
}