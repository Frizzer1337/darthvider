package com.roadtoepam.darthvider.model.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.roadtoepam.darthvider.exception.ServiceException;
import com.roadtoepam.darthvider.model.connectionpool.ConnectionFactory;
import com.roadtoepam.darthvider.model.util.security.EmailHasher;

public class HelpMailSender {
	
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
    		+ "	<div class=\"email_body\" style=\"width: 350px;height: 600px;background-color: #F6F6F6;margin-right: auto;margin-left:auto;\">\r\n"
    		+ "	<div class=\"email_body_in\" style=\"width: 300px;height: 500px;margin-right: auto;margin-left:auto;\">\r\n"
    		+ "	<header>\r\n"
    		+ "		<div class=\"email_logo\" style=\"display: flex;align-items: center;padding-top: 15px;margin-left: 80px;margin-right: 80px;\">\r\n"
    		+ "				<img src=\"https://pxizig.stripocdn.email/content/guids/CABINET_2d7df38e4f9bb7a1ce52a63df7d3cf30/images/43491631369238425.png\" class=\"email_logo_img\" style=\"height: 30px;margin-right: 10px;\">\r\n"
    		+ "				<span>DarthVider</span>\r\n"
    		+ "		</div>\r\n"
    		+ "	</header>\r\n"
    		+ "	<div class=\"email_main\" style=\"text-align: center;margin-top: 10px;height: 500px;width: 290px;margin-right: auto;margin-left: auto;background-color: white;\">\r\n"
    		+ "		<div class=\"email_text_upper email_text\" style=\"font-weight: 500;padding-top: 20px;\">Hi there, we got you're email and now you have ticket with number:<br> ";
    private static final String HTMLSECONDPART = ", <br> we will try to answer as fast as we can. \r\n"
    		+ "		</div>\r\n"
    		+ "		<div class=\"email_text_middle email_text\" style=\"font-weight: 500;padding-top: 20px;padding-bottom:30px;\">If you wait too long, just call us and say you're ticket number</div>\r\n"
    		+ "		<img src=\"https://pxizig.stripocdn.email/content/guids/CABINET_2d7df38e4f9bb7a1ce52a63df7d3cf30/images/helpmail.png\" height=\"250px\">\r\n"
    		+ "	</div>\r\n"
    		+ "	</div>\r\n"
    		+ "</body>\r\n"
    		+ "</html>";;
    private final Properties appProperties = new Properties();
    private MimeMessage mimeMessage;

    public void sendMessage(String email,long ticketId) throws ServiceException {
    	
        try {
        	
            initMessage(email,ticketId);
            Transport.send(mimeMessage);
            
        } catch (MessagingException e) {
        	
            logger.error(e);
            throw new ServiceException();
            
        }
    }

    private void initMessage(String email,long ticketId){
    	try (InputStream inputStream = ConnectionFactory.class.getClassLoader().getResourceAsStream(PATH);){
			appProperties.load(inputStream);
			Session session = createSession(appProperties);
			
	        mimeMessage = new MimeMessage(session);
	            mimeMessage.setSubject(SUBJECT);
	            mimeMessage.setContent(HTML+ticketId+HTMLSECONDPART,"text/html");
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