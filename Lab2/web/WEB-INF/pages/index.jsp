<%@ page import="ro.uaic.info.javatechnologies.models.Categories" %>
<%@ page import="java.util.Enumeration" %>
<%@ page import="java.util.Arrays" %>
<%@ page import="java.util.Random" %><%--
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
<form method="POST" action="store" autocomplete="off">
    <jsp:useBean id="categories" scope="request" class="ro.uaic.info.javatechnologies.models.Categories"/>
    <select name="categorySelect" id="categorySelect">
        <%
            String preSelectedCategory = null;
            Cookie cookies[] = request.getCookies();
            if (cookies != null) {
                for (Cookie cookie: cookies) {
                    if (cookie.getName().equals("StoreServlet.category")) {
                        preSelectedCategory = cookie.getValue();
                    }
                }
            }
            out.println("<option value=\"\"></option>");
            for (String category : categories.getCategories()) {
                out.println(String.format("<option value=\"%s\" %s>%s</option>", category, category.equals(preSelectedCategory) ? "selected" : "", category));
            }
        %>
    </select>
    <br>
    Key:
    <input type="text" name="key" size="20" value=""/> <br/>
    Name:
    <input type="text" name="name" size="20" value=""/> <br/>

    Captcha:<br />
    <img src=<%="/captcha?no-cache=" + new Random().nextInt(1000) %>/> <br />
    <input type="text" name="captcha" size="20" value=""/> <br/>

    <input type="submit" name="submit" value="Submit">
</form>
</body>
</html>