package com.springdatabase.validation;

import java.util.regex.*;

public class ValidateUser {
   
      
      public ValidateUser() {
    	  
      }
      
      
      public boolean isNameValid(String name) {
    	  
    	  	  String name_pattern = "[\\d\\w-]+";
    	      Pattern r = Pattern.compile(name_pattern);    	      
    	      Matcher m = r.matcher(name);
    	      
    	      if(m.find()) {
    	    	  return true;
    	      }
    	      
    	      return false;
      }
      
      
      public boolean isEmailValid(String email) {
    	  
    	  String email_pattern = "[\\w\\d-]+[@]{1}[-\\w\\d]+[.]{1}[-\\w\\d]+";
	      Pattern r = Pattern.compile(email_pattern);    	      
	      Matcher m = r.matcher(email);
	      
	      if(m.find()) {
	    	  return true;
	      }
	      
	      return false;
	      
      }
}
