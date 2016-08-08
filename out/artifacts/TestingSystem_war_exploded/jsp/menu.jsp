<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="ctg" uri="http://customtags.rud.ua" %>

<ctg:lang var="language" param="userlang"/>
<fmt:setLocale value="${language}"/>
<fmt:bundle basename="ua/rud/testingsystem/resource/messages">

    <%--Page caption--%>
    <div id="center">
        <h1><fmt:message key="menu.availableTests"/></h1><br/>
    </div>

    <%--Menu--%>
    <div id="left">
        <form action="controller" method="get">
            <input type="hidden" name="command" value="start">

            <c:forEach var="subject" items="${sessionScope.subjects}">
                <%--Subject name--%>
                <h3><c:out value="${subject.name}"/></h3>

                <%--Output of tests--%>
                <c:forEach var="entry" items="${subject.tests.entrySet()}">
                    <p>
                        <c:set var="testId" value="${entry.getKey()}"/>
                        <c:set var="testCaption" value="${entry.getValue()}"/>

                        <input type="radio" name="id" value="${testId}"/>
                        <c:out value="${testCaption}"/>

                            <%--If there are any result for the test--%>
                        <c:set var="rates" value="${sessionScope.results[testId]}"/>
                        <c:if test="${rates.size() > 0}">
                            <br/><fmt:message key="menu.results"/>
                            <c:forEach var="rate" items="${rates}">
                                <c:out value="${rate}"/>%
                            </c:forEach>
                            <br/>
                        </c:if>
                    </p>
                </c:forEach>
                <br/>
            </c:forEach>

            <fmt:message key="menu.start" var="start"/>
            <input type="submit" name="start" value="${start}">

        </form>
    </div>

</fmt:bundle>