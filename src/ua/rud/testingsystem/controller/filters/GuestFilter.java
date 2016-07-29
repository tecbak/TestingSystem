package ua.rud.testingsystem.controller.filters;

import ua.rud.testingsystem.entities.user.User;
import ua.rud.testingsystem.entities.user.UserRole;
import ua.rud.testingsystem.resource.PageManager;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * Prevents from unauthorized access
 */
public class GuestFilter implements Filter {
    private String indexJsp;

    @Override
    public void init(FilterConfig config) throws ServletException {
        indexJsp = PageManager.getProperty("path.page.index");
//        config.getInitParameter("indexJsp");
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        String command = request.getParameter("command");
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpSession session = httpRequest.getSession();
        User user = (User) session.getAttribute("user");

        /*
        * Filter all request without particular command
        */
        if (command == null || command.isEmpty()) {
            RequestDispatcher dispatcher = request.getRequestDispatcher(indexJsp);
            dispatcher.forward(request, response);
        }

        /*
        * These guest commands are available only for guest users
        * and they're the only commands to be invoked by these users
        */

//        boolean guestCommand = command.startsWith("guest");
//                command.equals("register") || command.equals("registration") ||
//                command.equals("authorization") || command.equals("login");

        /*
        * Null user == guest user
        */
//        boolean guestUser = (user == null);
        if (command.startsWith("all")) {
            chain.doFilter(request, response);

        } else if (command.startsWith("guest")) {
            if (user == null) {
                chain.doFilter(request, response);
            } else {
                RequestDispatcher dispatcher = request.getRequestDispatcher(indexJsp);
                dispatcher.forward(request, response);
            }
        } else if (command.startsWith("admin")) {
            if (user != null && user.getRole() == UserRole.ADMIN) {
                chain.doFilter(request, response);
            } else {
                RequestDispatcher dispatcher = request.getRequestDispatcher(indexJsp);
                dispatcher.forward(request, response);
            }
        } else {
            if (user != null) {
                chain.doFilter(request, response);
            } else {
                RequestDispatcher dispatcher = request.getRequestDispatcher(indexJsp);
                dispatcher.forward(request, response);
            }
        }


//        if (user == null) {
//            if (command.startsWith("guest")) {
//                chain.doFilter(request, response);
//            } else {
//                RequestDispatcher dispatcher = request.getRequestDispatcher(indexJsp);
//                dispatcher.forward(request, response);
//            }
//        } else if (user.getRole() == UserRole.ADMIN) {
//
//        }
//
//
//        if (user == null && command.startsWith("guest")) {
//            chain.doFilter(request, response);
//        } else {
//            RequestDispatcher dispatcher = request.getRequestDispatcher(indexJsp);
//            dispatcher.forward(request, response);
//        }
//    }
//
//        if(user.getRole()==UserRole.ADMIN)
//
//
//        /*
//        * Continue if guest user invokes guest command or
//        * authorized user invokes any other command.
//        * Otherwise - redirect to index.jsp
//        */
//            if((guestCommand ==guestUser))
//
//    {
//        chain.doFilter(request, response);
//
//    } else
//
//    {
//        RequestDispatcher dispatcher = request.getRequestDispatcher(indexJsp);
//        dispatcher.forward(request, response);
//    }
//        /*Continue if user is authorized or user wants to register,
//        * otherwise filter redirects to index page*/
//        if (user != null ||                     // check if user is authorized
//                (command != null &&             // check if there is any command and if the command is either "register", "registration" or "authorization"
//                        (command.equals("register") || command.equals("registration") || command.equals("authorization")))) {
//            chain.doFilter(request, response);
//        } else {
//            RequestDispatcher dispatcher = request.getRequestDispatcher(indexJsp);
//            dispatcher.forward(request, response);
//        }
    }

    @Override
    public void destroy() {
    }
}
