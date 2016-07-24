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
        <title><fmt:message key="menu.title"/></title>
        <style type="text/css">
            table {
                font-family: Arial;
            }
        </style>
    </head>
    <body>
    <div align="center">
        <table width="600px">  <%-- frame="border"--%>
            <tr>
                <td align="left">
                    <a href="controller?command=logout"><fmt:message key="common.logout"/></a>
                    <%--<a href="controller?command=results"> <fmt:message key="common.results"/></a>--%>
                    <c:if test="${sessionScope.user.role.toString() == 'ADMIN'}">
                        <a href="controller?command=admin"> <fmt:message key="common.administration"/></a>
                    </c:if>
                </td>
                <td align="right">
                    <a href="controller?command=menu&userlang=en">ENG</a>
                    <a href="controller?command=menu&userlang=ru">РУC</a><br>
                </td>
            </tr>
            <tr align="left">
                <td colspan="2">
                        <%--template--%>
                    <div align="center">
                        <h1><fmt:message key="menu.availableTests"/></h1><br/>
                    </div>

                    <div align="left">
                        <form action="controller" method="get">
                            <input type="hidden" name="command" value="start">

                            <c:forEach var="subject" items="${sessionScope.subjects}">

                                <h2><c:out value="${subject.name}"/></h2>

                                <c:forEach var="entry" items="${subject.tests.entrySet()}">
                                    <input type="radio" name="id" value="${entry.getKey()}"/>
                                    <c:out value="${entry.getValue()}"/>
                                    <br/>
                                </c:forEach>
                                <br/>
                            </c:forEach>

                            <fmt:message key="menu.start" var="start"/>
                            <input type="submit" name="select" value="${start}">

                        </form>
                    </div>
                        <%--template--%>
                </td>
            </tr>
        </table>
    </div>
    </body>
    </html>

</fmt:bundle>