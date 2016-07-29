<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="ctg" uri="http://customtags.rud.ua" %>

<c:set var="navigation" value="allError"/>
<ctg:lang var="language" param="userlang"/>
<fmt:setLocale value="${language}"/>
<fmt:bundle basename="ua/rud/testingsystem/resource/messages">

    <!DOCTYPE html>
    <html lang="${language}">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
        <title><fmt:message key="error.title"/></title>
        <style type="text/css">
            table {
                font-family: Arial;
            }
        </style>
    </head>
    <body>
    <div align="center">
        <table width="600px">
            <tr>
                <td align="left">
                        <%--Logout link--%>
                    <a href="controller?command=logout"><fmt:message key="common.logout"/></a>
                        <%--Administration link--%>
                    <c:if test="${sessionScope.user.role.toString() == 'ADMIN'}">
                        <a href="controller?command=adminMenu"> <fmt:message key="common.administration"/></a>
                    </c:if>
                </td>
                <td align="right">
                        <%--Language links--%>
                    <a href="controller?command=${navigation}&userlang=en">ENG</a>
                    <a href="controller?command=${navigation}&userlang=ru">РУC</a><br>
                </td>
            </tr>
            <tr align="left">
                <td colspan="2">
                        <%--template--%>
                    <div align="center">
                        <h2><fmt:message key="error.caption"/></h2>
                        <fmt:message key="path.page.index" var="index"/>
                        <a href="controller?command=allIndex"><fmt:message key="error.continue"/></a>
                            <%--<input type="button" onclick="history.back()" value="Вернуться назад">--%>
                    </div>
                        <%--template--%>
                </td>
            </tr>
        </table>
    </div>
    </body>
    </html>

</fmt:bundle>