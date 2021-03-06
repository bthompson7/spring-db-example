package com.springdatabase;

import org.junit.jupiter.api.Test;
import com.springdatabase.validation.ValidateUser;

import static org.assertj.core.api.Assertions.assertThat;

import org.aspectj.lang.annotation.Before;

public class TestValidation {

	ValidateUser u = new ValidateUser();

	/*
	 * 
	 * Valid names
	 */
	@Test
	public void testValidName() throws Exception {
		boolean isNameValid = u.isNameValid("Ben Thompson");
		assertThat(isNameValid).isEqualTo(true);
	}
	
	@Test
	public void testValidName2() throws Exception {
		boolean isNameValid = u.isNameValid("Ben Philip-Thompson");
		assertThat(isNameValid).isEqualTo(true);
	}
	
	
	/*
	 * 
	 * Invalid names
	 */
	@Test
	public void testInvalidName() throws Exception {
		boolean isNameValid = u.isNameValid("Ben$ Thompson");
		assertThat(isNameValid).isEqualTo(false);
	}
	
	@Test
	public void testInvalidName2() throws Exception {
		boolean isNameValid = u.isNameValid("Ben Thompson '; EXEC sp_MSForEachTable 'DROP TABLE ?'; --");
		assertThat(isNameValid).isEqualTo(false);
	}
	
	@Test
	public void testInvalidName3() throws Exception {
		boolean isNameValid = u.isNameValid("Ben Thompson 1;DROP TABLE users");
		assertThat(isNameValid).isEqualTo(false);
	}
	
	
	
	/*
	 * 
	 * Valid emails
	 * 
	 */
	@Test
	public void testValidEmail() throws Exception {
		boolean isEmailValid = u.isEmailValid("somecoolemail@yahoo.org");
		System.out.println(isEmailValid);
		assertThat(isEmailValid).isEqualTo(true);
	}
	
	@Test
	public void testValidEmail2() throws Exception {
		boolean isEmailValid = u.isEmailValid("somecool-email@yahoo.org");
		assertThat(isEmailValid).isEqualTo(true);
	}
	
	@Test
	public void testValidEmail3() throws Exception {
		boolean isEmailValid = u.isEmailValid("somecoolemail@some-site.org");
		assertThat(isEmailValid).isEqualTo(true);
	}
	
	
	/*
	 * 
	 * Invalid emails
	 * 
	 */
	@Test
	public void testInvalidEmail() throws Exception {
		boolean isEmailValid = u.isEmailValid("somec$%@oolemail@yahoo.rg");
		assertThat(isEmailValid).isEqualTo(false);
	}
	
	@Test
	public void testInvalidEmail2() throws Exception {		
		boolean isEmailValid = u.isEmailValid("somecoolemail@yaho$o.org");
		assertThat(isEmailValid).isEqualTo(false);
	}
	
	@Test
	public void testInvalidEmail3() throws Exception {		
		boolean isEmailValid = u.isEmailValid("$$$$$$-email@gmail.com");
		assertThat(isEmailValid).isEqualTo(false);
	}
	
	
	
	
	
	
}
