# Java Servlet Technologies Lab1

##### Create a servlet that receives pairs of strings (key, value) and stores them on the server (along with the timestamp of the request):
1. in memory, using a Map data structure and also
1. in a text file (may be a .properties file)

The servlet invocation will be done using a simple HTML form. At each invocation the servlet will receive a single pair of strings. The servlet will return the response as an HTML page containing all the pairs that were submitted, ordered by key. Write in the server log the following information about each request: the HTTP method used, the IP-address of the client, the user-agent, the client language(s) and the parameters of the request. (Take a look at HttpServletRequest API).

---

##### Invoke the service from a desktop application (Java, Python, .NET, etc.). 
In this case, the servlet must respond with a simple text containing the list of words, instead of an HTML page. 
Analyze concurrency issues and resource contention, invoking the servlet repeatedly, in an asynchronous manner.
