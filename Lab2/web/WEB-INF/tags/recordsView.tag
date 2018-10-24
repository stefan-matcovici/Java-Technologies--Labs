<%@ attribute name="category" required="true" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="f" uri="/WEB-INF/tlds/functions" %>

<c:set var="clientLocale" value="${pageContext.request.locale}" />
<fmt:setLocale value="${pageContext.request.locale}" scope="page"/>
<fmt:setBundle basename="ro.uaic.info.javatechnologies.messages" var="msg" scope="page"/>
<c:choose>
    <c:when test="${not empty category}">
        <c:set var="records" value="${f:filterByCategory(sessionScope.get(\"StoreServlet.records\"), category)}" />
    </c:when>
    <c:otherwise>
        <c:set var="records" value="${sessionScope.get(\"StoreServlet.records\")}" />
    </c:otherwise>
</c:choose>

<table>
    <tr>
        <th><fmt:message key="category" bundle="${msg}"/></th>
        <th><fmt:message key="key" bundle="${msg}"/></th>
        <th><fmt:message key="name" bundle="${msg}"/></th>
    </tr>
    <c:forEach var="record" items="${records}">
        <tr>
            <td><c:out value="${record.getCategory()}"/></td> <br/>
            <td><c:out value="${record.getKey()}"/></td> <br/>
            <td><c:out value="${record.getName()}"/></td> <br/>
            <td><fmt:formatDate pattern="dd.MMMM.yyyy" value="${record.getDate()}"/></td>
        </tr>
    </c:forEach>
</table>

<a href="../"><fmt:message key="back" bundle="${msg}"/></a>