<%@ page isErrorPage="true" contentType="text/html; ISO-8859-1"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="clientLocale" value="${pageContext.request.locale}" />
<fmt:setBundle basename="ro.uaic.info.javatechnologies.messages" var="msg" scope="page"/>
<html>
<head>
    <style>
        h1, h2 {
            width:50%;
            margin: auto;
        }
    </style>
</head>
<body>
    <h1><fmt:message key="error" bundle="${msg}"/>!</h1>
    <h2><%=exception.getMessage()%></h2>
    <a href="../"><fmt:message key="back" bundle="${msg}"/></a>
</body>
</html>
