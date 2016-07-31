package ua.rud.testingsystem.controller.commands;

import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mockito;
import ua.rud.testingsystem.controller.RequestWrapper;
import ua.rud.testingsystem.resource.MessageManager;

import java.util.Locale;

import static org.junit.Assert.assertEquals;

public class AdminAddTestCommandTest extends Mockito {

    @Test(expected = NullPointerException.class)
    public void shouldThrowNullPointerExceptionWithNullParameter() throws Exception {
        String name = new AdminAddTestCommand().execute(null);
    }

    @Test
    public void onCaptionNull_reportsAboutIt() throws Exception {

        RequestWrapper rw = mock(RequestWrapper.class);
        when(rw.getRequestParameter(eq("caption"))).thenReturn("");
        when(rw.getSessionLanguage()).thenReturn(Locale.ENGLISH);

        ArgumentCaptor<String> key = ArgumentCaptor.forClass(String.class);
        ArgumentCaptor<String> value = ArgumentCaptor.forClass(String.class);

        String response = new AdminAddTestCommand().execute(rw);

        verify(rw, times(1)).getRequestParameter(eq("caption"));
        verify(rw).setRequestAttribute(key.capture(), value.capture());

        assertEquals("addTestMessage", key.getValue());
        assertEquals(MessageManager.getProperty("editTests.emptyCaption", Locale.ENGLISH), value.getValue());

    }

}