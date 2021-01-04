package org.dbms.javaApp;
import java.util.*;
import javax.mail.*;
import javax.mail.internet.*;
public class SendEmail {
	
	private static final String from="abc@gmail.com";
	
	public static void sendEmail( String to, String otp) {
		
		Properties properties=new Properties();
		properties.put("mail.smtp.host","smtp.gmail.com");
		properties.put("mail.smtp.port","465");
		properties.put("mail.smtp.ssl.enable","true");
		properties.put("mail.smtp.auth","true");
		
		Session session=Session.getDefaultInstance(properties,new javax.mail.Authenticator()
		{
			protected PasswordAuthentication getPasswordAuthentication() {

				return new PasswordAuthentication("abc@gmail.com", "password");
			}
		});
		session.setDebug(true);
		
		try {
			String msg=otp+" is your varification code ";
		      MimeMessage message = new MimeMessage(session);
		      message.setFrom(new InternetAddress(from));
		      message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
		      message.setSubject("Reset your password");
		      message.setText(msg);
		      Transport.send(message);
		      System.out.println("Sent message successfully....");
		   } catch (MessagingException mex) {
		      mex.printStackTrace();
		   }
		
	}
}

