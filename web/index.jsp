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
        <title><fmt:message key="index.title"/></title>
    </head>
    <body>
    <c:if test="${not empty sessionScope.user}">
        <jsp:forward page="/jsp/menu.jsp"/>
    </c:if>

    <c:if test="${empty sessionScope.user}">
        <jsp:forward page="/jsp/login.jsp"/>
    </c:if>



    <div align="center">
        <h1><fmt:message key="index.caption"/></h1>
        <a href="/controller?command=login"><fmt:message key="index.login"/></a><br>
        <a href="/controller?command=register"><fmt:message key="index.registration"/></a>
    </div>
    </body>
    </html>

</fmt:bundle>
