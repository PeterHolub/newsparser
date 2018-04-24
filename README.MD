# News parser for http://www.pravda.com.ua
Implementation of news parser using Java SE,JSP,JSTL tag library, Jsoup, OpenCSV, H2 database and Jetty.
## Prerequisites
* **JDK >= 1.8.0_131**;
* **Apache Maven >= 3.5.2**
## Project structure 
  **Java classes:**
* src\main\java\newsparser.dao -interface that provides  design and API to implement to work with Database
* src\main\java\newsparser.daoimpl -concrete classes that is an implementation of the interfaces from dao package
* src\main\java\newsparser.model - POJO classes pack
* src\main\java\newsparser.parser.Parser - Class for parsing HTML elements
* src\main\java\newsparser.properties.PropertiesFile - Class for loading .properties files 
* src\main\java\newsparser.servlets.GetDataByDates - Servlet for getting previous parsed data by date and time
* src\main\java\newsparser.servlets.GetDates - Servlet for getting Parsing dates and time and send as parameter to page
* src\main\java\newsparser.servlets.SaveToDatabase - Servlet for creating table if not exist and saving parsed data to database
* src\main\java\newsparser.servlets.SendEmail - Servlet for sending e-mail
* src\main\java\newsparser.util.DatabaseConnection - Initializer of Database connection


**Properties files:**
* src\main\resources\databaseproperties.properties
* src\main\resources\emailproperties.properties
* src\main\resources\parserproperties.properties

**Web files:**
* src\main\webapp - contains jsp files & css file with deploying descriptor web.xml
 
## Run the App

Run script for server local deploy:
```
mvn jetty:run
```
Then, open http://localhost:8080/ in your browser