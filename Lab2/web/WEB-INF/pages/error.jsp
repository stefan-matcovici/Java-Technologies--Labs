<%--
  Created by IntelliJ IDEA.
  User: Stefan
  Date: 10/11/2018
  Time: 1:00 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page isErrorPage="true" contentType="text/html; ISO-8859-1"%>
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
    <h1>Error!</h1>
    <h2><%=exception.getMessage()%></h2>
    <a href="../">Back to form</a>
</body>
</html>
