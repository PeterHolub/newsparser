package com.peterholub.newsparser.services;

import org.springframework.boot.autoconfigure.mail.MailProperties;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.javamail.*;
import org.springframework.stereotype.Service;
import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.File;

@Service
public class EmailServiceImpl implements EmailService {
   private JavaMailSender emailSender;
   private MailProperties mailProperties;

    public EmailServiceImpl(JavaMailSender emailSender, MailProperties mailProperties) {
        this.emailSender = emailSender;
        this.mailProperties = mailProperties;
    }

    public void sendEmail(String to) {

        try {
            MimeMessage message = emailSender.createMimeMessage();
            // pass 'true' to the constructor to create a multipart message
            MimeMessageHelper helper = new MimeMessageHelper(message, true);

            helper.setTo(to);
            helper.setSubject(mailProperties.getProperties().get("subject"));
            helper.setText(mailProperties.getProperties().get("text"));

            FileSystemResource file = new FileSystemResource(new File(mailProperties.getProperties().get("file")));
            helper.addAttachment("Newsparser", file);

            emailSender.send(message);
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }
}

