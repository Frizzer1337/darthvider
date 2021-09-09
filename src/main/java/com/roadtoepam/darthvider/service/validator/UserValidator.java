package com.roadtoepam.darthvider.service.validator;

/**
 * The class UserValidator used to validate user data.
 */
public class UserValidator {
	
	 private static final String LOGIN_REGEX = "^[\\w_]{6,20}$";
	 private static final String EMAIL_REGEX= "^([\\w-\\.]+){1,64}@([\\w&&[^_]]+){2,255}.[a-z]{2,}$";
     private static final String PASSWORD_REGEX = "^.{8,30}$";
    
     public static boolean validateLogin(String login) {
    	 
    	 return validate(login,LOGIN_REGEX);
    	 
     }
     
     public static boolean validateEmail(String email) {
    	 
    	 return validate(email,EMAIL_REGEX);
    	 
     }
     
     public static boolean validatePassword(String password) {
 	 
    	 return validate(password,PASSWORD_REGEX);
 	 
     }
     
     private UserValidator(){};
     
    
     /**
      * Validate method used to reduce same code amount.
      *
      * @param any string data
      * @param regex pattern
      * @return true if data matches pattern
      */
     private static boolean validate(String data,String regex) {
    	 
    	return data!=null && data.matches(regex);
    	 
     }


}
