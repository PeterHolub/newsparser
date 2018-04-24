package newsparser.servlets;

import newsparser.properties.PropertiesFile;

import java.io.IOException;
import java.util.Properties;
import javax.activation.*;
import javax.mail.*;
import javax.mail.internet.*;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

//Servlet for sending e-mail
@WebServlet("/SendEmail")
public class SendEmail extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        PropertiesFile propertiesFile = new PropertiesFile();
        // Recipient's email ID needs to be mentioned.
        String to = request.getParameter("email");
        // Sender's email ID needs to be mentioned
        String from = propertiesFile.getMailProperties().getProperty("from");
        final String username = propertiesFile.getMailProperties().getProperty("username");
        final String password = propertiesFile.getMailProperties().getProperty("password");

        String host = propertiesFile.getMailProperties().getProperty("host");

        Properties properties = new Properties();
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.starttls.enable", "true");
        properties.put("mail.smtp.host", host);
        properties.put("mail.smtp.port", "587");

        // Get the Session object.
        Session session = Session.getInstance(properties,
                new javax.mail.Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(username, password);
                    }
                });

        try {

            // Create a default MimeMessage object.
            Message message = new MimeMessage(session);

            // Set From: header field of the header.
            message.setFrom(new InternetAddress(from));

            // Set To: header field of the header.
            message.setRecipients(Message.RecipientType.TO,
                    InternetAddress.parse(to));

            // Set Subject: header field
            message.setSubject(propertiesFile.getMailProperties().getProperty("subject"));

            // Create the message part
            BodyPart messageBodyPart = new MimeBodyPart();

            // Now set the actual message
            messageBodyPart.setText(propertiesFile.getMailProperties().getProperty("text"));

            // Create a multipar message
            Multipart multipart = new MimeMultipart();

            // Set text message part
            multipart.addBodyPart(messageBodyPart);

            // Part two is attachment
            messageBodyPart = new MimeBodyPart();
            String filename = propertiesFile.getMailProperties().getProperty("filename");
            DataSource source = new FileDataSource(filename);
            messageBodyPart.setDataHandler(new DataHandler(source));
            messageBodyPart.setFileName(filename);
            multipart.addBodyPart(messageBodyPart);

            // Send the complete message parts
            message.setContent(multipart);

            // Send message
            Transport.send(message);

        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
        request.setAttribute("download", "<a href=\"/SVFileDownload\">Download CVS file</a>");
        request.setAttribute("send", "Your email was sent successfully!");
        request.getRequestDispatcher("/index.jsp").forward(request, response);
    }
}