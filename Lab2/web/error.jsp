<%--
  Created by IntelliJ IDEA.
  User: Stefan
  Date: 10/11/2018
  Time: 1:00 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page isErrorPage="true" import="java.io.*" contentType="text/plain"%>

Message:
<%=exception.getMessage()%>

StackTrace:
<%
	StringWriter stringWriter = new StringWriter();
	PrintWriter printWriter = new PrintWriter(stringWriter);
	exception.printStackTrace(printWriter);
	out.println(stringWriter);
	printWriter.close();
	stringWriter.close();
%>