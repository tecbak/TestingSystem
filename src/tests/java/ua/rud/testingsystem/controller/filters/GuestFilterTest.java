package ua.rud.testingsystem.controller.filters;

import org.junit.Before;
import org.junit.experimental.theories.DataPoints;
import org.junit.experimental.theories.FromDataPoints;
import org.junit.experimental.theories.Theories;
import org.junit.experimental.theories.Theory;
import org.junit.runner.RunWith;
import ua.rud.testingsystem.entities.user.User;
import ua.rud.testingsystem.entities.user.UserRole;
import ua.rud.testingsystem.managers.PageManager;

import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.*;

@RunWith(Theories.class)
public class GuestFilterTest {
    private static final User USER = new User() {{
        setRole(UserRole.USER);
    }};
    private static final User ADMIN = new User() {{
        setRole(UserRole.ADMIN);
    }};
    private static final User GUEST = null;

    private static final String All_COMMAND = "allAnyCommand";
    private static final String GUEST_COMMAND = "guestAnyCommand";
    private static final String USER_COMMAND = "userAnyCommand";
    private static final String ADMIN_COMMAND = "adminAnyCommand";
    private static final String INCORRECT_COMMAND = "incorrectAnyCommand";


    @DataPoints("expectForwardToIndex")
    public static Object[][] data0 = new Object[][]{
            {null, GUEST},
            {null, USER},
            {null, ADMIN},

            {GUEST_COMMAND, USER},
            {GUEST_COMMAND, ADMIN},

            {USER_COMMAND, GUEST},

            {ADMIN_COMMAND, GUEST},
            {ADMIN_COMMAND, USER},

            {INCORRECT_COMMAND, GUEST},
            {INCORRECT_COMMAND, USER},
            {INCORRECT_COMMAND, ADMIN},
    };

    @DataPoints("expectDoChain")
    public static Object[][] data1 = new Object[][]{
            {All_COMMAND, GUEST},
            {All_COMMAND, USER},
            {All_COMMAND, ADMIN},

            {GUEST_COMMAND, GUEST},

            {USER_COMMAND, USER},
            {USER_COMMAND, ADMIN},

            {ADMIN_COMMAND, ADMIN},
    };


    private HttpServletRequest request;
    private HttpServletResponse response;
    private FilterChain chain;
    private HttpSession session;
    private FilterConfig config;

    @Before
    public void setUp() throws Exception {
        /*Mock parameters*/
        request = mock(HttpServletRequest.class);
        response = mock(HttpServletResponse.class);
        chain = mock(FilterChain.class);
        config = mock(FilterConfig.class);

        session = mock(HttpSession.class);
        when(request.getSession()).thenReturn(session);
    }

    @Theory
    public void expectForwardToIndex(@FromDataPoints("expectForwardToIndex") Object[] data) throws ServletException, IOException {
        when(request.getParameter(eq("command"))).thenReturn((String) data[0]);
        when(session.getAttribute(eq("user"))).thenReturn(data[1]);

        RequestDispatcher dispatcher = mock(RequestDispatcher.class);
        doNothing().when(dispatcher).forward(request, response);

        String indexJsp = PageManager.getProperty("path.page.index");
        when(request.getRequestDispatcher(eq(indexJsp))).thenReturn(dispatcher);

        GuestFilter filter = new GuestFilter();
        filter.init(config);
        filter.doFilter(request, response, chain);

        verify(dispatcher).forward(request, response);
    }

    @Theory
    public void expectDoChain(@FromDataPoints("expectDoChain") Object[] data) throws ServletException, IOException {
        when(request.getParameter(eq("command"))).thenReturn((String) data[0]);
        when(session.getAttribute(eq("user"))).thenReturn(data[1]);

        doNothing().when(chain).doFilter(request, response);

        GuestFilter filter = new GuestFilter();
        filter.init(config);
        filter.doFilter(request, response, chain);

        verify(chain).doFilter(request, response);
    }

}