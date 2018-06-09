package samplebootapp.mailcore.util;

import java.util.Date;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import com.google.gson.Gson;

import samplebootapp.mailcore.domain.EmailRequest;

public class TestMailUtil {

	public static void sendTestEmail(String payload) {
		try {
			Gson gson = new Gson();
			EmailRequest rqstObj = gson.fromJson(payload, EmailRequest.class);

			String subject = "Just another test email";
			String body = "Hi " + rqstObj.getName() + ", \n The test email has reached your mailbox.";
			String toEmail = rqstObj.getEmail();

			Properties props = System.getProperties();

			final String username = "mailtestcore@gmail.com";
			final String password = "!@#$Test";

			props.put("mail.smtp.host", "smtp.gmail.com");
			props.put("mail.smtp.socketFactory.port", "465");
			props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
			props.put("mail.smtp.auth", "true");
			props.put("mail.smtp.port", "465");

			Session session = Session.getDefaultInstance(props, new javax.mail.Authenticator() {
				protected PasswordAuthentication getPasswordAuthentication() {
					return new PasswordAuthentication(username, password);
				}
			});
			MimeMessage msg = new MimeMessage(session);
			msg.addHeader("Content-type", "text/HTML; charset=UTF-8");
			msg.addHeader("format", "flowed");
			msg.addHeader("Content-Transfer-Encoding", "8bit");

			msg.setFrom(new InternetAddress("mailtestcore@gmail.com", "MAIL-CORE"));

			msg.setReplyTo(InternetAddress.parse("mailtestcore@gmail.com", false));

			msg.setSubject(subject, "UTF-8");

			msg.setText(body, "UTF-8");

			msg.setSentDate(new Date());

			msg.setRecipients(Message.RecipientType.TO, InternetAddress.parse(toEmail, false));
			Transport.send(msg);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
