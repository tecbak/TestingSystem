package ua.rud.testingsystem.tags;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpSession;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.TagSupport;
import java.util.Locale;


public class LanguageTag extends TagSupport {
    // Name of session's attribute storing value of locale (session's locale attribute)
    private String var;
    // Name of request parameter storing value of new defined language
    private String param;

    /*Setters*/
    public void setVar(String var) {
        this.var = var;
    }

    public void setParam(String param) {
        this.param = param;
    }

    /*Methods*/

    @Override
    public int doStartTag() throws JspException {
        HttpSession session = pageContext.getSession();
        ServletRequest request = pageContext.getRequest();

        String language = request.getParameter(param);

        /*
         * If language is defined in request parameter -
         * set session's locale attribute value from the parameter
         */
        if (language != null && !language.isEmpty()) {
            Locale locale = new Locale(language);
            session.setAttribute(var, locale);

        /*
         * If session's locale attribute value hasn't been defined yet -
         * use request's locale
         */
        } else if (session.getAttribute(var) == null) {
            Locale locale = request.getLocale();
            session.setAttribute(var, locale);
        }

        /*
        Otherwise, the session's locale attribute value won't be changed
         */

        return SKIP_BODY;
    }
}

