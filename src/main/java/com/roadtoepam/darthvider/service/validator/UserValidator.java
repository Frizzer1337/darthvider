package com.roadtoepam.darthvider.service.validator;

/**
 * The class UserValidator used to validate user data.
 */
public class UserValidator {
	
	 private static final String LOGIN_REGEX = "^[\\w_]{6,20}$";
	 private static final String EMAIL_REGEX= "^([\\w-\\.]+){1,64}@([\\w&&[^_]]+){2,255}.[a-z]{2,}$";
     private static final String PASSWORD_REGEX = "^.{8,30}$";
     private static final String PHONE_REGEX = "^(\\+375|80)(29|25|44|33)(\\d{3})(\\d{2})(\\d{2})$";
     private static final String CITY_REGEX = "^[\\p{L}_]{1,30}$";
     private static final String NAME_REGEX = "^[\\p{L}_]{1,30}$";
     private static final String SURNAME_REGEX = "^[\\p{L}_]{1,30}$";
     
    
     public boolean  validatePhone(String phone) {
 		
    	 return validate(phone,PHONE_REGEX);
    	 
 	}
     
    public boolean  validateCity(String city) {
  		
    	 return validate(city,CITY_REGEX);
    	 
 	}

    public boolean  validateName(String name) {
  		
   	 return validate(name,NAME_REGEX);
   	 
	}
    
    public boolean  validateSurname(String surname) {
  		
   	 return validate(surname,SURNAME_REGEX);
   	 
	}
     
     public boolean validateLogin(String login) {
    	 
    	 return validate(login,LOGIN_REGEX);
    	 
     }
     
     public boolean validateEmail(String email) {
    	 
    	 return validate(email,EMAIL_REGEX);
    	 
     }
     
     public boolean validatePassword(String password) {
 	 
    	 return validate(password,PASSWORD_REGEX);
 	 
     }
     
    
     /**
      * Validate method used to reduce same code amount.
      *
      * @param any string data
      * @param regex pattern
      * @return true if data matches pattern and not null
      */
     private static boolean validate(String data,String regex) {
    	 
    	return data!=null && data.matches(regex);
    	 
     }


}
