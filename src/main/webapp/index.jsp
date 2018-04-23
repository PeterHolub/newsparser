<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">

<html>
<head>
    <link href="${pageContext.servletContext.contextPath}/css/index.css" rel="stylesheet"
          type="text/css">
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

</head>
<body>
<h1 style="color: #528ecc" align="center">News parser for http://www.pravda.com.ua</h1>

<form action="${pageContext.servletContext.contextPath}/ParsingDates" method="post" style="text-align: right">

    <button type="submit" class="dataButtons">PreviousResults</button>
</form>

<div class="buttons">
    <form action="${pageContext.servletContext.contextPath}/Main" method="post">
        <input type="hidden" name="button" value="start"/>
        <button type="submit" class="myButton">Start</button>
    </form>

    <form action="${pageContext.servletContext.contextPath}/Main" method="post">
        <input type="hidden" name="button" value="clean"/>
        <button type="submit" class="myButton">Clean</button>
    </form>


    <c:if test="${clean =='true'}">
        <c:remove var="pageElementsList"/>
    </c:if>

</div>
<c:set var="count" value="0" scope="page"/>

<table>

    <c:if test="${!empty pageElementsList}">

        <tr>
            <th>Index number</th>
            <th>Date & Time of Parsing</th>
            <th>News Time</th>
            <th>News Header</th>
            <th>News Text</th>
            <th>News Mark</th>
        </tr>

    </c:if>

    <c:forEach items="${pageElementsList}" var="current">

        <c:set var="count" value="${count + 1}" scope="page"/>

        <tr>
            <td>
                <c:out value="${count}"/>
            </td>

            <td>
                <c:out value="${current.parseDateTime}"/>
            </td>

            <td>
                <c:out value="${current.time}"/>
            </td>

            <td>
                <c:url value="${current.headerUrl}"/>
            </td>
            <c:choose>
                <c:when test="${current.style =='bold'}">
                    <td>
                        <b><c:out value="${current.subtitle}"/></b>
                    </td>
                </c:when>
                <c:when test="${current.style =='red'}">
                    <td>
                        <font color="red"><c:out value="${current.subtitle}"/></font>
                    </td>

                </c:when>
                <c:otherwise>
                    <td>
                        <c:out value="${current.subtitle}"/>
                    </td>

                </c:otherwise>
            </c:choose>
            <td>
                <c:url value="${current.mark}"/>
            </td>
        </tr>

    </c:forEach>

</table>

<c:if test="${!empty pageElementsList}">

    <div class="buttons">
        <form action="${pageContext.servletContext.contextPath}/CSVFileGenerator" method="post">
            <input type="hidden" name="button" value="start"/>
            <button type="submit" class="dataButtons">Safe to CSV</button>
        </form>

        <h3 style="color: red"><c:out value="${csvSaved}"/></h3>
        <c:url value="${download}"/>
        <h3 style="color: red"><c:out value="${databaseSaved}"/></h3>

        <form action="${pageContext.servletContext.contextPath}/SaveToDatabase" method="post">
            <input type="hidden" name="button"/>
            <button type="submit" class="dataButtons">Safe to Database</button>
        </form>


        <c:if test="${!empty download}">

            <form action="${pageContext.servletContext.contextPath}/SendEmail" method="post">

                <h2 style="color: #79bbff" align="center">Send e-mail To:</h2>

                <input type="text" name="email" required value="" size="16"/>

                <input type="submit" value="Send e-mail" class="myButton" align="middle">

            </form>
            <h3 style="color: #79bbff"><c:out value="${send}"/></h3>

        </c:if>
    </div>
</c:if>
</body>
</html>
