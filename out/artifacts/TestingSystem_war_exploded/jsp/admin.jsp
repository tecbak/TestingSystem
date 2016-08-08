<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="ctg" uri="http://customtags.rud.ua" %>

<ctg:lang var="language" param="userlang"/>
<fmt:setLocale value="${language}"/>
<fmt:bundle basename="ua/rud/testingsystem/resource/messages">

    <%--Page caption--%>
    <div id="center">
        <h1><fmt:message key="admin.caption"/></h1><br/>
    </div>

    <div id="left">

            <%--Edit subjects link--%>
        <p><a href="controller?command=adminEditSubjects">
            <fmt:message key="admin.editSubjects"/>
        </a></p>

            <%--Edit tests link--%>
        <p><a href="controller?command=adminEditTests">
            <fmt:message key="admin.editTests"/>
        </a></p>

        <hr/>

            <%--Back to main menu link--%>
        <p><a href="controller?command=menu">
            <fmt:message key="admin.back"/>
        </a></p>

    </div>


</fmt:bundle>