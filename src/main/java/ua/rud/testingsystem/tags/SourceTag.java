package ua.rud.testingsystem.tags;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.TagSupport;
import java.io.IOException;

public class SourceTag extends TagSupport {
    private String value;

    public void setValue(String value) {
        this.value = value;
    }

    @Override
    public int doStartTag() throws JspException {
        value = value.replaceAll("\\n", "<br>");
        value = value.replaceAll("\\t", "&nbsp;&nbsp;&nbsp;&nbsp;");
        value = value.replaceAll("\\s{2}", "&nbsp;");
        value = value.replaceAll("<", "&lt;");
        value = value.replaceAll(">", "&gt;");


        try {
            pageContext.getOut().print(value);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return SKIP_BODY;
    }
}