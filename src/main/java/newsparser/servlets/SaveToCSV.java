package newsparser.servlets;

import com.opencsv.bean.*;
import com.opencsv.exceptions.*;
import newsparser.model.PageElements;
import newsparser.parser.Parser;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.*;
import java.nio.file.*;
import java.util.ArrayList;

//Servlet for CSV file generation
@WebServlet("/SaveToCSV")
public class SaveToCSV extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //Path where file will be generated
        String File_Name = "./newsparser.csv";
        try (
                Writer writer = Files.newBufferedWriter(Paths.get(File_Name))) {
            Parser parser = new Parser();
            ArrayList<PageElements> elements = parser.getPageElements();
            //writing ArrayList as CSV file using OpenCSV
            StatefulBeanToCsv<PageElements> beanToCsv = new StatefulBeanToCsvBuilder<PageElements>(writer).build();

            beanToCsv.write(elements);
        } catch (CsvRequiredFieldEmptyException | CsvDataTypeMismatchException e) {
            e.printStackTrace();
        }
        //sending attribute as download link
        request.setAttribute("download", "<a href=\"/DownloadCSV\">Download CSV File</a>");
        request.setAttribute("csvSaved", "CSV file generated and ready to download");
        request.getRequestDispatcher("/index.jsp").forward(request, response);
    }
}










