package tn.esprit.utils;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

public class MailController {

    public MailController() {
    }

    public void SendMail(String st) throws MessagingException {
        Properties properties = new Properties();
        properties.put("mail.smtp.host", "smtp.gmail.com");
        properties.put("mail.smtp.port", "587");
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.starttls.enable", "true");

        Authenticator authenticator = new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication("ahmeddhouioui29@gmail.com", "feal xhip bteo otvu");//szwv ifts mrbf fzoa
            }
        };
        Session session = Session.getInstance(properties, authenticator);
        MimeMessage message = new MimeMessage(session);
        message.setFrom(new InternetAddress("ahmeddhouioui29@gmail.com"));
        message.addRecipient(Message.RecipientType.TO, new InternetAddress(st));
        message.setSubject("Réservation");
        message.setText("Reservation");


        Transport.send(message);
    }

}