<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="ctg" uri="http://customtags.rud.ua" %>

<c:set var="navigation" value="adminEditSubjects"/>
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
                    <a href="controller?command=logout"><fmt:message key="common.logout"/></a>
                    <c:if test="${sessionScope.user.role.toString() == 'ADMIN'}">
                        <a href="controller?command=adminMenu"> <fmt:message key="common.administration"/></a>
                    </c:if>
                </td>
                <td align="right">
                    <a href="controller?command=${navigation}&userlang=en">ENG</a>
                    <a href="controller?command=${navigation}&userlang=ru">РУC</a><br>
                </td>
            </tr>
            <tr align="left">
                <td colspan="2">
                        <%--template--%>

                        <%--Common caption--%>
                    <div align="center">
                        <h1><fmt:message key="editSubjects.caption"/></h1>
                    </div>

                        <%--Add caption--%>
                    <div align="center">
                        <h2><fmt:message key="editSubjects.addSubject"/></h2>
                    </div>

                        <%--Add form--%>
                    <div align="left">
                        <form action="controller" method="post">
                            <input type="hidden" name="command" value="adminSaveSubject"/>
                            <h3><fmt:message key="editSubjects.newSubject"/>:</h3>
                            <input type="text" name="name" value=""/>
                            <fmt:message var="add" key="editSubjects.add"/>
                            <input type="submit" name="add" value="${add}">
                                ${addSubjectMessage}
                        </form>
                    </div>

                    <hr>

                        <%--Delete caption--%>
                    <div align="center">
                        <h2><fmt:message key="editSubjects.deleteSubject"/></h2>
                    </div>

                        <%--Delete form--%>
                    <div align="left">
                        <form action="controller" method="post">
                            <input type="hidden" name="command" value="adminDeleteSubject">
                            <c:forEach var="subject" items="${sessionScope.subjects}">
                                <p>
                                    <input type="checkbox" name="subjectId" value="${subject.id}"/>
                                    <c:out value="${subject.name}"/>
                                </p>
                            </c:forEach>
                            <fmt:message var="delete" key="editSubjects.delete"/>
                            <input type="submit" name="delete" value="${delete}"> ${deleteSubjectMessage}
                        </form>
                            <%--<ul>--%>

                            <%--<p>--%>
                            <%--<li>--%>
                            <%--<form action="controller" method="post">--%>
                            <%--<input type="hidden" name="command" value="adminSaveSubject"/>--%>
                            <%--<fmt:message key="editSubjects.newSubject"/>:<br/>--%>
                            <%--<input type="text" name="name" value=""/>--%>
                            <%--<fmt:message var="add" key="editSubjects.add"/>--%>
                            <%--<input type="submit" name="add" value="${add}">--%>
                            <%--${addSubjectMessage}--%>
                            <%--</form>--%>
                            <%--</li>--%>
                            <%--</p><br/>--%>
                            <%--</ul>--%>

                        <hr>
                        <p><a href="controller?command=adminMenu">
                            <fmt:message key="editSubjects.back"/>
                        </a></p>

                    </div>

                        <%--template--%>
                </td>
            </tr>
        </table>
    </div>
    </body>
    </html>

</fmt:bundle>