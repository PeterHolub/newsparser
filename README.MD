# News parser for http://www.pravda.com.ua
Implementation of news parser using Java SE,JSP,JSTL tag library, Jsoup, OpenCSV, H2 database and Jetty.
## Prerequisites
* **JDK >= 1.8.0_131**;
* **Apache Maven >= 3.5.2**
## Project structure 
  **Java classes:**
* src\main\java\newsparser.Main -Main Servlet of App (Start and Clear buttons functions) 
* src\main\java\newsparser.csv.CSVFileDownload - Class for CSV file download function
* src\main\java\newsparser.csv.CSVFileGenerator - Class for CSV file generation
* src\main\java\newsparser.database.DatabaseInitializer - Initializer of Database connection
* src\main\java\newsparser.database.ParsingDatabase - Class for getting previous parsed data by date and time
* src\main\java\newsparser.database.ParsingDates - Class for getting Parsing dates and time and send as parameter to page
* src\main\java\newsparser.database.SaveToDatabase - Class for creating table if not exist and saving parsed data to database
* src\main\java\newsparser.email.SendEmail - Class for sending e-mail
* src\main\java\newsparser.model - package for POJO
* src\main\java\newsparser.parser.Parser - Class for parsing HTML elements
* src\main\java\newsparser.properties.PropertiesFile - Class for loading .properties files 

**Properties files:**
*src\main\resources\databaseproperties.properties
*src\main\resources\emailproperties.properties
*src\main\resources\parserproperties.properties

**Web files:**
* src\main\webapp - contains jsp files & css file with deploying descriptor web.xml
 
## Run the App

Run script for server local deploy:
```
mvn jetty:run
```
Then, open http://localhost:8080/ in your browser