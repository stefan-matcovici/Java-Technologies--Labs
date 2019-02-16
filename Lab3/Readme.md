# Java Servlet Technologies Lab3

Web Filters, Custom JSP Tags, JSTL 
##### Continue the application created in the previous lab, adding the following components:
* A web filter that will log all requests received by input.jsp or result.jsp pages.
* A web filter that will validate the requests addressed to the InputController. If either key or value is missing, the request will be redirected back to input.jsp. If category parameter is missing, the request will be decorated such that the returned category will have a default value, specified as a context init parameter.
* A web filter that will decorate the response by adding a specific prelude (at the beginning) and a specific coda (at the end) to the generated HTML page. Important: we assume that the pages are already created and the functionalities describead above cannot be implemented by modifying them directly.
* Design a solution that allows parameterization of how the pages of our applications are to be decorated, similar to the SiteMesh framework .
* Create a custom tag with the name record that allows the inclusion in the response page of the value of a key. Example: \<record key='ejb' category='javaee'\/\> 
The tag may have a body. In this case, the content of the body will represent the keys to be translated, separated by some delimiter (comma, for example). The tag may have the following attributes:
  * key: (compulsory) the key we want the value for;
  * category: (optional) the category in which to search for the specified key.

* Create a custom tag using JSTL to implement a view of the data. 
The execution of this tag will create an HTML table containing the records that were saved on the server. As in the previous lab, the content of the table may be the records stored in the current session or all the records from the persistent storage. 
Example: <recordsView category='web' />
(Optional) Internationalize the application using at least two localizations (en and ro). Translate the static text and the display the dates when records were added according to the selected locale. 
Note: To create the view custom tag use a tag file and not a class file handler. The goal of this exercise is to use JSTL - do not write any Java code.
