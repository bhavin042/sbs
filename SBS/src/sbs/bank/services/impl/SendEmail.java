package sbs.bank.services.impl;
import java.io.IOException;
import java.util.Properties;
 




import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
 
public class SendEmail {
 
	public void send(String id, String data, String input) throws IOException {
 
		final String uname = "sbsproject.group2@gmail.com";
		final String pwd = "sbsgroup@2";
 
		Properties props = new Properties();
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.starttls.enable", "true");
		props.put("mail.smtp.host", "smtp.gmail.com");
		props.put("mail.smtp.port", "587");
		
		System.out.println("In email");
		Session session = Session.getInstance(props,
		  new javax.mail.Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(uname, pwd);
			}
		  });
 
		Multipart multipart = new MimeMultipart();
		
		try {
 
			Message msg = new MimeMessage(session);
			msg.setFrom(new InternetAddress("sbsproject.group2@gmail.com"));
			msg.setRecipients(Message.RecipientType.TO,
				InternetAddress.parse(id));
			if(input.equals("OTP")){
				msg.setSubject("One Time Password");
				msg.setText("OTP : "
					+ data);
			}
			else if(input.equals("PKI")){
				msg.setSubject("PKI");
				System.out.println("Inside PKI ");
				MimeBodyPart attachPart = new MimeBodyPart();
				System.out.println("Set path");
				String attachFile = data;
				attachPart.attachFile(attachFile);
				System.out.println("attach file");
				multipart.addBodyPart(attachPart);
				System.out.println("add multipart body");
				msg.setContent(multipart);
				/*msg.setSubject("Notification From One Of the Users");
				msg.setText(data);*/
			}		
			System.out.println("set msg content");
			//msg.setContent(multipart);
			System.out.println("before send");
			Transport.send(msg);
			System.out.println("after send");
 
 
		} catch (MessagingException e) {
			//throw new RuntimeException(e);
			System.out.println("Exception in SendMailTLS"+e);
		}
	}
}