package ua.rud.testingsystem.controller.filters;

import javax.servlet.*;
import java.io.IOException;

/**
 * Prevents from direct access to JSPs
 */
public class DirectAccessFilter implements Filter {
    private String indexJsp;

    @Override
    public void init(FilterConfig config) throws ServletException {
        indexJsp = config.getInitParameter("indexJsp");
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        RequestDispatcher dispatcher = request.getRequestDispatcher(indexJsp);
        dispatcher.forward(request, response);
    }

    @Override
    public void destroy() {
    }
}
