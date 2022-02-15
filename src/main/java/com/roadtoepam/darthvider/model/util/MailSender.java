package com.roadtoepam.darthvider.model.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.roadtoepam.darthvider.exception.ServiceException;
import com.roadtoepam.darthvider.model.connectionpool.ConnectionFactory;
import com.roadtoepam.darthvider.model.util.security.EmailHasher;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;

public class MailSender {
	
	    private final Logger logger = LogManager.getLogger();
	    private static final String PATH = "app.properties";
	    private static final String NAME = "mail.user.name";
	    private static final String PASSWORD = "mail.user.password";
	    private static final String SUBJECT = "DarthVider.com";
	    private static final String HTML = "<!DOCTYPE html>\r\n"
	    		+ "<html lang=\"en\">\r\n"
	    		+ "<head>\r\n"
	    		+ "	<meta charset=\"UTF-8\">\r\n"
	    		+ "	</head></html><html xmlns=\"http://www.w3.org/1999/xhtml\" xmlns:o=\"urn:schemas-microsoft-com:office:office\" style=\"font-family:arial, 'helvetica neue', helvetica, sans-serif\">\r\n"
	    		+ "	<title>DarthVider.by-internet to every house!</title>\r\n"
	    		+ "	\r\n"
	    		+ "\r\n"
	    		+ "<body id=\"body\" style=\"font-family: sans-serif;font-size: 18px;font-weight: 600;\">\r\n"
	    		+ "	<div class=\"email_body\" style=\"width: 350px;height: 850px;background-color: #F6F6F6;margin-right: auto;margin-left:auto;\">\r\n"
	    		+ "	<div class=\"email_body_in\" style=\"width: 300px;height: 850px;margin-right: auto;margin-left:auto;\">\r\n"
	    		+ "	<header>\r\n"
	    		+ "		<div class=\"email_logo\" style=\"display: flex;align-items: center;padding-top: 15px;margin-left: 80px;margin-right: 80px;\">\r\n"
	    		+ "				<img src=\"https://pxizig.stripocdn.email/content/guids/CABINET_2d7df38e4f9bb7a1ce52a63df7d3cf30/images/43491631369238425.png\" class=\"email_logo_img\" style=\"height: 30px;margin-right: 10px;\">\r\n"
	    		+ "				<span>DarthVider</span>\r\n"
	    		+ "		</div>\r\n"
	    		+ "	</header>\r\n"
	    		+ "	<div class=\"email_main\" style=\"text-align: center;margin-top: 10px;height: 700px;width: 290px;margin-right: auto;margin-left: auto;background-color: white;\">\r\n"
	    		+ "		<div class=\"email_text_upper email_text\" style=\"font-weight: 500;padding-top: 20px;\">Before you go out and became a part of our big family, please verify your email adress.\r\n"
	    		+ "		</div>\r\n"
	    		+ "		<div class=\"email_text_middle email_text\" style=\"font-weight: 500;padding-top: 20px;\">We cant wait to learn more about you.\r\n"
	    		+ "		So please add information about yourself in your account page!	</div>\r\n"
	    		+ "		<img src=\"https://pxizig.stripocdn.email/content/guids/CABINET_2d7df38e4f9bb7a1ce52a63df7d3cf30/images/50051631370241803.png\" height=\"300px\">\r\n"
	    		+ "		<div class=\"email_text_bottom email_text\" style=\"font-weight: 500;\">Before you go out and became a part of our big family, please verify your email adress.\r\n"
	    		+ "		</div>\r\n"
	    		+ "		<a href=\"http://localhost:8080/darthvider/controller?command=verifyUser&user_id=";
	    private static final String HTMLSECONDPART = "\" class=\"email_button primary_button\" style=\"width: 150px; height:40px; border: 1px solid #b62c37;margin-left: auto;text-decoration: none;padding: 0 auto;display: flex;color: #b62c37;border-radius: 20px;margin-top: 10px;margin-right: auto;\">\r\n"
	    		+ "			<span class=\"confrim_text\" style=\"margin:auto;\">Confrim email</span></a>\r\n"
	    		+ "	</div>\r\n"
	    		+ "	</div>\r\n"
	    		+ "	</div>\r\n"
	    		+ "</body>\r\n"
	    		+ "</html>";;
	    private final Properties appProperties = new Properties();
	    private MimeMessage mimeMessage;

	    public void sendMessage(long id, String email) throws ServiceException {
	        try {
	            initMessage(id, email);
	            Transport.send(mimeMessage);
	        } catch (MessagingException e) {
	            logger.error(e);
	            throw new ServiceException();
	        }
	    }

	    private void initMessage(long id, String email){
	    	try (InputStream inputStream = ConnectionFactory.class.getClassLoader().getResourceAsStream(PATH);){
				appProperties.load(inputStream);
				Session session = createSession(appProperties);
				
				var hasher = new EmailHasher();
				long hashedId = hasher.hashId(id);
				
		        mimeMessage = new MimeMessage(session);
		            mimeMessage.setSubject(SUBJECT);
		            mimeMessage.setContent(HTML+hashedId+HTMLSECONDPART,"text/html");
		            mimeMessage.setRecipient(Message.RecipientType.TO, new InternetAddress(email));
		            
			} catch (IOException e) {
				logger.error("Error while reading" + PATH);
	        } catch (MessagingException e) {
	            logger.error("Error while creating mail",e);
	        }
	    }

	    private Session createSession(Properties properties){
	        String name = properties.getProperty(NAME);
	        String password = properties.getProperty(PASSWORD);
	        return Session.getDefaultInstance(properties, new Authenticator() {
	            @Override
	            protected PasswordAuthentication getPasswordAuthentication() {
	                return new PasswordAuthentication(name, password);
	            }
	        });
	    }
	    

}
