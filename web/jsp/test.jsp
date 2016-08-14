<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="ctg" uri="http://customtags.rud.ua" %>

<ctg:lang var="language" param="userlang"/>
<fmt:setLocale value="${language}"/>
<fmt:bundle basename="ua/rud/testingsystem/resource/messages">

    <div align="center">
            <%--Test caption--%>
        <h1><c:out value="${sessionScope.test.caption}"/></h1>
            <%--Results--%>
        <c:if test="${sessionScope.test.completed}">
            <h2><fmt:message key="test.correctAnswers"/> ${sessionScope.test.getRate()}%</h2>
        </c:if>

    </div>

    <div align="left">
        <form action="controller" method="post">
            <input type="hidden" name="token" value="${sessionScope.token}"/>

            <c:forEach var="question" items="${sessionScope.test.questions}">
                <p>
                        <%--If test's completed and question answered incorrectly - task's font color is red,
                        correctly - green, test's not completed - black--%>
                    <c:choose>
                    <c:when test="${sessionScope.test.completed && question.correct}">
                        <c:set var="color" value="green"/>
                    </c:when>
                    <c:when test="${sessionScope.test.completed && !question.correct}">
                        <c:set var="color" value="red"/>
                    </c:when>
                    <c:otherwise>
                        <c:set var="color" value="black"/>
                    </c:otherwise>
                    </c:choose>

                        <%--Task--%>
                    <span class="${color}"><b><ctg:source value="${question.task}"/></b></span><br/>

                        <%--Answers--%>

                <table width="100%">
                    <c:forEach var="answer" items="${question.answers}">
                        <tr>

                                <%--Define color of answer's text--%>
                            <c:choose>
                                <c:when test="${sessionScope.test.completed && answer.correct}">
                                    <c:set var="color" value="green"/>
                                </c:when>
                                <c:otherwise>
                                    <c:set var="color" value="black"/>
                                </c:otherwise>
                            </c:choose>

                                <%--Define attributes of answer checkbox--%>
                            <c:choose>
                                <c:when test="${sessionScope.test.completed && answer.checked}">
                                    <c:set var="attribute" value="disabled checked"/>
                                </c:when>
                                <c:when test="${sessionScope.test.completed && !answer.checked}">
                                    <c:set var="attribute" value="disabled"/>
                                </c:when>
                                <c:otherwise>
                                    <c:set var="attribute" value=""/>
                                </c:otherwise>
                            </c:choose>

                                <%--Checkbox--%>
                            <td width="10px">
                                <input type="checkbox" name="id" value="${answer.id}" ${attribute}/>
                            </td>

                                <%--Answer's text--%>
                            <td>
                                <span class="${color}"><ctg:source value="${answer.text}"/></span><br/>
                            </td>
                        </tr>
                    </c:forEach>
                </table>

                </p>
                <hr>

            </c:forEach>

                <%--Choose which button to show--%>
            <c:choose>
                <c:when test="${!sessionScope.test.completed}">
                    <fmt:message key="test.complete" var="complete"/>
                    <input type="hidden" name="command" value="userComplete">
                    <input type="submit" name="select" value="${complete}">
                </c:when>
                <c:otherwise>
                    <fmt:message key="test.menu" var="menu"/>
                    <input type="hidden" name="command" value="userMenu">
                    <input type="submit" name="back" value="${menu}">
                </c:otherwise>
            </c:choose>
        </form>
    </div>

</fmt:bundle>