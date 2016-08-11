<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<fmt:bundle basename="ua/rud/testingsystem/resource/config" prefix="path.page.">

    <c:choose>
        <c:when test="${empty sessionScope.token}">

        </c:when>
    </c:choose>

    <c:choose>
        <c:when test="${empty sessionScope.user}">
            <%--Load login page if user isn't authorized--%>
            <fmt:message var="page" key="login" scope="session"/>
        </c:when>
        <c:otherwise>
            <%--Load menu page if user is authorized--%>
            <fmt:message var="page" key="menu" scope="session"/>
        </c:otherwise>
    </c:choose>

    <%--Redirect to frame.jsp--%>
    <fmt:message var="frame" key="frame"/>
    <jsp:forward page="${frame}"/>

</fmt:bundle>