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
        <title><fmt:message key="register.title"/></title>
    </head>
    <body>
    <div align="center">
        <h1><fmt:message key="register.caption"/></h1>
        <form action="controller" method="post">
            <input type="hidden" name="command" value="registration">
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
    </div>
    </body>
    </html>

</fmt:bundle>