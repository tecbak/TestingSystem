package ua.rud.testingsystem.controller.filters;

import javax.servlet.*;
import java.io.IOException;

/**
 * Changes encoding if it doesn't match the specified one
 */
public class EncodingFilter implements Filter {
    private String code;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        code = filterConfig.getInitParameter("encoding");
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        String codeRequest = request.getCharacterEncoding();

        // TODO: 19.07.2016 purpose of code != null?
        if (code != null && !code.equalsIgnoreCase(codeRequest)) {
            request.setCharacterEncoding(code);
            response.setCharacterEncoding(code);
        }

        chain.doFilter(request, response);
    }

    @Override
    public void destroy() {
        code = null;
    }
}
