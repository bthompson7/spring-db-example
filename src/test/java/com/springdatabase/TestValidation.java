package com.springdatabase;

import org.junit.jupiter.api.Test;
import com.springdatabase.validation.ValidateUser;

import static org.assertj.core.api.Assertions.assertThat;

public class TestValidation {

	@Test
	public void testName() throws Exception {
		ValidateUser u = new ValidateUser();
		
		boolean isNameValid = u.isNameValid("Ben Thompson");
		
		assertThat(isNameValid);
	}
	
	
	@Test
	public void testValidEmail() throws Exception {
		ValidateUser u = new ValidateUser();
		
		boolean isEmailValid = u.isNameValid("somecoolemail@yahoo.org");
		
		assertThat(isEmailValid);
	}
	
	@Test
	public void testValidEmail2() throws Exception {
		ValidateUser u = new ValidateUser();
		
		boolean isEmailValid = u.isNameValid("somecool-email@yahoo.org");
		
		assertThat(isEmailValid);
	}
	
	@Test
	public void testValidEmail3() throws Exception {
		ValidateUser u = new ValidateUser();
		
		boolean isEmailValid = u.isNameValid("somecoolemail@some-site.org");
		
		assertThat(isEmailValid);
	}
	
	
	@Test
	public void testInvalidEmail() throws Exception {
		ValidateUser u = new ValidateUser();
		
		boolean isEmailValid = u.isNameValid("somecoolemail@yahoo........org");
		
		assertThat(!isEmailValid);
	}
	
	@Test
	public void testInvalidEmail2() throws Exception {
		ValidateUser u = new ValidateUser();
		
		boolean isEmailValid = u.isNameValid("somecoolemail@yaho$o.org");
		
		assertThat(!isEmailValid);
	}
	
	
	
	
	
}
