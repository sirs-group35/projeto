package sirs.group35.ala.util;

import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class SendMail {

    public static void sendMail(String to, String firstName, String lastName, String fileName, String fileHash, String fileTimestamp) {

        String from = "alafirm.law@gmail.com";
        String host = "smtp.gmail.com";
        Properties properties = System.getProperties();

        properties.put("mail.smtp.host", host);
        properties.put("mail.smtp.port", "465");
        properties.put("mail.smtp.ssl.enable", "true");
        properties.put("mail.smtp.auth", "true");

        Session session = Session.getInstance(properties, new javax.mail.Authenticator() {

            protected PasswordAuthentication getPasswordAuthentication() {

                return new PasswordAuthentication("alafirm.law@gmail.com", "dxgvrmkjbmxvxrky");

            }

        });

        // Used to debug SMTP issues
        session.setDebug(false);

        try {

            MimeMessage message = new MimeMessage(session);
            message.setFrom(new InternetAddress(from));
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
            message.setSubject("File Submission Receipt");

            String content = "Dear " + firstName + " " + lastName + ",\n\n"
                    + "Thank you for submitting your file to ALA.\n"
                    + "Here is the receipt for your submission:\n\n"
                    + "File Name: " + fileName + "\n"
                    + "File Hash: " + fileHash + "\n"
                    + "File Timestamp: " + fileTimestamp + "\n\n"
                    + "Best regards,\n"
                    + "ALA Team";
            message.setText(content);

            System.out.println("Sending email...");
            Transport.send(message);
            System.out.println("Message sent successfully.");
        } catch (MessagingException mex) {
            mex.printStackTrace();
        }

    }

}