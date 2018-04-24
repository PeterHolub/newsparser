package newsparser.servlets;

import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.*;
//Servlet for file download
@WebServlet("/DownloadCSV")
public class DownloadCSV extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //Settings for response (send's file with .csv extension)
        response.setContentType("application/octet-stream");
        response.setHeader("Content-Disposition", "attachment;filename=newsparser.csv");

        //Path to file for reading and sending as response
        File file = new File("./newsparser.csv");
        FileInputStream fileIn = new FileInputStream(file);
        ServletOutputStream out = response.getOutputStream();

        byte[] outputByte = new byte[4096];

        while (fileIn.read(outputByte, 0, 4096) != -1) {
            out.write(outputByte, 0, 4096);
        }
        fileIn.close();
        out.flush();
        out.close();
        request.getRequestDispatcher("/index.jsp").forward(request, response);
    }
}
