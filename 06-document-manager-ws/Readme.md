# Java Technologies Lab11

## Web Services 

##### The application will offer the following JAX-WS service:
* [ViewDocumentService](./src/main/java/ro/uaic/info/technologies/documentmanager/ws/ViewDocumentService.java) that returns a "list" of the documents there were uploaded. The parameter of the web method will be an identifier for the user. If the parameter is null, then all documents should be considered.
* Define a [simple message handler](./src/main/java/ro/uaic/info/technologies/documentmanager/ws/handlers/LogicalHandlerImpl.java) that will act as a logging component.
