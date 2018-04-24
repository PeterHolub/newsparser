package newsparser.servlets;

import newsparser.model.PageElements;
import newsparser.daoimpl.PageElementsImpl;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;

//Servlet for getting previous parsed data by date and time
@WebServlet("/GetDataByDates")
public class GetDataByDates extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ArrayList<PageElements> pageElements = new ArrayList<>();
        PageElementsImpl pageElementsImpl = new PageElementsImpl();
        String date = request.getParameter("parsingdate");
        try {
            pageElements = pageElementsImpl.getPageElementsByDate(date);
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        request.getServletContext().setAttribute("pageElementsByDate", pageElements);
        request.getRequestDispatcher("/archive.jsp").forward(request, response);

    }
}

