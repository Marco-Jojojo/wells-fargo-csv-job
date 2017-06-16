package com.peiwc.billing.process.mail;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

/**
 * Created by carlos.acosta on 16/06/2017.
 */
@Component
public class MailSender {

    private static final String DEFAULT_SMTP_PORT = "25";

    @Value("${mail.send.enabled}")
    private boolean isEnabled;

    @Value("${mail.to.subject}")
    private String mailSubject;

    /**
     * sends a mail message indicating the finish status of the current process.
     *
     * @param mailMessage message argument that contains the body text.
     * @throws MessagingException if message could not be sent.
     */
    public void sendMailMessage(final String mailMessage) throws MessagingException {
        if (isEnabled) {
            final Properties properties = new Properties();
            final String smtpHost = System.getProperty("email.server.address");
            properties.setProperty("mail.smtp.auth", "false");
            properties.setProperty("mail.smtp.host", smtpHost);
            properties.setProperty("mail.smtp.port", DEFAULT_SMTP_PORT);
            final Session session = Session.getInstance(properties);
            final Message message = new MimeMessage(session);
            final String responsible = System.getProperty("email.notification.address");
            message.setRecipient(Message.RecipientType.TO, new InternetAddress(responsible));
            message.setSubject(mailSubject);
            message.setText(mailMessage);
            Transport.send(message);
        }
    }

}