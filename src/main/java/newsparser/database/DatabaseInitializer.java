package newsparser.database;

import newsparser.properties.PropertiesFile;
import java.io.IOException;
import java.sql.*;

//Class for Database connection
public class DatabaseInitializer {

    public static Connection getConnection() throws SQLException, ClassNotFoundException, IOException {
        PropertiesFile propertiesFile = new PropertiesFile();
        Class.forName(propertiesFile.getDatabaseProperties().getProperty("driverName"));
        return DriverManager.getConnection(propertiesFile.getDatabaseProperties().getProperty("databaseUrl"));

    }
}
