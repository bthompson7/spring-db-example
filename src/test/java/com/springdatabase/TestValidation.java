package com.springdatabase;

import org.junit.jupiter.api.Test;
import com.springdatabase.validation.ValidateUser;

import static org.assertj.core.api.Assertions.assertThat;

import org.aspectj.lang.annotation.Before;

public class TestValidation {

	ValidateUser u = new ValidateUser();

	
	@Test
	public void testValidName() throws Exception {
		
		boolean isNameValid = u.isNameValid("Ben Thompson");
		
		assertThat(isNameValid);
	}
	
	@Test
	public void testValidName2() throws Exception {
		boolean isNameValid = u.isNameValid("Ben-Philip Thompson");
		assertThat(isNameValid);
	}
	
	@Test
	public void testInvalidName() throws Exception {
		boolean isNameValid = u.isNameValid("Ben$ Thompson");
		assertThat(isNameValid);
	}
	
	
	
	@Test
	public void testValidEmail() throws Exception {
		boolean isEmailValid = u.isNameValid("somecoolemail@yahoo.org");
		assertThat(isEmailValid);
	}
	
	@Test
	public void testValidEmail2() throws Exception {
		boolean isEmailValid = u.isNameValid("somecool-email@yahoo.org");
		assertThat(isEmailValid);
	}
	
	@Test
	public void testValidEmail3() throws Exception {
		boolean isEmailValid = u.isNameValid("somecoolemail@some-site.org");
		assertThat(isEmailValid);
	}
	
	
	@Test
	public void testInvalidEmail() throws Exception {
		boolean isEmailValid = u.isNameValid("somecoolemail@yahoo........org");
		assertThat(!isEmailValid);
	}
	
	@Test
	public void testInvalidEmail2() throws Exception {		
		boolean isEmailValid = u.isNameValid("somecoolemail@yaho$o.org");
		assertThat(!isEmailValid);
	}
	
	
	
	
	
}
