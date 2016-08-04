package ua.rud.testingsystem.controller.commands;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mockito;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;
import ua.rud.testingsystem.controller.RequestWrapper;
import ua.rud.testingsystem.entities.subject.Subject;
import ua.rud.testingsystem.entities.subject.SubjectUtils;
import ua.rud.testingsystem.resource.MessageManager;
import ua.rud.testingsystem.resource.PageManager;

import javax.servlet.ServletException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import static org.junit.Assert.assertEquals;

@RunWith(PowerMockRunner.class)
@PrepareForTest(SubjectUtils.class)
public class AdminSaveSubjectCommandTest {

    @Test
    public void onNameNull_reportAboutIt() throws ServletException {
        PowerMockito.spy(SubjectUtils.class);

        /*
         * return empty List<Subjects>
         * when SubjectUtils.getSubjects() is invoked
         */
        List<Subject> subjects = new ArrayList<>();
        PowerMockito.doReturn(subjects).when(SubjectUtils.class);
        SubjectUtils.getSubjects();

        /*
         * return null
         * when wrapper.getRequestParameter("name") is invoked
         */
        RequestWrapper wrapper = Mockito.mock(RequestWrapper.class);
        Mockito.when(wrapper.getRequestParameter("name")).thenReturn(null);

        /*
         * return Locale.English
         * when wrapper.getSessionLanguage() is invoked
         */
        Mockito.when(wrapper.getSessionLanguage()).thenReturn(Locale.ENGLISH);

        /*Invoke method to be tested*/
        String page = new AdminSaveSubjectCommand().execute(wrapper);

        /*Catch arguments and compare with expected ones*/
        ArgumentCaptor<ArrayList> o0 = ArgumentCaptor.forClass(ArrayList.class);
        ArgumentCaptor<String> o1 = ArgumentCaptor.forClass(String.class);
        ArgumentCaptor<String> s = ArgumentCaptor.forClass(String.class);

        /*Capture message*/
        Mockito.verify(wrapper).setRequestAttribute(s.capture(), o1.capture());
        assertEquals("addSubjectMessage", s.getValue());
        assertEquals(MessageManager.getProperty("editSubjects.nameEmpty", Locale.ENGLISH), o1.getValue());

        /*Capture updating list os subjects*/
        Mockito.verify(wrapper).setSessionAttribute(s.capture(), o0.capture());
        assertEquals("subjects", s.getValue());
        assertEquals(subjects, o0.getValue());

        /*Check value to be returned*/
        String expectedPage = PageManager.getProperty("path.page.editSubjects");
        assertEquals(expectedPage, page);
    }

    @Test
    public void onSubjectExists_reportAboutIt() throws ServletException {
        PowerMockito.spy(SubjectUtils.class);

        /*
         * return empty List<Subjects>
         * when SubjectUtils.getSubjects() is invoked
         */
        List<Subject> subjects = new ArrayList<>();
        PowerMockito.doReturn(subjects).when(SubjectUtils.class);
        SubjectUtils.getSubjects();

        /*
         * return true
         * when SubjectUtils.subjectExists() is invoked
         */
        PowerMockito.doReturn(true).when(SubjectUtils.class);
        SubjectUtils.subjectExists(Mockito.any());

        /*
         * return "name"
         * when wrapper.getRequestParameter("name") is invoked
         */
        RequestWrapper wrapper = Mockito.mock(RequestWrapper.class);
        Mockito.when(wrapper.getRequestParameter("name")).thenReturn("name");

        /*
         * return Locale.English
         * when wrapper.getSessionLanguage() is invoked
         */
        Mockito.when(wrapper.getSessionLanguage()).thenReturn(Locale.ENGLISH);

        /*Invoke method to be tested*/
        String page = new AdminSaveSubjectCommand().execute(wrapper);

        /*Catch arguments and compare with expected ones*/
        ArgumentCaptor<ArrayList> o0 = ArgumentCaptor.forClass(ArrayList.class);
        ArgumentCaptor<String> o1 = ArgumentCaptor.forClass(String.class);
        ArgumentCaptor<String> s = ArgumentCaptor.forClass(String.class);

        /*Capture message*/
        Mockito.verify(wrapper).setRequestAttribute(s.capture(), o1.capture());
        assertEquals("addSubjectMessage", s.getValue());
        assertEquals(MessageManager.getProperty("editSubjects.subjectExists", Locale.ENGLISH), o1.getValue());

        /*Capture updating list os subjects*/
        Mockito.verify(wrapper).setSessionAttribute(s.capture(), o0.capture());
        assertEquals("subjects", s.getValue());
        assertEquals(subjects, o0.getValue());

        /*Check value to be returned*/
        String expectedPage = PageManager.getProperty("path.page.editSubjects");
        assertEquals(expectedPage, page);

    }
    @Test
    public void saveSubjectTest() throws ServletException {
        PowerMockito.spy(SubjectUtils.class);

        /*
         * return empty List<Subjects>
         * when SubjectUtils.getSubjects() is invoked
         */
        List<Subject> subjects = new ArrayList<>();
        PowerMockito.doReturn(subjects).when(SubjectUtils.class);
        SubjectUtils.getSubjects();

        /*
         * return false
         * when SubjectUtils.subjectExists() is invoked
         */
        PowerMockito.doReturn(false).when(SubjectUtils.class);
        SubjectUtils.subjectExists(Mockito.any());


        /*
         * do nothing
         * when SubjectUtils.addSubject() is invoked
         */
        PowerMockito.doNothing().when(SubjectUtils.class);
        SubjectUtils.addSubject(Mockito.any());

        /*
         * return "name"
         * when wrapper.getRequestParameter("name") is invoked
         */
        RequestWrapper wrapper = Mockito.mock(RequestWrapper.class);
        Mockito.when(wrapper.getRequestParameter("name")).thenReturn("name");

        /*
         * return Locale.English
         * when wrapper.getSessionLanguage() is invoked
         */
        Mockito.when(wrapper.getSessionLanguage()).thenReturn(Locale.ENGLISH);

        /*Invoke method to be tested*/
        String page = new AdminSaveSubjectCommand().execute(wrapper);

         /*Catch arguments and compare with expected ones*/
        ArgumentCaptor<ArrayList> o = ArgumentCaptor.forClass(ArrayList.class);
        ArgumentCaptor<String> s = ArgumentCaptor.forClass(String.class);

        /*Capture updating list os subjects*/
        Mockito.verify(wrapper).setSessionAttribute(s.capture(), o.capture());
        assertEquals("subjects", s.getValue());
        assertEquals(subjects, o.getValue());

        /*Check value to be returned*/
        String expectedPage = PageManager.getProperty("path.page.editSubjects");
        assertEquals(expectedPage, page);
    }


}