<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">

<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

    <link href="${pageContext.servletContext.contextPath}/css/index.css" rel="stylesheet"
          type="text/css">
</head>
<body>
<h1 style="color:red" align="center"> Previous Parsing Data</h1>
<form action="${pageContext.servletContext.contextPath}/index.jsp" method="post" style="text-align: right">

    <button type="submit" class="dataButtons">Back To Main Page</button>
</form>


<c:if test="${!empty dataList}">

    <div class="buttons">
        <form method="post" action="${pageContext.servletContext.contextPath}/GetDataByDates"
              style="text-align: center">
            <table>

                <tr>
                    <td>Select Date:</td>
                    <td>
                        <select name="parsingdate" id="parsingdate" title="parsingdate">

                            <c:forEach var="data" items="${dataList}">
                                <option value="<c:out value="${data.datetime}"/>"><c:out
                                        value="${data.datetime}"/></option>
                            </c:forEach>
                        </select>

                    </td>

                </tr>
                <tr>
                    <td colspan="2">
                        <input type="submit" value="Show Results" class="myButton" >
                    </td>

                </tr>
            </table>

        </form>

    </div>
</c:if>

<c:set var="count" value="0" scope="page"/>

<table>
    <c:if test="${!empty pageElementsByDate}">

        <tr>
            <th>Index number</th>
            <th>Date & Time of Parsing</th>
            <th>News Time</th>
            <th>News Header</th>
            <th>News Text</th>
            <th>News Mark</th>
        </tr>

    </c:if>
    <c:forEach items="${pageElementsByDate}" var="current">

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


</body>
</html>