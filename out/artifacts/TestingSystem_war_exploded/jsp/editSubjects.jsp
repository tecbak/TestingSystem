<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="ctg" uri="http://customtags.rud.ua" %>

<ctg:lang var="language" param="userlang"/>
<fmt:setLocale value="${language}"/>
<fmt:bundle basename="ua/rud/testingsystem/resource/messages">

    <%--Page caption--%>
    <div id="center">
        <h1><fmt:message key="editSubjects.caption"/></h1>
    </div>

    <%--Add caption--%>
    <div id="center">
        <h2><fmt:message key="editSubjects.addSubject"/></h2>
    </div>

    <%--Add form--%>
    <div id="left">
        <form action="controller" method="post">
            <input type="hidden" name="command" value="adminSaveSubject"/>
            <input type="hidden" name="token" value="${sessionScope.token}"/>
            <h3><fmt:message key="editSubjects.newSubject"/>:</h3>
            <input type="text" name="name" value=""/>
            <fmt:message var="add" key="editSubjects.add"/>
            <input type="submit" name="add" value="${add}">
                ${addSubjectMessage}
        </form>
    </div>

    <hr>

    <%--Delete caption--%>
    <div id="center">
        <h2><fmt:message key="editSubjects.deleteSubject"/></h2>
    </div>

    <%--Delete form--%>
    <div id="left">
        <form action="controller" method="post">
            <input type="hidden" name="command" value="adminDeleteSubject">
            <input type="hidden" name="token" value="${sessionScope.token}"/>
            <c:forEach var="subject" items="${sessionScope.subjects}">
                <p>
                    <input type="checkbox" name="subjectId" value="${subject.id}"/>
                    <c:out value="${subject.name}"/>
                </p>
            </c:forEach>
            <fmt:message var="delete" key="editSubjects.delete"/>
            <input type="submit" name="delete" value="${delete}"> ${deleteSubjectMessage}
        </form>
    </div>

    <hr>

    <%--Back to admin menu link--%>
    <div id="left">
        <p><a href="controller?command=adminMenu&token=${sessionScope.token}">
            <fmt:message key="editSubjects.back"/>
        </a></p>
    </div>

</fmt:bundle>