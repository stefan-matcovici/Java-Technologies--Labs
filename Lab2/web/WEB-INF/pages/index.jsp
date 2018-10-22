<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="java.util.Random" %>

<%@ page contentType="text/html;charset=UTF-8" %>
<c:set var="clientLocale" value="${pageContext.request.locale}" />
<fmt:setBundle basename="ro.uaic.info.javatechnologies.messages" var="msg" scope="page"/>
<html>
<head>
    <title>Page</title>
</head>
<body>
<form method="POST" action="store" autocomplete="off">
    <jsp:useBean id="categories" scope="request" class="ro.uaic.info.javatechnologies.models.Categories"/>
    <fmt:message key="category" bundle="${msg}"/>:
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
    <fmt:message key="key" bundle="${msg}"/>:
    <input type="text" name="key" size="20" value=""/> <br/>
    <fmt:message key="name" bundle="${msg}"/>:
    <input type="text" name="name" size="20" value=""/> <br/>

    <fmt:message key="captcha" bundle="${msg}"/>:<br />
    <img src=<%="/captcha?no-cache=" + new Random().nextInt(1000) %>/> <br />
    <input type="text" name="captcha" size="20" value=""/> <br/>


    <input type="submit" name="submit" value="<fmt:message key="submit" bundle="${msg}"/>">
</form>
</body>
</html>