# Java Technologies Lab10

## Contexts and Dependency Injection (CDI)
##### Create a simple JSF application for managing documents (or integrate the following functionalities into an existing application). The application will allow the following:
* An [authentication mechanism](./src/ro/uaic/info/technologies/documentmanager/interceptors/LoggedIn.java) based on username and password.
* Register new users and assign them a specific role, for example admin, guest, etc.
* [Specify a time frame](./src/ro/uaic/info/technologies/documentmanager/interceptors/ValidPeriod.java), in which registration is open for users and submissions.
* The possibility to upload a document (for guests) and to view all uploaded documents (for admin). Each uploaded document will have a uniquely generated registration number. All submissions will be logged in a text file. 
* Use at least one non trivial JSF component, for example a data table, a tree, a dialog, etc.
* Use a relational database and JDBC in order to store and retrieve data. (PostgreSQL is recommended).
* It is recommended to use an Ajax-based JSF implementation: PrimeFaces, ICEFaces (ACE components), RichFaces, etc.
* Additional points will be given for using JSF technology beyond the "beginner" level (use custom converters, validators, "rich" components, i18n, etc.).
