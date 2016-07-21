package ua.rud.testingsystem.filters;

import javax.servlet.*;
import java.io.IOException;

/**
 * Redirects to index.jsp all attempts of direct access to JSPs
 */
public class DirectAccessFilter implements Filter {
    private String indexJsp;

    @Override
    public void init(FilterConfig config) throws ServletException {
        indexJsp = config.getInitParameter("indexJsp");
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
//        HttpServletRequest httpRequest = (HttpServletRequest) request;
//        HttpServletResponse httpResponse = (HttpServletResponse) response;
//        httpResponse.sendRedirect(httpRequest.getContextPath() + indexJsp);
//        chain.doFilter(request, response);

        RequestDispatcher dispatcher = request.getRequestDispatcher(indexJsp);
        dispatcher.forward(request, response);
    }

    @Override
    public void destroy() {
    }
}
