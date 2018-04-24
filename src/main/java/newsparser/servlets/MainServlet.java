package newsparser.servlets;

import newsparser.model.PageElements;
import newsparser.parser.Parser;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.*;
import java.util.ArrayList;

//Main Servlet of App (Start and Clear buttons functions)
@WebServlet("/MainServlet")
public class MainServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Parser parser = new Parser();
        String button = request.getParameter("button");

        if (button.equals("start")) {
            ArrayList<PageElements> list = parser.getPageElements();
            request.getServletContext().setAttribute("pageElementsList", list);
        } else {
            String clean = "true";
            request.setAttribute("clean", clean);

            File file = new File("newsparser.csv");
            file.delete();

        }
        request.getRequestDispatcher("/index.jsp").forward(request, response);
    }







}

