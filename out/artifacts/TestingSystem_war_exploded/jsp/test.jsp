<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="ctg" uri="http://customtags.rud.ua" %>

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

            .red {
                color: red;
            }

            .black {
                color: black;
            }

            .green {
                color: limegreen;
            }
        </style>
    </head>
    <body>
    <div align="center">
        <table width="600px">  <%-- frame="border"--%>
            <tr>
                <td align="left">
                    <a href="controller?command=logout"><fmt:message key="common.logout"/></a>
                    <%--<a href="controller?command=results"> <fmt:message key="common.results"/></a>--%>
                    <c:if test="${sessionScope.user.role.toString() == 'ADMIN'}">
                        <a href="controller?command=admin"> <fmt:message key="common.administration"/></a>
                    </c:if>
                </td>
                <td align="right">
                    <a href="controller?command=test&userlang=en">ENG</a>
                    <a href="controller?command=test&userlang=ru">РУC</a><br>
                </td>
            </tr>
            <tr align="left">
                <td colspan="2">
                        <%--template--%>
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
                                    <span class="${color}"><c:out value="${question.task}"/></span><br/>

                                        <%--Answers--%>
                                    <c:forEach var="answer" items="${question.answers}">

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
                                        <input type="checkbox" name="id" value="${answer.id}" ${attribute}/>

                                        <%--Answer's text--%>
                                        <span class="${color}"><c:out value="${answer.text}"/></span><br/>
                                    </c:forEach>
                                </p>
                            </c:forEach>

                                <%--Choose which button to show--%>
                            <c:choose>
                                <c:when test="${!sessionScope.test.completed}">
                                    <fmt:message key="test.complete" var="complete"/>
                                    <input type="hidden" name="command" value="complete">
                                    <input type="submit" name="select" value="${complete}">
                                </c:when>
                                <c:otherwise>
                                    <fmt:message key="test.menu" var="menu"/>
                                    <input type="hidden" name="command" value="menu">
                                    <input type="submit" name="back" value="${menu}">
                                </c:otherwise>
                            </c:choose>
                        </form>
                    </div>
                        <%--template--%>
                </td>
            </tr>
        </table>
    </div>
    </body>
    </html>

</fmt:bundle>