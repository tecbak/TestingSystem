<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="ctg" uri="http://customtags.rud.ua" %>

<ctg:lang var="language" param="userlang"/>
<fmt:setLocale value="${language}"/>
<fmt:bundle basename="ua/rud/testingsystem/resource/messages">

    <%--Caption--%>
    <div id="center">
        <h1><fmt:message key="editTests.caption"/></h1>
    </div>

    <%--Add test caption--%>
    <div id="center">
        <h2><fmt:message key="editTests.addTest"/></h2>
    </div>
    <%--Add test form--%>
    <div id="left">
        <form action="controller" method="post">
            <input type="hidden" name="command" value="adminAddTest"/>
            <input type="hidden" name="token" value="${sessionScope.token}"/>

                <%--List of subjects--%>
            <h3><fmt:message key="editTests.chooseSubject"/>:</h3>
            <c:forEach var="subject" items="${sessionScope.subjects}">
                <input type="radio" name="subjectId" value="${subject.id}">
                <c:out value="${subject.name}"/><br/>
            </c:forEach>

                <%--Enter text's caption--%>
            <h3><fmt:message key="editTests.enterCaption"/>:</h3>
            <p>
                <input type="text" name="caption" size="80" value="${param.caption}"/>
            </p>

            <fmt:message key="editTests.add" var="add"/>
            <input type="submit" name="add" value="${add}"> ${addTestMessage}
        </form>
    </div>
    <hr>

    <%--Delete test caption--%>
    <div id="center">
        <h2><fmt:message key="editTests.deleteTest"/></h2>
    </div>

    <%--Delete test form--%>
    <div id="left">
        <form action="controller" method="post">
            <input type="hidden" name="command" value="adminDeleteTest"/>
            <input type="hidden" name="token" value="${sessionScope.token}"/>
            <c:forEach var="subject" items="${sessionScope.subjects}">
                <h3><c:out value="${subject.name}"/></h3>

                <%--Output of tests--%>
                <c:forEach var="entry" items="${subject.tests.entrySet()}">
                    <c:set var="testId" value="${entry.getKey()}"/>
                    <c:set var="testCaption" value="${entry.getValue()}"/>

                    <input type="checkbox" name="testId" value="${testId}"/>
                    <c:out value="${testCaption}"/><br/>
                </c:forEach>
            </c:forEach>
            <br/>
            <fmt:message var="delete" key="editTests.delete"/>
            <input type="submit" name="delete" value="${delete}"> ${deleteTestMessage}
        </form>
    </div>

    <hr>

    <p><a href="controller?command=adminMenu">
        <fmt:message key="editTests.back"/>
    </a></p>

</fmt:bundle>