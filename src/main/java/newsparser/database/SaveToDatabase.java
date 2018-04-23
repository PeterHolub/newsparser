package newsparser.database;

import newsparser.model.PageElements;
import newsparser.parser.Parser;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;

@WebServlet("/SaveToDatabase")
public class SaveToDatabase extends HttpServlet {

    //Class for creating table if not exist and saving parsed data to database
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        request.getParameter("pageElementsList");
        try {
            savetoDatabase();
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }

        request.setAttribute("databaseSaved", "File uploaded and saved into database");
        request.getRequestDispatcher("/index.jsp").forward(request, response);
    }

    private void savetoDatabase() throws SQLException, ClassNotFoundException, IOException {

        Connection connection = DatabaseInitializer.getConnection();

        //Checking if Table Exist
        DatabaseMetaData dbm = connection.getMetaData();
        ResultSet tables = dbm.getTables(null, null, "newsparser", null);

        if (!tables.next()) {

            //If Table not exist create table  "newsparser"
            Statement statement = connection.createStatement();
            statement.execute("CREATE TABLE newsparser(style VARCHAR(8),parsedatetime VARCHAR(25),time VARCHAR(6)," +
                    "headerurl VARCHAR(600),subtitle VARCHAR (600), mark VARCHAR(30)) ");
        }

        Parser parser = new Parser();
        ArrayList<PageElements> elements = parser.getPageElements();
        //Saving parsed data to Database
        String sqlBook = "INSERT INTO newsparser (style, parsedatetime,time, headerurl, subtitle, mark) VALUES (?,?,?,?,?,?)";
        PreparedStatement preparedStatement = connection.prepareStatement(sqlBook);
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
}









