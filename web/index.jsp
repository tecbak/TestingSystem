<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="ctg" uri="http://customtags.rud.ua" %>

<fmt:bundle basename="ua/rud/testingsystem/resource/config" prefix="path.page.">
    <%--Redirect to login page if user isn't authorized--%>
    <fmt:message var="login" key="login"/>
    <c:if test="${empty sessionScope.user}">
        <jsp:forward page="${login}"/>
    </c:if>
    <%--Redirect to menu page if user is authorized--%>
    <fmt:message var="menu" key="menu"/>
    <c:if test="${not empty sessionScope.user}">
        <jsp:forward page="${menu}"/>
    </c:if>
</fmt:bundle>



