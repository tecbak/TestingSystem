<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="ctg" uri="http://customtags.rud.ua" %>

<ctg:lang var="language" param="userlang"/>
<fmt:setLocale value="${language}"/>
<fmt:bundle basename="ua/rud/testingsystem/resource/messages">

    <%--Page caption--%>
    <div id="center">
        <h2><fmt:message key="error.caption"/></h2>
    </div>

    <%--Continue link--%>
    <div id="center">
        <a href="controller?command=allIndex">
            <fmt:message key="error.continue"/>
        </a>
    </div>

</fmt:bundle>