<%@ page import="ro.uaic.info.javatechnologies.Record" %>
<%@ page import="java.util.List" %><%--
  Created by IntelliJ IDEA.
  User: smatcovici
  Date: 9/5/2018
  Time: 9:38 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
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
        <th>Category</th>
        <th>Name</th>
        <th>Key</th>
    </tr>
    <% List<Record> records = (List<Record>) session.getAttribute("StoreServlet.records");
        for (int i = 0; i < records.size(); i++) {
            out.print(String.format("<tr>" +
                    "<td>%s</td>" +
                    "<td>%s</td>" +
                    "<td>%s</td>" +
                    "</tr>", records.get(i).getCategory(), records.get(i).getKey(), records.get(i).getName()));
        }
    %>
</table>

<a href="../">Back to form</a>
</body>
</html>
