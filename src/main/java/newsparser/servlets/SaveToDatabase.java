package newsparser.servlets;

import newsparser.model.PageElements;
import newsparser.daoimpl.PageElementsImpl;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;

//Servlet for creating table if not exist and saving parsed data to database
@WebServlet("/SaveToDatabase")
public class SaveToDatabase extends HttpServlet {


    @Override
    @SuppressWarnings("unchecked")
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        ArrayList<PageElements> elements = (ArrayList<PageElements>) getServletContext().getAttribute("pageElementsList");

        PageElementsImpl pageElementsImpl = new PageElementsImpl();

        try {
            pageElementsImpl.savetoDatabase(elements);
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }

        request.setAttribute("databaseSaved", "File uploaded and saved into database");
        request.getRequestDispatcher("/index.jsp").forward(request, response);
    }


}









