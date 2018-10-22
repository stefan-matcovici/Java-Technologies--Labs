<%@ attribute name="category" required="true" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ tag import="ro.uaic.info.javatechnologies.models.Record" %>
<%@ tag import="java.util.List" %>
<c:set var="clientLocale" value="${pageContext.request.locale}" />
<fmt:setBundle basename="ro.uaic.info.javatechnologies.messages" var="msg" scope="page"/>
<table>
    <tr>
        <th><fmt:message key="category" bundle="${msg}"/></th>
        <th><fmt:message key="key" bundle="${msg}"/></th>
        <th><fmt:message key="name" bundle="${msg}"/></th>
    </tr>
    <% List<Record> records = (List<Record>) session.getAttribute("StoreServlet.records");
        if (records != null) {
            for (Record record : records) {
                out.print(String.format("<tr>" +
                        "<td>%s</td>" +
                        "<td>%s</td>" +
                        "<td>%s</td>" +
                        "</tr>", record.getCategory(), record.getKey(), record.getName()));
            }
        }
    %>
</table>