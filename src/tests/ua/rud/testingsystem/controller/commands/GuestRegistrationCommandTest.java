package ua.rud.testingsystem.controller.commands;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mockito;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;
import ua.rud.testingsystem.controller.RequestWrapper;
import ua.rud.testingsystem.entities.user.UserUtils;
import ua.rud.testingsystem.resource.MessageManager;
import ua.rud.testingsystem.resource.PageManager;

import javax.servlet.ServletException;
import java.util.Locale;


@RunWith(PowerMockRunner.class)
@PrepareForTest(UserUtils.class)
public class GuestRegistrationCommandTest {
    RequestWrapper wrapper = Mockito.mock(RequestWrapper.class);

    @Before
    public void setUp() throws Exception {

        /*Wrapper standard behaviour*/
        Mockito.when(wrapper.getRequestParameter("login")).thenReturn("login");
        Mockito.when(wrapper.getRequestParameter("password0")).thenReturn("password");
        Mockito.when(wrapper.getRequestParameter("password1")).thenReturn("password");
        Mockito.when(wrapper.getRequestParameter("firstName")).thenReturn("firstName");
        Mockito.when(wrapper.getRequestParameter("lastName")).thenReturn("lastName");
        Mockito.when(wrapper.getRequestParameter("email")).thenReturn("email@email.com");
        Mockito.when(wrapper.getSessionLanguage()).thenReturn(Locale.ENGLISH);

        /*Spy UserUtils*/
        PowerMockito.spy(UserUtils.class);
        PowerMockito.doReturn(true).when(UserUtils.class);
        UserUtils.isLoginUnique(Mockito.any());

        PowerMockito.doReturn(true).when(UserUtils.class);
        UserUtils.isEmailUnique(Mockito.any());

        PowerMockito.doNothing().when(UserUtils.class);
        UserUtils.saveUser(Mockito.any(), Mockito.any());


    }

    @Test
    public void onAnythingIsNotFilled_reportAboutIt() throws ServletException {
        PowerMockito.doReturn(false).when(UserUtils.class);
        UserUtils.isFilled(Mockito.anyVararg());


        /*Verify returned page*/
        String expectedPage = PageManager.getProperty("path.page.register");
        String actualPage = new GuestRegistrationCommand().execute(wrapper);
        Assert.assertEquals(expectedPage, actualPage);

        /*Verify message*/
        ArgumentCaptor<String> s = ArgumentCaptor.forClass(String.class);
        ArgumentCaptor<Object> o = ArgumentCaptor.forClass(Object.class);
        Mockito.verify(wrapper).setRequestAttribute(s.capture(), o.capture());
        String message = MessageManager.getProperty("register.emptyFields", Locale.ENGLISH);

        Assert.assertEquals(message, o.getValue());
    }

    @Test
    public void onPasswordsMismatch_reportAboutIt() throws ServletException {
        Mockito.when(wrapper.getRequestParameter("password0")).thenReturn("a");
        Mockito.when(wrapper.getRequestParameter("password1")).thenReturn("b");

        /*Verify returned page*/
        String expectedPage = PageManager.getProperty("path.page.register");
        String actualPage = new GuestRegistrationCommand().execute(wrapper);
        Assert.assertEquals(expectedPage, actualPage);

        /*Verify message*/
        ArgumentCaptor<String> s = ArgumentCaptor.forClass(String.class);
        ArgumentCaptor<Object> o = ArgumentCaptor.forClass(Object.class);
        Mockito.verify(wrapper).setRequestAttribute(s.capture(), o.capture());
        String message = MessageManager.getProperty("register.passwordsMismatch", Locale.ENGLISH);

        Assert.assertEquals(message, o.getValue());
    }

    @Test
    public void onLoginExists_reportAboutIt() throws ServletException {
        PowerMockito.doReturn(false).when(UserUtils.class);
        UserUtils.isLoginUnique(Mockito.any());

        /*Verify returned page*/
        String expectedPage = PageManager.getProperty("path.page.register");
        String actualPage = new GuestRegistrationCommand().execute(wrapper);
        Assert.assertEquals(expectedPage, actualPage);

        /*Verify message*/
        ArgumentCaptor<String> s = ArgumentCaptor.forClass(String.class);
        ArgumentCaptor<Object> o = ArgumentCaptor.forClass(Object.class);
        Mockito.verify(wrapper).setRequestAttribute(s.capture(), o.capture());
        String message = MessageManager.getProperty("register.loginExists", Locale.ENGLISH);

        Assert.assertEquals(message, o.getValue());
    }

    @Test
    public void onEmailExists_reportAboutIt() throws ServletException {
        PowerMockito.doReturn(false).when(UserUtils.class);
        UserUtils.isEmailUnique(Mockito.any());

        /*Verify returned page*/
        String expectedPage = PageManager.getProperty("path.page.register");
        String actualPage = new GuestRegistrationCommand().execute(wrapper);
        Assert.assertEquals(expectedPage, actualPage);

        /*Verify message*/
        ArgumentCaptor<String> s = ArgumentCaptor.forClass(String.class);
        ArgumentCaptor<Object> o = ArgumentCaptor.forClass(Object.class);
        Mockito.verify(wrapper).setRequestAttribute(s.capture(), o.capture());
        String message = MessageManager.getProperty("register.emailExists", Locale.ENGLISH);

        Assert.assertEquals(message, o.getValue());
    }

    @Test
    public void onEmailInvalid_reportAboutIt() throws ServletException {
        Mockito.when(wrapper.getRequestParameter("email")).thenReturn("invalid_email");

        /*Verify returned page*/
        String expectedPage = PageManager.getProperty("path.page.register");
        String actualPage = new GuestRegistrationCommand().execute(wrapper);
        Assert.assertEquals(expectedPage, actualPage);

        /*Verify message*/
        ArgumentCaptor<String> s = ArgumentCaptor.forClass(String.class);
        ArgumentCaptor<Object> o = ArgumentCaptor.forClass(Object.class);
        Mockito.verify(wrapper).setRequestAttribute(s.capture(), o.capture());
        String message = MessageManager.getProperty("register.emailInvalid", Locale.ENGLISH);

        Assert.assertEquals(message, o.getValue());
    }

    @Test
    public void executeTest() throws ServletException {


        /*Verify returned page*/
        String expectedPage = PageManager.getProperty("path.page.register");
        String actualPage = new GuestRegistrationCommand().execute(wrapper);
        Assert.assertEquals(expectedPage, actualPage);

        /*Verify save of new user*/
        PowerMockito.verifyStatic();
        UserUtils.saveUser(Mockito.any(), Mockito.any());

        /*Verify message*/
        ArgumentCaptor<String> s = ArgumentCaptor.forClass(String.class);
        ArgumentCaptor<Object> o = ArgumentCaptor.forClass(Object.class);
        Mockito.verify(wrapper).setRequestAttribute(s.capture(), o.capture());
        String message = MessageManager.getProperty("register.success", Locale.ENGLISH);

        Assert.assertEquals(message, o.getValue());
    }

}