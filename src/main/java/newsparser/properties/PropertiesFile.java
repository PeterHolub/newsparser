package newsparser.properties;

import java.io.*;
import java.util.Properties;

//Class for getting instance to properties files
public class PropertiesFile {

    public Properties getDatabaseProperties() throws IOException {
        // Get the inputStream
        InputStream inputStream = this.getClass().getClassLoader()
                .getResourceAsStream("databaseproperties.properties");

        Properties dataBaseProperties = new Properties();

        // load the inputStream using the PropertiesFile
        dataBaseProperties.load(inputStream);

        return dataBaseProperties;
    }

    public Properties getMailProperties() throws IOException {
        // Get the inputStream
        InputStream inputStream = this.getClass().getClassLoader()
                .getResourceAsStream("emailproperties.properties");

        Properties mailProperties = new Properties();

        // load the inputStream using the PropertiesFile
        mailProperties.load(inputStream);

        return mailProperties;
    }

    public Properties getParserProperties() throws IOException {
        // Get the inputStream
        InputStream inputStream = this.getClass().getClassLoader()
                .getResourceAsStream("parserproperties.properties");

        Properties parserProperties = new Properties();

        // load the inputStream using the PropertiesFile
        parserProperties.load(inputStream);

        return parserProperties;
    }


}
