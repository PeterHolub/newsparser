package newsparser.servlets;

import newsparser.model.ParsingDate;
import newsparser.daoimpl.ParsingDateImpl;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.sql.*;
import java.util.*;

//Servlet for getting Parsing dates and time and send as parameter to page
@WebServlet("/GetDates")
public class GetDates extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        ParsingDateImpl parsingDateImpl = new ParsingDateImpl();

        List<ParsingDate> list = null;
        try {
            list = parsingDateImpl.getParsingDate();
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        request.getServletContext().removeAttribute("dataList");
        request.getServletContext().setAttribute("dataList", list);
        request.getRequestDispatcher("/archive.jsp").forward(request, response);
    }
}



