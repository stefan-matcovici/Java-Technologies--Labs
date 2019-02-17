# Java Servlet Technologies Lab4

## JavaServer Faces
##### Create a Web application using JavaServer Faces technology, dedicated to optional course allocation. The JSF application (for now...) will have the following components:
* [A Web page for defining optional packages](./web/pages/optional-package-form.xhtml). Each package is dedicated to a specific year of study and semester.
* [A Web page for defining lecturers](./web/pages/lecturers-form.xhtml).
* [A Web page for defining optional and compulsory courses](./course-form.xhtml). Each course should have the properties: name, year of study, semester, url, lecturer(s), number of study groups, etc. An optional courses must specify its package.

###### Notes:
* Use at least one non trivial JSF component, for example a data table, a tree, a dialog, etc.
* Use a relational database and JDBC in order to store and retrieve data. (PostgreSQL is recommended).
* It is recommended to use an Ajax-based JSF implementation: PrimeFaces, ICEFaces (ACE components), RichFaces, etc.
* Additional points will be given for using JSF technology beyond the "beginner" level (use custom converters, validators, "rich" components, i18n, etc.).

# Java Servlet Technologies Lab5

##### Create the pages using [templates](./web/WEB-INF/templates):
* [page.xhtml](./web/WEB-INF/templates/page.xhtml): describing the general aspect of the application pages: header, content, footer. The header should display the title and might include a menu bar. The footer will display a copyright notice and the current version of the aplication. The header, footer and the menu bar should all be in separate .xhtml files.
* [dataView.xhtml](./web/WEB-INF/templates/dataView.xhtml): a generic page for displaying data as a list, dataTable, etc.
* [dataEdit.xhtml](./web/WEB-INF/templates/dataEdit.xhtml): a generic page for editing data. This could be a dialog containing a generic form.

##### Other features:
* Create at least some [composite components](./web/WEB-INF/resources/ezcomp). For example, create a [custom scrollable dataTable](./web/WEB-INF/resources/ezcomp/dataTable.xhtml) for representing a collection of items, with support for CRUD operations.
* Create at least some [converters](./src/ro/uaic/info/javatechnologies/optcourses/utils) (eg. [for the course URL](./src/ro/uaic/info/javatechnologies/optcourses/utils/URLConverter.java)) and at least one [validator](./src/ro/uaic/info/javatechnologies/optcourses/validation/EnumeratedValidator.java).
* Use the components ajax and poll in order to continuously display how many active sessions are in progress (or a similar information).
* Internationalize the application, offering support for at least [two locales](./src/ro/uaic/info/javatechnologies/optcourses/bundles).

###### Notes
* Use Facelets and not JSP for creating the pages.
* Additional points will be given for using [JSF technology beyond the "beginner" level](./web/pages/opt-packages-view.xhtml).
