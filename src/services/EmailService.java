package services;

import javax.mail.Session;
import java.util.Properties;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class EmailService {
    private String senderEmail;
    private String senderPassword;
    private Properties properties;

    public EmailService() {
        this.senderEmail = "rayankurma@gmail.com";
        this.senderPassword = "scpp lrjv jwvd yriw";
    }

        public void sendEmail(String recipientEmail, String subject, String body) {

            this.properties = new Properties();


        // Email properties

        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.starttls.enable", "true");
        properties.put("mail.smtp.host", "smtp.gmail.com");
        properties.put("mail.smtp.port", "587");

        // Create a mail session with the specified properties
        Session session = Session.getInstance(properties, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(senderEmail, senderPassword);
            }
        });

        try {
            // Create a MimeMessage object
            MimeMessage message = new MimeMessage(session);

            // Set the sender email address
            message.setFrom(new InternetAddress(senderEmail));

            // Set the recipient email address
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(recipientEmail));

            // Set the email subject
            message.setSubject(subject);

            // Set the email body
            message.setText(body);

            // Send the email
            Transport.send(message);

            System.out.println("Email sent successfully.");

        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }

}
