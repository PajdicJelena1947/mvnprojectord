package com.service;

import com.entity.Pacijent;
import java.util.Properties;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.springframework.stereotype.Service;

/**
 *
 * @author jelena.pajdic
 */
@Service("slanjeMejla")
public class SlanjeMejlaService {
    Properties mailServerProperties;
	 Session getMailSession;
	MimeMessage generateMailMessage;
        
        
        public  void generateAndSendEmail(Pacijent karton,String email,String tekst) throws AddressException, MessagingException {
 
		
		mailServerProperties = System.getProperties();
		mailServerProperties.put("mail.smtp.port", "587");
		mailServerProperties.put("mail.smtp.auth", "true");
		mailServerProperties.put("mail.smtp.starttls.enable", "true");
		
		
		getMailSession = javax.mail.Session.getDefaultInstance(mailServerProperties, null);
		generateMailMessage = new MimeMessage(getMailSession);
		generateMailMessage.addRecipient(Message.RecipientType.TO, new InternetAddress(email));
		generateMailMessage.setSubject("Pacijent-lekar");
		
		generateMailMessage.setContent(tekst, "text/html");
		
 
	
	
		Transport transport = getMailSession.getTransport("smtp");
 
		
		transport.connect("smtp.gmail.com",karton.getEmail(), karton.getSifra());
		transport.sendMessage(generateMailMessage, generateMailMessage.getAllRecipients());
		transport.close();
	}
}
