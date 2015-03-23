package emailjava;

import java.util.Properties;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

/**
 *
 * @author thrasos
 * http://thrasos.net
 * 
 * send an email using Gmail or any other email provider with Java.
 * 
 * This example uses the javax library
 */
public class EmailJava {

    static Properties mailServerProperties;
    static Session getMailSession;
    static MimeMessage generateMailMessage;

    public static void main(String args[]) throws AddressException, MessagingException {
        generateAndSendEmail("Subject goes here", "Body of the email goes here");
        System.out.println("\n\n ===>  Your Java code has sent and email, good work! Now Check your email..");
    }

    public static void generateAndSendEmail(String subject, String body) throws AddressException, MessagingException {

        /*
         * Step 1 set the mail server properties[port,auth, ttls]
         */
        System.out.println("\n 1st ===>  Mail Server Properties.");
        mailServerProperties = System.getProperties();
        mailServerProperties.put("mail.smtp.port", "587");
        mailServerProperties.put("mail.smtp.auth", "true");
        mailServerProperties.put("mail.smtp.starttls.enable", "true");
        System.out.println("Mail Server Properties are OK");

        /*
         * Step 2 get the mail server Session[MimeMessage, recipient, Subject, Body]
         */
        System.out.println("\n\n 2nd ===> get Mail Session..");
        getMailSession = Session.getDefaultInstance(mailServerProperties, null);
        generateMailMessage = new MimeMessage(getMailSession);
        //recipient
        generateMailMessage.addRecipient(Message.RecipientType.TO, new InternetAddress("mail@thrasos.net"));
        //subject
        generateMailMessage.setSubject(subject);
        //email Body       
        generateMailMessage.setContent(body, "text/html");
        System.out.println("Mail Session created and is OK");

        /*
         * Step 3 get the mail server Session and Send Email.
         */
        System.out.println("\n\n 3rd ===> Get Session and Send mail");
        Transport transport = getMailSession.getTransport("smtp");
        // Enter your  GMAIL Username or email and Password . or any other settings depending on your email provider.
        transport.connect("smtp.gmail.com", "USERNAME", "PASSWORD");
        transport.sendMessage(generateMailMessage, generateMailMessage.getAllRecipients());
        transport.close();
    }
}
