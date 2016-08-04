package ua.rud.testingsystem.controller.commands;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;
import ua.rud.testingsystem.controller.RequestWrapper;
import ua.rud.testingsystem.entities.subject.SubjectUtils;
import ua.rud.testingsystem.resource.PageManager;

import java.util.ArrayList;

@RunWith(PowerMockRunner.class)
@PrepareForTest(SubjectUtils.class)
public class AdminEditSubjectsCommandTest {
    private RequestWrapper wrapper = Mockito.mock(RequestWrapper.class);

    @Test
    public void executeTest() throws Exception {
        wrapper = Mockito.mock(RequestWrapper.class);

        /*Do nothing on wrapper.setSessionAttribute*/
        Mockito.doNothing().when(wrapper).setSessionAttribute(Mockito.any(), Mockito.any());

        /*Return empty ArrayList on SubjectUtils.getSubjects*/
        PowerMockito.spy(SubjectUtils.class);
        PowerMockito.doReturn(new ArrayList()).when(SubjectUtils.class);
        SubjectUtils.getSubjects();

        String expectedPage = PageManager.getProperty("path.page.editSubjects");
        String actualPage = new AdminEditSubjectsCommand().execute(wrapper);

        Assert.assertEquals(expectedPage,actualPage);

    }
}