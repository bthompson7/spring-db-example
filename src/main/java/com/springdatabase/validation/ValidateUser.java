package com.springdatabase.validation;

import java.util.regex.*;

public class ValidateUser {
   
      
      public ValidateUser() {
    	  
      }
      
      
      public boolean isNameValid(String name) {
    	  
    	  	  String name_pattern = "[a-zA-Z0-9\\s]+";
    	      Pattern r = Pattern.compile(name_pattern);    	      
    	      Matcher m = r.matcher(name);
    	      
    	      if(m.matches()) {
    	    	  return true;
    	      }
    	      
    	      return false;
      }
      
      
      public boolean isEmailValid(String email) {
    	  
    	  String email_pattern = "[a-zA-Z0-9-]+[@]{1}[a-zA-Z0-9-]+[.]{1}[a-zA-Z0-9-]+";
	      Pattern r = Pattern.compile(email_pattern);    	      
	      Matcher m = r.matcher(email);
	      
	      if(m.matches()) {
	    	  return true;
	      }
	      
	      return false;
	      
      }
}
