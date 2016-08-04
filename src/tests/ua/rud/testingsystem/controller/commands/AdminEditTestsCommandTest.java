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
public class AdminEditTestsCommandTest {

    @Test
    public void executeTest() throws Exception {

        /*Do nothing when wrapper.setSessionAttribute*/
        RequestWrapper wrapper = Mockito.mock(RequestWrapper.class);
        Mockito.doNothing().when(wrapper).setSessionAttribute(Mockito.any(), Mockito.any());

        /*Return empty List on SubjectUtils.getSubjects*/
        PowerMockito.mockStatic(SubjectUtils.class);
        PowerMockito.doReturn(new ArrayList<>()).when(SubjectUtils.class);
        SubjectUtils.getSubjects();

        String expectedPage = PageManager.getProperty("path.page.editTests");
        String actualPage = new AdminEditTestsCommand().execute(wrapper);

        Assert.assertEquals(expectedPage, actualPage);
    }
}