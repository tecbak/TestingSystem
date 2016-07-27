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
        <title><fmt:message key="login.title"/></title>
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
                    <c:if test="${not empty sessionScope.user}">
                        Logout
                    </c:if>
                </td>
                <td align="right">
                    <a href="controller?command=guestRegister&userlang=en">ENG</a>
                    <a href="controller?command=guestRegister&userlang=ru">РУC</a><br/>
                </td>
            </tr>
            <tr align="center">
                <td colspan="2">
                        <%--template--%>

                    <h1><fmt:message key="register.caption"/></h1>
                    <form action="controller" method="post">
                        <input type="hidden" name="command" value="guestRegistration">
                        <table>
                            <tr>
                                <td><fmt:message key="register.login"/></td>
                                <td><input type="text" name="login" value="${param.login}"/></td>
                            </tr>
                            <tr>
                                <td><fmt:message key="register.password"/></td>
                                <td><input type="password" name="password0"/></td>
                            </tr>
                            <tr>
                                <td><fmt:message key="register.confirmPassword"/></td>
                                <td><input type="password" name="password1"/></td>
                            </tr>
                            <tr>
                                <td><fmt:message key="register.firstName"/></td>
                                <td><input type="text" name="firstName" value="${param.firstName}"/></td>
                            </tr>
                            <tr>
                                <td><fmt:message key="register.lastName"/></td>
                                <td><input type="text" name="lastName" value="${param.lastName}"/></td>
                            </tr>
                            <tr>
                                <td><fmt:message key="register.email"/></td>
                                <td><input type="email" name="email" value="${param.email}"/></td>
                            </tr>
                            <tr>
                                <fmt:message key="register.submit" var="submit"/>
                                <td colspan="2" align="center"><input type="submit" value="${submit}"/></td>
                            </tr>
                        </table>
                    </form>
                    <br/>
                        ${registrationMessage}
                    <br/><br/>
                                <%--template--%>

                </td>
            </tr>
            <tr align="center">
                <td colspan="2">
                    <fmt:message key="register.registered"/>
                    <a href="controller?command=guestLogin">
                        <fmt:message key="register.signin"/>
                    </a>
                </td>
            </tr>
        </table>
    </div>
    </body>
    </html>

</fmt:bundle>
