# Java Technologies Lab4

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

# Java Technologies Lab5

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

# Java Technologies Lab6

## JNDI, Resources, Listeners
##### Continue the application created for the previous lab, implementing an efficient way for obtaining connections to the database:
* Configure a connection pool and a JDBC resource using an administrative tool (such as GlassFish Console).
* Create DataSource objects using either JNDI directly or resource injection.
* Consider the situation when the application should be implemented using a multi tenant architecture: a single application instance accessing multiple, isolated databases, having the same structure.

# Java Technologies Lab7

## Java Persistence API (JPA)
##### Rewrite the persistence layer of the application created for the previous laboratory using a technology that implements the JPA specifications:
* Define the [persistence unit](./src/META-INF/persistence.xml) using a data source configured as a JDBC Resource.
* Create the EntityManager objects using dependency injection.
* Define the [mappings](./src/ro/uaic/info/javatechnologies/optcourses/entities] using JPA-only annotations. You should have at least a One-To-Many association.
* Implement the [repository classes](./src/ro/uaic/info/javatechnologies/optcourses/repository) using JPA-QL.
* Create two new tables for storing in the database students and their preferences regarding the optional courses. Each student should offer a complete sorted list of the optional courses in each package defined for his year of study.
* Use [inheritance mapping](./src/ro/uaic/info/javatechnologies/optcourses/entities/CoursesEntity.java] for courses and optional courses and/or students and lecturers.
* Define the following queries:
  * All students.
  * Students that have incomplete preference lists.
  * All optional courses together with the degree of preference among students.
* Create two new tables for storing in the database all the grades obtained by the students and the preferences of the lecturers, regarding the students that will be admitted to their optional courses. These preferences will not be individual, they will take into condideration the grades obtained on some courses.
###### Notes
* It is required to use a JPA implementation and not just any third-party ORM library.
* A bonus will be given for adapting the Web interface to the new specifications.

# Java Technologies Lab8

## JPA Criteria API (JPA)
##### Continue the JSF application created in the previous laboratories, adding a ["search page"](./web/pages/search-courses.xhtml). This page will allow students to search for courses, using various filters: by name, by type (compulsory/optional), by capacity, etc.
* Each filter will have a checkox - if it is checked then the filter will be taken into consideration.
* The query must be implemented using JPA Criteria API.

# Java Technologies Lab9

## Enterprise Java Beans (EJB)

##### Rewrite the data access layer of the application created in the previous laboratories, implementing the [repository classes](./src/ro/uaic/info/javatechnologies/optcourses/repository) as Enterprise Java Beans:
* Use the support offered by the EJB technology for implementing transactions.
* Use an EJB interceptor in order to monitor the running time of a specific group of methods.
* Create at least one timer that will perform some operations (generate an HTML page, for example) using a specified schedule.

##### Add a "manual assignment" page, allowing a group of students to apply together for a specific course. The following enterprise beans must be implemented:
* [CourseCheckingBean](./src/ro/uaic/info/javatechnologies/optcourses/ejb/CourseCheckingBean.java) - Stateless session bean that offers methods for checking the availability of a course (its capacity is not exceeded).
* [AssignmentBean](./src/ro/uaic/info/javatechnologies/optcourses/ejb/AssignementBean.java) - Stateful session bean responsible with the assignment of one or more students to a specific course. The assignment should be atomic, either all students are successfully assigned, or the transaction will be rolled back. Create a trigger in the database that will verify if the number of students assigned to an optional course does not exceed the capacity of the course.
* [CourseStatusBean](./src/ro/uaic/info/javatechnologies/optcourses/ejb/CourseStatusBean.java) - Singleton session bean that keeps an in-memory map of the current assignments. The map will be instantiated at application startup and updated whenever the assignments change.
###### Notes 
* Create test units or update the Web interface of your project in order to test your EJBs.
* It is necessary to implement a model in which collisions do not occur and all the constraints of the problem are met.
* Use in an non-trivial manner the services provided by the EJB container (for a bouns, maybe...).

