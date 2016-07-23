<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="ctg" uri="http://customtags.rud.ua" %>

<ctg:lang var="language" param="userlang"/>
<fmt:setLocale value="${language}"/>
<fmt:bundle basename="ua/rud/testingsystem/resource/messages">

    <!DOCTYPE html>
    <html lang="${language}">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
        <style type="text/css">
            table {
                font-family: Arial;
            }
        </style>
        <title><fmt:message key="login.title"/></title>
    </head>

    <body>
    <div align="center">
        <table width="600px">  <%-- frame="border"--%>
            <tr>
                <td align="left">
                    <c:if test="${not empty sessionScope.user}">
                        Logout
                    </c:if>
                </td>
                <td align="right">
                    <a href="controller?command=login&userlang=en">ENG</a>
                    <a href="controller?command=login&userlang=ru">РУC</a><br>
                </td>
            </tr>
            <tr align="center">
                <td colspan="2">

                    <h1><fmt:message key="login.caption"/></h1>

                    <form action="controller" method="get">
                        <input type="hidden" name="command" value="authorization">
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
                                    <input type="submit" name="send" value="${submit}"/>
                                </td>
                            </tr>
                        </table>
                    </form>

                    <br/>
                        ${errorLoginPassMessage}
                    <br/><br/>
                </td>
            </tr>
            <tr align="center">
                <td colspan="2">
                    <fmt:message key="login.noAccount"/>
                    <a href="controller?command=register">
                        <fmt:message key="login.register"/>
                    </a>
                </td>
            </tr>
        </table>
    </div>
    </body>
    </html>

</fmt:bundle>