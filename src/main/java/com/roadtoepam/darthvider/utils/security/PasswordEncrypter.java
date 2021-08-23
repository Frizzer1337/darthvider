package com.roadtoepam.darthvider.utils.security;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.mindrot.jbcrypt.BCrypt;

import com.roadtoepam.darthvider.connectionpool.ConnectionFactory;

public class PasswordEncrypter {
	
	private static final String resource = "app.properties";
	private static final Properties appProperties = new Properties();
	private static final Logger logger= LogManager.getLogger();
	
	private static String salt;
	
	/**
	 * Encrypt.
	 *
	 * @param password to hash
	 * @return the hashed string
	 */
	
	public static String encrypt(String password) {
		
		try(InputStream inputStream = ConnectionFactory.class.getClassLoader().getResourceAsStream(resource);) {
			
			appProperties.load(inputStream);
			salt = appProperties.getProperty("salt");
			
		} catch (IOException e) {
			logger.warn("Error while getting salt from" + resource);
			throw new RuntimeException(e);
		}
		
		String hashedPassword = BCrypt.hashpw(password, salt);
		
		return hashedPassword;
			
	}
	
	private PasswordEncrypter() {};

}
