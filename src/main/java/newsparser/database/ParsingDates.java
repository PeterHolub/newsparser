package newsparser.database;

import newsparser.model.ParsingDate;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.sql.*;
import java.util.*;

//Class for getting Parsing dates and time and send as parameter to page
@WebServlet("/ParsingDates")
public class ParsingDates extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        List<ParsingDate> list = null;
        try {
            list = getParsingDate();
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        request.getServletContext().removeAttribute("dataList");
        request.getServletContext().setAttribute("dataList", list);
        request.getRequestDispatcher("/archive.jsp").forward(request, response);
    }

    private List<ParsingDate> getParsingDate() throws SQLException, ClassNotFoundException, IOException {

        ArrayList<ParsingDate> parsingDate = new ArrayList<>();

        Connection connection = DatabaseInitializer.getConnection();

        String sql = "SELECT parsedatetime FROM newsparser";

        Statement statement = connection.createStatement();

        ResultSet resultSet = statement.executeQuery(sql);

        while (resultSet.next()) {
            String datetime = resultSet.getString("parsedatetime");
            parsingDate.add(new ParsingDate(datetime));
        }
            //removing duplicate values using HashSet collection
        return new ArrayList<>(new HashSet<>(parsingDate));
    }


}



