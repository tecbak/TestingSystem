<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="ctg" uri="http://customtags.rud.ua" %>

<c:set var="navigation" value="adminEditTests"/>
<ctg:lang var="language" param="userlang"/>
<fmt:setLocale value="${language}"/>
<fmt:bundle basename="ua/rud/testingsystem/resource/messages">

    <!DOCTYPE html>
    <html lang="${language}">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
        <title><fmt:message key="menu.title"/></title>
        <style type="text/css">
            table {
                font-family: Arial;
            }
        </style>
    </head>
    <body>
    <div align="center">
        <table width="600px">
            <tr>
                <td align="left">
                        <%--Logout link--%>
                    <a href="controller?command=logout"><fmt:message key="common.logout"/></a>
                        <%--Administration link--%>
                    <c:if test="${sessionScope.user.role.toString() == 'ADMIN'}">
                        <a href="controller?command=adminMenu"> <fmt:message key="common.administration"/></a>
                    </c:if>
                </td>
                <td align="right">
                        <%--Language links--%>
                    <a href="controller?command=${navigation}&userlang=en">ENG</a>
                    <a href="controller?command=${navigation}&userlang=ru">РУC</a><br>
                </td>
            </tr>
            <tr align="left">
                <td colspan="2">
                        <%--template--%>

                        <%--No new test is creating--%>
                    <c:if test="${empty sessionScope.newTest}">
                        <%--Caption--%>
                        <div align="center">
                            <h1><fmt:message key="editTests.caption"/></h1>
                        </div>


                        <%--Add test caption--%>
                        <div align="center">
                            <h2><fmt:message key="editTests.addTest"/></h2>
                        </div>
                        <%--Add test form--%>
                        <div align="left">
                            <form action="controller" method="post">
                                <input type="hidden" name="command" value="adminAddTest"/>

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
                        <div align="center">
                            <h2><fmt:message key="editTests.deleteTest"/></h2>
                        </div>
                        <%--Delete test form--%>
                        <div align="left">
                            <form action="controller" method="post">
                                <input type="hidden" name="command" value="adminDeleteTest"/>
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

                    </c:if>


                        <%--New test creating--%>
                    <c:if test="${not empty sessionScope.newTest}">

                        <div align="center">
                            <h1><fmt:message key="editTests.newTestCaption"/></h1>
                        </div>

                        <%--Caption of new test--%>
                        <div align="center">
                            <h2><c:out value="${sessionScope.newTest.caption}"/></h2>
                        </div>

                        <%--Buttons save/cancel--%>
                        <div align="center">
                            <fmt:message key="editTests.save" var="save"/>
                            <input type="button" class="button" value="${save}"
                                   onclick="location.href='controller?command=adminSaveTest&save=1'"/>
                            <fmt:message key="editTests.cancel" var="cancel"/>
                            <input type="button" class="button" value="${cancel}"
                                   onclick="location.href='controller?command=adminSaveTest&save=0'"/><br/>
                                ${saveTestMessage}
                        </div>


                        <%--Already created questions--%>
                        <div align="left">
                            <c:forEach var="question" items="${sessionScope.newTest.questions}">


                                <p>

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
                                </p>


                            </c:forEach>
                        </div>

                        <%--Form for new question--%>
                        <div align="left">
                            <form action="controller" method="post">
                                <input type="hidden" name="command" value="adminAddQuestion"/>
                                <h3><fmt:message key="editTests.newQuestion"/>:</h3>
                                <textarea name="task" rows="3" cols="80">${param.task}</textarea>
                                <h3><fmt:message key="editTests.answers"/>:</h3>
                                <p>
                                    <input type="checkbox" name="answerId" value="0">
                                    <textarea name="text" rows="1" cols="75"></textarea><br/>
                                    <input type="checkbox" name="answerId" value="1">
                                    <textarea name="text" rows="1" cols="75"></textarea><br/>
                                    <input type="checkbox" name="answerId" value="2">
                                    <textarea name="text" rows="1" cols="75"></textarea><br/>
                                    <input type="checkbox" name="answerId" value="3">
                                    <textarea name="text" rows="1" cols="75"></textarea><br/>
                                    <input type="checkbox" name="answerId" value="4">
                                    <textarea name="text" rows="1" cols="75"></textarea><br/>
                                    <input type="checkbox" name="answerId" value="5">
                                    <textarea name="text" rows="1" cols="75"></textarea><br/>
                                    <input type="checkbox" name="answerId" value="6">
                                    <textarea name="text" rows="1" cols="75"></textarea><br/>
                                    <input type="checkbox" name="answerId" value="7">
                                    <textarea name="text" rows="1" cols="75"></textarea><br/>
                                    <input type="checkbox" name="answerId" value="8">
                                    <textarea name="text" rows="1" cols="75"></textarea><br/>
                                    <input type="checkbox" name="answerId" value="9">
                                    <textarea name="text" rows="1" cols="75"></textarea>
                                </p>
                                <fmt:message key="editTests.addQuestion" var="addQuestion"/>
                                <input type="submit" name="addQuestion" value="${addQuestion}"/> ${addQuestionMessage}
                            </form>
                        </div>
                    </c:if>

                    <hr>

                    <p><a href="controller?command=adminMenu">
                        <fmt:message key="editTests.back"/>
                    </a></p>

                        <%--template--%>
                </td>
            </tr>
        </table>
    </div>
    </body>
    </html>

</fmt:bundle>