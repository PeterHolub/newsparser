package newsparser.daoimpl;

import newsparser.dao.PageElementsDAO;
import newsparser.util.DatabaseConnection;
import newsparser.model.PageElements;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
//
public class PageElementsImpl implements PageElementsDAO {
    private Connection connection;
    private ResultSet resultSet;
    private PreparedStatement preparedStatement;

    public void savetoDatabase(ArrayList<PageElements> elements) throws SQLException, ClassNotFoundException, IOException {

        connection = DatabaseConnection.getConnection();

        //Checking if Table Exist
        DatabaseMetaData dbm = connection.getMetaData();
        resultSet = dbm.getTables(null, null, "newsparser", null);

        if (!resultSet.next()) {

            //If Table not exist create table  "newsparser"
            Statement statement = connection.createStatement();
            statement.execute("CREATE TABLE newsparser(style VARCHAR(8),parsedatetime VARCHAR(25),time VARCHAR(6)," +
                    "headerurl VARCHAR(600),subtitle VARCHAR (600), mark VARCHAR(30)) ");
        }
        //Saving parsed data to Database
        preparedStatement = connection.prepareStatement("INSERT INTO newsparser (style, parsedatetime,time, headerurl, subtitle, mark) VALUES (?,?,?,?,?,?)");
        for (PageElements e : elements) {
            preparedStatement.setString(1, e.getStyle());
            preparedStatement.setString(2, e.getParseDateTime());
            preparedStatement.setString(3, e.getTime());
            preparedStatement.setString(4, e.getHeaderUrl());
            preparedStatement.setString(5, e.getSubtitle());
            preparedStatement.setString(6, e.getMark());
            preparedStatement.addBatch();
        }
        preparedStatement.executeBatch();
        connection.close();

    }

    public ArrayList<PageElements> getPageElementsByDate(String date) throws SQLException, ClassNotFoundException, IOException {
        ArrayList<PageElements> pageElementsList = new ArrayList<>();

        connection = DatabaseConnection.getConnection();

        preparedStatement = connection.prepareStatement("SELECT * FROM newsparser WHERE parsedatetime =(?)");

        preparedStatement.setString(1, date);

        resultSet = preparedStatement.executeQuery();

        while (resultSet.next()) {
            String style = resultSet.getString("style");
            String parseDateTime = resultSet.getString("parsedatetime");
            String time = resultSet.getString("time");
            String headerUrl = resultSet.getString("headerurl");
            String subtitle = resultSet.getString("subtitle");
            String mark = resultSet.getString("mark");

            pageElementsList.add(new PageElements(style, parseDateTime, time, headerUrl, subtitle, mark));

        }
        connection.close();
        return pageElementsList;
    }


}








