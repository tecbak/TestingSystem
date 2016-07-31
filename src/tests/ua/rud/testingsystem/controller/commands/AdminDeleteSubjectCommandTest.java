package ua.rud.testingsystem.controller.commands;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;
import ua.rud.testingsystem.controller.RequestWrapper;
import ua.rud.testingsystem.entities.SubjectUtils;
import ua.rud.testingsystem.resource.MessageManager;
import ua.rud.testingsystem.resource.PageManager;

import javax.servlet.ServletException;
import java.util.ArrayList;
import java.util.Locale;

import static org.easymock.EasyMock.expect;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.verify;
import static org.powermock.api.easymock.PowerMock.*;

@RunWith(PowerMockRunner.class)
@PrepareForTest(SubjectUtils.class)
public class AdminDeleteSubjectCommandTest {
    String page;
    RequestWrapper wrapper = mock(RequestWrapper.class);

    @Before
    public void setUp() throws Exception {
        when(wrapper.getSessionLanguage()).thenReturn(Locale.ENGLISH);
        when(wrapper.getRequestParameterValues("subjectId")).thenReturn(new String[]{"100"});

    }

    @Test
    public void onSubjectIdNull_ReportAboutIt() throws ServletException {
        when(wrapper.getRequestParameterValues("subjectId")).thenReturn(null);
        mockStatic(SubjectUtils.class);
        expect(SubjectUtils.getSubjects()).andReturn(new ArrayList<>());

        ArgumentCaptor<String> s = ArgumentCaptor.forClass(String.class);
        ArgumentCaptor<String> o = ArgumentCaptor.forClass(String.class);

        replayAll();
        page = new AdminDeleteSubjectCommand().execute(wrapper);
        verifyAll();

        verify(wrapper).setRequestAttribute(s.capture(), o.capture());


        String message = MessageManager.getProperty("editSubjects.noSubjectSelected", Locale.ENGLISH);

        assertEquals("deleteSubjectMessage", s.getValue());
        assertEquals(message, o.getValue());
    }

    @Test
    public void deleteSubject() throws Exception {
        when(wrapper.getRequestParameterValues("subjectId")).thenReturn(new String[]{"100"});
        PowerMockito.spy(SubjectUtils.class);

        PowerMockito.doNothing().when(SubjectUtils.class);
        SubjectUtils.deleteSubjects(any(String[].class));

        PowerMockito.doReturn(new ArrayList<>()).when(SubjectUtils.class);
        SubjectUtils.getSubjects();

        replayAll();
        page = new AdminDeleteSubjectCommand().execute(wrapper);
        verifyAll();


    }


    @After
    public void tearDown() throws Exception {


        assertEquals(page, PageManager.getProperty("path.page.editSubjects"));
    }

}