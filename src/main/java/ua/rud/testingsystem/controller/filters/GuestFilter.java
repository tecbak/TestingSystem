package ua.rud.testingsystem.controller.filters;

import ua.rud.testingsystem.entities.user.User;
import ua.rud.testingsystem.entities.user.UserRole;
import ua.rud.testingsystem.managers.PageManager;

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
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        String command = request.getParameter("command");
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpSession session = httpRequest.getSession();
        User user = (User) session.getAttribute("user");


        if (isCommandMatchUser(command, user)) {
            chain.doFilter(request, response);
        } else {
            RequestDispatcher dispatcher = request.getRequestDispatcher(indexJsp);
            dispatcher.forward(request, response);
        }
    }

//    @Override
//    public void destroy() {
//    }

    /**
     * If command's name starts with prefix:
     * "all" - this command is available for all users
     * "guest" - available only for unauthorized users
     * "admin" - available only for administrator
     * "user"- for any authorized user
     *
     * @param command invoked by user
     * @param user who has invoked a command
     * @return {@code true} if user rights to invoke the command,
     * {@code false} otherwise
     */
    private boolean isCommandMatchUser(String command, User user) {
        /*Return false if command is null*/
        if (command == null) {
            return false;
        }

        /*Return true if command is for all users*/
        if (command.startsWith("all")) {
            return true;
        }

        /*Return true if "guest" command is applied by unauthorized user*/
        if (command.startsWith("guest")) {
            return user == null;
        }

        /*Return true if "user" command is applied by authorized user*/
        if (command.startsWith("user")) {
            return user != null;
        }

        /*Return true if "admin" command is applied by admin*/
        if (command.startsWith("admin")) {
            return (user != null) && (user.getRole() == UserRole.ADMIN);
        }

        /*Return false if command has no appropriate user identifier*/
        return false;
    }
}
