package com.springdatabase;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import static org.assertj.core.api.Assertions.assertThat;


@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class TestController {

	@LocalServerPort
	private int port;

	@Autowired
	private TestRestTemplate restTemplate;

	@Test
	public void getUserByName() throws Exception {
		assertThat(this.restTemplate.getForObject("http://localhost:" + port + "/api/v1/getUsers?name=Phil",
				String.class)).contains("philemail123@gmail.com");
	}
	
	@Test
	public void getUserByID() throws Exception {
		assertThat(this.restTemplate.getForObject("http://localhost:" + port + "api/v1/getID?id=7",
				String.class)).contains("7");
	}
	
	@Test
	public void getAllUsers() throws Exception {
		assertThat(this.restTemplate.getForObject("http://localhost:" + port + "api/v1/all",
				String.class)).contains("name");
	}

	@Test
	public void addNewUserToDatabase() throws Exception{
		
		MultiValueMap<String, Object> parts = new LinkedMultiValueMap<String, Object>();
		parts.add("name", "John Smith");
		parts.add("email", "jsmith12356@gmail.com");
		
	assertThat(this.restTemplate.postForEntity("http://localhost:" + port + "api/v1/add",
			parts,  String.class)).asString().contains("Added user to the database");	
	}
	
	
	@Test
	public void isInputValid() throws Exception{
		
		MultiValueMap<String, Object> parts = new LinkedMultiValueMap<String, Object>();
		parts.add("name", "Test->/?"); //invalid name
		parts.add("email", "testemail123@gma$il.com"); //invalid email 
		
	assertThat(this.restTemplate.postForEntity("http://localhost:" + port + "api/v1/add",
			parts,  String.class)).asString().contains("invalid name or email");	
	}
	


	
	
}
