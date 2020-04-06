package org.computerShop.email;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.Properties;

public class SendEmail {

    private final String username = "KzzxD29@gmail.com";
    private final String password = "291099asd";

    public void sendMail(String emailTo, String title, String text){
        Properties prop = new Properties();

        prop.put("mail.smtp.host", "smtp.gmail.com");
        prop.put("mail.smtp.port", "465");
        prop.put("mail.smtp.auth", "true");
        prop.put("mail.smtp.socketFactory.port", "465");
        prop.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");

        Session sessions = Session.getInstance(prop, new javax.mail.Authenticator(){
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(username, password);
            }
        });


        try {
            Message message = new MimeMessage(sessions);
            message.setFrom(new InternetAddress("KzzxD29@gmail.com"));
            message.setRecipients(
                    Message.RecipientType.TO,
                    InternetAddress.parse(emailTo)
            );
            message.setSubject(title);
            message.setContent(text, "text/html;charset=utf-8");
            message.setSentDate(Date.from(Instant.now()));
            Transport.send(message);
        } catch (MessagingException e){

        }
    }


}
