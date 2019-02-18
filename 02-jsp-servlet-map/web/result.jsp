<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="ro.uaic.info.javatechnologies.models.Record" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<c:set var="clientLocale" value="${pageContext.request.locale}" />
<fmt:setBundle basename="ro.uaic.info.javatechnologies.messages" var="msg" scope="page"/>
<html>
<head>
    <title>Result</title>
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
<table>
    <tr>
        <th><fmt:message key="category" bundle="${msg}"/></th>
        <th><fmt:message key="key" bundle="${msg}"/></th>
        <th><fmt:message key="name" bundle="${msg}"/></th>
    </tr>
    <% List<Record> records = (List<Record>) session.getAttribute("StoreServlet.records");
        if (records != null) {
            for (int i = 0; i < records.size(); i++) {
                out.print(String.format("<tr>" +
                        "<td>%s</td>" +
                        "<td>%s</td>" +
                        "<td>%s</td>" +
                        "</tr>", records.get(i).getCategory(), records.get(i).getKey(), records.get(i).getName()));
            }
        }
    %>
</table>

<a href="../"><fmt:message key="back" bundle="${msg}"/></a>
</body>
</html>
