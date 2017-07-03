package com.peiwc.billing.process.mail;

import java.util.Properties;

import javax.mail.Address;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * Created by carlos.acosta on 16/06/2017.
 */
@Component
public class MailSender {
	private static final String DEFAULT_SMTP_PORT = "25";
	private static final Logger LOGGER = Logger.getLogger(MailSender.class);
	@Value("${mail.send.enabled}")
	private boolean isEnabled;
	@Value("${mail.to.subject}")
	private String mailSubject;
	@Value("${mail.send.from}")
	private String mailFrom;

	/**
	 * sends a mail message indicating the finish status of the current process.
	 *
	 * @param mailMessage
	 *            message argument that contains the body text.
	 */
	public void sendMailMessage(final String mailMessage) {
		if (isEnabled) {
			try {
				final Properties properties = new Properties();
				final String smtpHost = System.getProperty("email.server.address");
				properties.setProperty("mail.smtp.auth", "false");
				properties.setProperty("mail.smtp.host", smtpHost);
				properties.setProperty("mail.smtp.port", MailSender.DEFAULT_SMTP_PORT);
				final Session session = Session.getInstance(properties);
				final Message message = new MimeMessage(session);
				final String responsible = System.getProperty("email.notification.address");
				message.setRecipient(Message.RecipientType.TO, new InternetAddress(responsible));
				final Address[] from = new Address[] { new InternetAddress(mailFrom) };
				message.addFrom(from);
				message.setSubject(mailSubject);
				message.setText(mailMessage);
				Transport.send(message);
			} catch (final MessagingException ex) {
				MailSender.LOGGER.error(ex, ex);
			}
		}
	}
}