<%@ page import="ro.uaic.info.javatechnologies.Categories" %>
<%@ page import="java.util.Enumeration" %><%--
  Created by IntelliJ IDEA.
  User: smatcovici
  Date: 7/30/2018
  Time: 11:38 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title>Page</title>
</head>
<body>
<form method="POST" action="StoreServlet">
    <jsp:useBean id="categories" scope="request" class="ro.uaic.info.javatechnologies.Categories"/>
    <select name="categorySelect" id="categorySelect">
        <%
            for (String category : categories.getCategories()) {
                out.println(String.format("<option>%s</option>", category));
            }
        %>
    </select>
    <br>
    Key:
    <input type="text" name="key" size="20" value=""/> <br/>
    Name:
    <input type="text" name="name" size="20" value=""/> <br/>

    <input type="submit" name="submit" value="Submit">
</form>
</body>
</html>