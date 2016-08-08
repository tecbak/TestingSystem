<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="ctg" uri="http://customtags.rud.ua" %>

<c:set var="navigation" value="test"/>
<ctg:lang var="language" param="userlang"/>
<fmt:setLocale value="${language}"/>
<fmt:bundle basename="ua/rud/testingsystem/resource/messages">

    <!DOCTYPE html>
    <html lang="${language}">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
        <title><fmt:message key="common.title"/></title>
        <style type="text/css">
            table {
                font-family: Arial;
                margin: auto;
            }

            #left {
                text-align: left;
            }

            #right {
                text-align: right;
            }

            #center {
                text-align: center;
            }

            .red {
                color: red;
            }

            .black {
                color: black;
            }

            .green {
                color: limegreen;
            }
        </style>
    </head>

    <body>
    <table width="600px">
        <tr>

                <%--Logout and Administration links--%>
            <td id="left">
                <c:if test="${not empty sessionScope.user}">
                    <a href="controller?command=logout"><fmt:message key="common.logout"/></a>

                    <c:if test="${sessionScope.user.role.toString() == 'ADMIN'}">
                        <a href="controller?command=adminMenu"> <fmt:message key="common.administration"/></a>
                    </c:if>
                </c:if>
            </td>

                <%--Languages--%>
            <td id="right">
                <a href="controller?command=allRefresh&userlang=en">ENG</a>
                <a href="controller?command=allRefresh&userlang=ru">РУC</a><br>
            </td>
        </tr>

            <%--Content--%>
        <tr>
            <td colspan="2">
                <jsp:include page="${sessionScope.page}"/>
            </td>
        </tr>

    </table>
    </body>
    </html>

</fmt:bundle>