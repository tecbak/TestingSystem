<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<fmt:bundle basename="ua/rud/testingsystem/resource/config" prefix="path.page.">

    <c:choose>

        <%--Load login page if user isn't authorized--%>
        <c:when test="${empty sessionScope.user}">
            <fmt:message var="page" key="login" scope="session"/>
        </c:when>

        <%--Load menu page if user is authorized--%>
        <c:otherwise>
            <fmt:message var="page" key="menu" scope="session"/>
        </c:otherwise>

    </c:choose>

    <%--Redirect to frame.jsp--%>
    <fmt:message var="frame" key="frame"/>
    <jsp:forward page="${frame}"/>

</fmt:bundle>