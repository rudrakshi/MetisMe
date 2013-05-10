package com.metisme.model;

import java.util.Properties;
import javax.mail.Message;
import javax.mail.Session;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.InternetAddress;
import javax.mail.Transport;

public class Mail {
    public static void sendMail(String email,String message) {

        String fromAddress = "rudi.4689@gmail.com";
        String toAddress = email;
        String subject = "MetisMe Updates";
        
        Properties properties = System.getProperties();
        properties.put("mail.smtps.host", "smtp.gmail.com");
        properties.put("mail.smtps.auth", "true");

        Session session2 = Session.getInstance(properties);
        MimeMessage msg = new MimeMessage(session2);

        try {
                msg.setFrom(new InternetAddress(fromAddress));

                msg.addRecipients(Message.RecipientType.TO, toAddress);
                msg.setSubject(subject);
                msg.setText(message);

                Transport tr1 = session2.getTransport("smtps");

                tr1.connect("smtp.gmail.com", fromAddress, "rudrakshibargal");
                tr1.sendMessage(msg, msg.getAllRecipients());
                tr1.close();

        } catch (Exception e) {
                System.out.println(e);
        }
    }
}
