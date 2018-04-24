package newsparser.database;

import newsparser.model.PageElements;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;

//Class for getting previous parsed data by date and time
@WebServlet("/ParsingDatabase")
public class ParsingDatabase extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ArrayList<PageElements> pageElements = new ArrayList<>();

        String date = request.getParameter("parsingdate");
        try {
            pageElements = getPageElementsFromDatabase(date);
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }

        request.getServletContext().setAttribute("pageElementsByDate", pageElements);

        request.getRequestDispatcher("/archive.jsp").forward(request, response);

    }

    private ArrayList<PageElements> getPageElementsFromDatabase(String date) throws SQLException, ClassNotFoundException, IOException {
        ArrayList<PageElements> pageElementsList = new ArrayList<>();

        Connection connection = DatabaseInitializer.getConnection();

        PreparedStatement statement = connection.prepareStatement("SELECT * FROM newsparser WHERE parsedatetime =(?)");

        statement.setString(1, date);

        ResultSet resultSet = statement.executeQuery();

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

