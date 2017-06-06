package com.hcentive.hackathon.commons.email;

import java.io.ByteArrayOutputStream;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Properties;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.mail.util.ByteArrayDataSource;

import com.hcentive.hackathon.commons.exception.BaseCheckedException;

/**
 * @author Nitin.Gupta
 * 
 */
public class MailUtils {

	// XXX - Move these hardcodings to a prioperties file.

//	private static final String HOST = "smtp.mandrillapp.com";
	private static final String HOST = "email-smtp.eu-west-1.amazonaws.com";
	private static final String PORT = "587";
	private static final String USER = "AKIAI7GY7R4PTWV663TA";
//	private static final String PASSWORD = "ZZp_rBVmlEjJWTl2PgSnYA";
	private static final String PASSWORD = "AgEee/Dqb1QYHwQY3a/fWwxRQBM7HHYBCeGypP9vmH+q";
	private static final String FROM = "nitin@ishimaya.com";
	private static final String FROM_NAME = "Ishi Maya Operations";

	public static void sendEmailWithAttachments(String[] toAddresses,
			String subject, String message, String[] attachFiles)
			throws BaseCheckedException {

		List<MimeBodyPart> attachments = new ArrayList<MimeBodyPart>();

		if (attachFiles != null && attachFiles.length > 0) {

			try {
				for (String filePath : attachFiles) {

					if (filePath == null) {
						continue;
					}

					MimeBodyPart mimeBodyPart = new MimeBodyPart();
					mimeBodyPart.attachFile(filePath);
					attachments.add(mimeBodyPart);
				}
			} catch (Exception e) {
				throw new BaseCheckedException(e);
			}

		}

		sendEmailWithAttachments(toAddresses, subject, message,
				attachments.toArray(new MimeBodyPart[attachments.size()]));

	}

	public static void sendEmailWithAttachments(String[] toAddresses,
			String subject, String message, ByteArrayOutputStream[] baosArray)
			throws AddressException, MessagingException,
			UnsupportedEncodingException, BaseCheckedException {

		List<MimeBodyPart> attachments = new ArrayList<MimeBodyPart>();

		if (baosArray != null && baosArray.length > 0) {

			for (ByteArrayOutputStream baos : baosArray) {

				DataSource aAttachment = new ByteArrayDataSource(
						baos.toByteArray(), "application/octet-stream");
				MimeBodyPart mimeBodyPart = new MimeBodyPart();
				mimeBodyPart.setDataHandler(new DataHandler(aAttachment));

				attachments.add(mimeBodyPart);
			}

		}

		sendEmailWithAttachments(toAddresses, subject, message,
				attachments.toArray(new MimeBodyPart[attachments.size()]));

	}

	public static void sendEmail(String[] toAddresses, String subject,
			String message) throws BaseCheckedException {
		sendEmailWithAttachments(toAddresses, subject, message,
				new MimeBodyPart[] {});
	}

	public static void sendEmailWithAttachments(String[] toAddresses,
			String subject, String message, MimeBodyPart[] attachments)
			throws BaseCheckedException {

		// sets SMTP server properties
		Properties properties = new Properties();
		properties.put("mail.smtp.host", HOST);
		properties.put("mail.smtp.port", PORT);
		properties.put("mail.smtp.auth", "true");
		properties.put("mail.smtp.starttls.enable", "true");
		properties.put("mail.user", USER);
		properties.put("mail.password", PASSWORD);

		// creates a new session with an authenticator
		Authenticator auth = new Authenticator() {
			public PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(USER, PASSWORD);
			}
		};
		Session session = Session.getInstance(properties, auth);

		try {
			// creates a new e-mail message
			Message msg = new MimeMessage(session);

			msg.setFrom(new InternetAddress(FROM, FROM_NAME));
			/*
			 * msg.setRecipient(RecipientType.BCC, new InternetAddress(
			 * "nitin@ishimaya.com"));
			 */

			InternetAddress[] addresses = new InternetAddress[toAddresses.length];

			for (int i = 0; i < toAddresses.length; i++) {
				addresses[i] = new InternetAddress(toAddresses[i]);
			}

			msg.setRecipients(Message.RecipientType.TO, addresses);
			msg.setSubject(subject);
			msg.setSentDate(new Date());

			// creates message part
			MimeBodyPart messageBodyPart = new MimeBodyPart();
			messageBodyPart.setContent(message, "text/html");

			// creates multi-part
			Multipart multipart = new MimeMultipart();
			multipart.addBodyPart(messageBodyPart);

			// adds attachments
			if (attachments != null && attachments.length > 0) {
				for (int i = 0; i < attachments.length; i++) {
					multipart.addBodyPart(attachments[i]);
				}
			}

			// sets the multi-part as e-mail's content
			msg.setContent(multipart);

			// sends the e-mail
			Transport.send(msg);

		} catch (Exception e) {
			throw new BaseCheckedException(
					"Exception occured while sending email.", e);
		}
	}

	/**
	 * Test sending e-mail with attachments
	 */

	/*
	 * public static void main(String[] args) { // SMTP info String host =
	 * "smtp.mandrillapp.com"; String port = "587"; String mailFrom =
	 * "nitin@ishimaya.com"; String password = "ZZp_rBVmlEjJWTl2PgSnYA";
	 * 
	 * // message info String mailTo = "nitin@ishimaya.com"; String subject =
	 * "New email with attachments"; String message =
	 * "I have some attachments for you.";
	 * 
	 * // attachments String[] attachFiles = new String[1]; attachFiles[0] =
	 * "D:/Nitin-gupta.jpg";
	 * 
	 * try { sendEmailWithAttachments(host, port, mailFrom, password, mailTo,
	 * subject, message, attachFiles); System.out.println("Email sent."); }
	 * catch (Exception ex) { System.out.println("Could not send email.");
	 * ex.printStackTrace(); } }
	 */

	public static void main(String[] args) {
		System.out.println("sending mail");
		try {
			MailUtils.sendEmail(new String[] { "nitin@ishimaya.com" },
					"Test Message", "test Message");
		} catch (BaseCheckedException e) {
			e.printStackTrace();
		}

	}
}
