<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="ctg" uri="http://customtags.rud.ua" %>

<ctg:lang var="language" param="userlang"/>
<fmt:setLocale value="${language}"/>
<fmt:bundle basename="ua/rud/testingsystem/resource/messages">

    <%--Page caption--%>
    <div id="center">
        <h1><fmt:message key="addTest.caption"/></h1>
    </div>

    <%--Caption of new test--%>
    <div id="center">
        <h2><c:out value="${sessionScope.newTest.caption}"/></h2>
    </div>

    <%--Buttons save/cancel--%>
    <div id="center">
        <table>
            <tr>
                <td>
                    <fmt:message key="addTest.save" var="save"/>
                    <form action="controller" method="post">
                        <input type="hidden" name="command" value="adminSaveTest"/>
                        <input type="hidden" name="save" value="1"/>
                        <input type="hidden" name="token" value="${sessionScope.token}"/>
                        <input type="submit" name="save" value="${save}">
                    </form>
                </td>
                <td>
                    <fmt:message key="addTest.cancel" var="cancel"/>
                    <form action="controller" method="post">
                        <input type="hidden" name="command" value="adminSaveTest"/>
                        <input type="hidden" name="save" value="0"/>
                        <input type="hidden" name="token" value="${sessionScope.token}"/>
                        <input type="submit" name="cancel" value="${cancel}">
                    </form>
                </td>
            </tr>
        </table>
        <br/>
            ${saveTestMessage}
    </div>

    <%--Already created questions--%>
    <div id="left">
        <c:forEach var="question" items="${sessionScope.newTest.questions}">

            <%--Task--%>
            <h3><c:out value="${question.task}"/></h3>

            <%--Answers--%>
            <c:forEach var="answer" items="${question.answers}">

                <%--Define if checkbox is checked--%>
                <c:choose>
                    <c:when test="${answer.correct}">
                        <c:set var="checked" value="checked"/>
                    </c:when>
                    <c:otherwise>
                        <c:set var="checked" value=""/>
                    </c:otherwise>
                </c:choose>

                <%--Checkbox--%>
                <input type="checkbox" name="id" value="${answer.id}" ${checked} disabled/>

                <%--Answer's text--%>
                <c:out value="${answer.text}"/><br/>
            </c:forEach>

        </c:forEach>
    </div>

    <%--Form for new question--%>
    <div id="left">
        <form action="controller" method="post">
            <input type="hidden" name="command" value="adminAddQuestion"/>
            <input type="hidden" name="token" value="${sessionScope.token}"/>

                <%--"New question" caption--%>
            <h3><fmt:message key="addTest.newQuestion"/>:</h3>

                <%--Text area for new question's task--%>
            <textarea name="task" rows="3" cols="80"></textarea> <%--${param.task}--%>

                <%--"Answers" caption--%>
            <h3><fmt:message key="addTest.answers"/>:</h3>

                <%--Text areas for upto 10 answers--%>
            <p>
                <c:forEach begin="0" end="9" varStatus="loop">
                    <input type="checkbox" name="answerId" value="${loop.index}">
                    <textarea name="text" rows="1" cols="75"></textarea><br/>
                </c:forEach>
            </p>

                <%--"Add question" button--%>
            <fmt:message key="addTest.addQuestion" var="addQuestion"/>
            <input type="submit" name="addQuestion" value="${addQuestion}"/> ${addQuestionMessage}
        </form>
    </div>

    <hr>

    <%--Back to "edit tests" menu link--%>
    <p><a href="controller?command=adminMenu">
        <fmt:message key="addTest.back"/>
    </a></p>

</fmt:bundle>