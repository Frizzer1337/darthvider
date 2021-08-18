package com.roadtoepam.darthvider.utils.security;

import org.mindrot.jbcrypt.BCrypt;

public class PasswordEncrypter {
	
	static String salt = BCrypt.gensalt(10);//Correct?
	
	public static String encrypt(String password) {
		
		String hashedPassword = BCrypt.hashpw(password, salt);
		
		return hashedPassword;
			
	}
	
	private PasswordEncrypter() {};

}
