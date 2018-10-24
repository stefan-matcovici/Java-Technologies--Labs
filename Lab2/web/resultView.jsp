<%@ page import="java.util.Map" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="rv" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="/WEB-INF/tlds/tags" prefix="t" %>
<%@ taglib prefix="f" uri="/WEB-INF/tlds/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt" %>
<html>
<head>
    <title>Title</title>
    <style>
        table {
            width: 70%;
            margin: auto;
            border-collapse: collapse;
        }

        table, th, td {
            border: 1px solid black;
        }
    </style>
</head>
<body>
    <rv:recordsView category = "${pageContext.request.getParameter(\"categorySelect\")}"/>

    <p>You just entered the following record:<t:record-value key="${pageContext.request.getParameter(\"key\")}"></t:record-value></p>
    <p>The last records stored:</p>
    <t:record-value key="">
        <c:out value="${f:filterByDate(sessionScope.get(\"StoreServlet.records\"))}" />
    </t:record-value>


</body>
</html>
