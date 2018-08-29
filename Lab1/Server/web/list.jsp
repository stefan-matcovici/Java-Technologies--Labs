<%@ page import="java.util.Map" %><%--
  Created by IntelliJ IDEA.
  User: smatcovici
  Date: 7/30/2018
  Time: 3:25 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title>List</title>
</head>
<body>

<ul>
    <% Map<String, String> map = (Map<String, String>) request.getAttribute("map");
        for (Map.Entry<String, String> entry : map.entrySet()) {
    %>
    <li><%=entry.getKey()%> <%=entry.getValue()%></li>
    <% }%>
</ul>

<a href="../">Back to form</a>
</body>
</html>