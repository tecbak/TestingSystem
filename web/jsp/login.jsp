<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="ctg" uri="http://customtags.rud.ua" %>

<ctg:lang var="language" param="userlang"/>
<fmt:setLocale value="${language}"/>
<fmt:bundle basename="ua/rud/testingsystem/resource/messages">

    <%--Page caption--%>
    <div id="center">
        <h1><fmt:message key="login.caption"/></h1>
    </div>

    <%--Login form--%>
    <div id="left">
        <form action="controller" method="post">
            <input type="hidden" name="command" value="guestAuthorization">
            <table>
                <tr>
                    <td><fmt:message key="index.login"/></td>
                    <td><input type="text" name="login" required/></td>
                </tr>
                <tr>
                    <td><fmt:message key="login.password"/></td>
                    <td><input type="password" name="password" required/></td>
                </tr>
                <tr>
                    <td colspan="2" align="center">
                        <fmt:message key="login.submit" var="submit"/>
                        <input type="submit" name="action" value="${submit}"/>
                    </td>
                </tr>
            </table>
        </form>
    </div>

    <%--Message--%>
    <div id="center">
        <br/>
            ${errorLoginPassMessage}
        <br/><br/>
    </div>

    <%--Registration link--%>
    <div id="center">
        <fmt:message key="login.noAccount"/>
        <a href="controller?command=guestRegister">
            <fmt:message key="login.register"/>
        </a>
    </div>

</fmt:bundle>