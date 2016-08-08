<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<fmt:bundle basename="ua/rud/testingsystem/resource/config" prefix="path.page.">
    <fmt:message var="page" key="error" scope="session"/>
    <jsp:forward page="frame.jsp"/>
</fmt:bundle>