<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page isErrorPage="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<c:set var="language"
       value="${not empty param.language ? param.language : not empty language ? language : pageContext.request.locale}"
       scope="session"/>
<fmt:setLocale value="${language}"/>
<fmt:bundle basename="ua/rud/testingsystem/resource/messages">

    <!DOCTYPE html>
    <html lang="${language}">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
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
                    <a href="controller?command=login&language=en">ENG</a>
                    <a href="controller?command=login&language=ru">РУC</a><br>
                </td>
            </tr>
            <tr align="center">
                <td colspan="2">
                        <%--template--%>
                            <div align="center">
                                <h1>Error</h1>
                                <input type="button" onclick="history.back()" value="Вернуться назад">
                            </div>
                        <%--template--%>
                </td>
            </tr>
        </table>
    </div>
    </body>
    </html>

</fmt:bundle>