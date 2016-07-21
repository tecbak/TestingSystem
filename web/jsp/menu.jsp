<%@ page contentType="text/html;charset=UTF-8" language="java" %>
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
                    <a href="controller?command=menu&language=en">ENG</a>
                    <a href="controller?command=menu&language=ru">РУC</a><br>
                </td>
            </tr>
            <tr align="left">
                <td colspan="2">
                        <%--template--%>
                    <br>
                        ${sessionScope.user}<br>
                    id = ${sessionScope.user.id}<br>
                    login = ${sessionScope.user.login}<br>
                    firstname = ${sessionScope.user.firstName}<br>
                    lastname = ${sessionScope.user.lastName}<br>
                    email = ${sessionScope.user.email}<br>
                    role = ${sessionScope.user.role}<br>

                        <%--template--%>
                </td>
            </tr>
        </table>
    </div>
    </body>
    </html>

</fmt:bundle>