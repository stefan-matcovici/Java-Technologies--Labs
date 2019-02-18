# Java Technologies Lab10

## Contexts and Dependency Injection (CDI)
##### Create a simple JSF application for managing documents (or integrate the following functionalities into an existing application). The application will allow the following:
* An [authentication mechanism](./src/ro/uaic/info/technologies/documentmanager/interceptors/LoggedIn.java) based on username and password.
* Register new users and assign them a specific role, for example admin, guest, etc.
* [Specify a time frame](./src/ro/uaic/info/technologies/documentmanager/interceptors/ValidPeriod.java), in which registration is open for users and submissions.
* The possibility to upload a document (for guests) and to view all uploaded documents (for admin). Each uploaded document will have a uniquely generated registration number. All submissions will be logged in a text file. 

##### Use Contexts and Dependency Injection (CDI) for:
* the management of application's beans;
* decoupling the components using dependency injection (for example, [producer methods](./src/ro/uaic/info/technologies/documentmanager/producers));
* decoupling orthogonal concerns, such as logging;
* decoupling bussines concerns, such as verifying the date for operations like registration and submission;
* data validation, using Bean Validation annotations;
* implementing at least one [event-based comunication](./src/ro/uaic/info/technologies/documentmanager/services/TxtLoggingService.java) (for instance, whenever a new document is uploaded a message is produced and all observers of this type of event will be notified).

# Java Technologies Lab11

## Web Services 
##### Create RESTful Web services using JAX-RS that allow the interaction with at least one JPA entity, implementing CRUD operations. For example, the application may offer the following:
* adding a new document and deleting an existing document from the database ([RestService](./src/ro/uaic/info/technologies/documentmanager/webservices/RestService.java));
* Use XML or JSON for representing consumed or produced data. 

# Java Technologies Lab12

## Java EE Security
##### Add security features to the application created in the previous lab, using standard mechanisms offered by Java EE for:
* Authentication
* Controlling the access to Web resources
* Securing EJB components
