package Logic;
// Libs https://code.google.com/archive/p/javamail-android/downloads

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

/**
 * Класс для отправки писем
 */
public class MailSender extends javax.mail.Authenticator {

    private String user;
    private String password;
    private Session session;


    public MailSender()
    {
        user = "myshopweb555@gmail.com";
        password = "MyWebShop55";

        Properties props = new Properties();
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.socketFactory.port", "465");
        props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.port", "465");

        session = Session.getDefaultInstance(props,
                new javax.mail.Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(user, password);
                    }
                });
    }

    public static void sendMail(MailSender sender,String subject, String body, String recipients) throws Exception {
        try {
            MimeMessage message = new MimeMessage(sender.session);
            message.setFrom(new InternetAddress(sender.user));

            message.addRecipient(Message.RecipientType.TO, new InternetAddress(recipients));
            message.setSubject(subject);
            message.setText(body);

            //send message
            Transport.send(message);
            System.out.println("message sent successfully");
        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
    }
}