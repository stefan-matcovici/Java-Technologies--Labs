# Java Servlet Technologies Lab2
The purpose of the application is to integrate various components, such as JSP pages, servlets and JavaBeans.

#####  Starting from the problem described in lab 1, create a Web application containing the following components:
* input.jsp: a page containing a form for introducing a record, i.e. a triple containing a category, a key and a name. The categories are not static, being read from a server-side component (an object);
* result.jsp a page describing the response that will be delivered to the client, for example an HTML table containing the records stored on the server.
* error.jsp a page for displaying various errors, in an "user-friendly" manner. For example, introducing two records with the same key might generate a custom exception.
* an object-oriented domain model;
* a server-side component responsible with the bussines-logic of the application: writing the record to a server-side data structure, reading data from it, etc.
* a server-side component responsible with controlling the web-flow.

The servlet invocation will be done using a simple HTML form. At each invocation the servlet will receive a single pair of strings. The servlet will return the response as an HTML page containing all the pairs that were submitted, ordered by key. Write in the server log the following information about each request: the HTTP method used, the IP-address of the client, the user-agent, the client language(s) and the parameters of the request. (Take a look at HttpServletRequest API).

---

##### Use a "hand-made" cookie to store the category selected by the client. When the user returns to the site (after the current session was invalidated) and presents this cookie, the category will be set automatically. 
---
##### Add a CAPTCHA facility to the input form. A bonus may be awarded for an original implementation of a CAPTCHA system.
