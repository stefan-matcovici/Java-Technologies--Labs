# Java Technologies Lab6

##### Implement a simple system to analyze the performance gain of using a connection pool:
* Create two servlest for two types of requests: [INSERT](./Server/src/ro/uaic/info/javatechnologies/dbperformance/controllers/InsertServlet.java) and [SELECT](./Server/src/ro/uaic/info/javatechnologies/dbperformance/controllers/SelectServlet.java). 
* The servlet must connect to a database and perform the requested operation over some table, having the columns: (id, request_time, remote_addr, request_params). 
* The servlet will respond with a "success" or "error" message.
* Define an application context parameter specifying the method used for managing database connections: singleton, connection-per-session, connection-pool.
* Create a [desktop application client](./Bomber/src/ro/uaic/info/javatechnologies/bomber) that will send multiple, concurrent invocations to the servlet. Each client thread will send a number of requests over a period of time, over the same Web session. 
* Analyze the behaviour of the system in each scenario.
