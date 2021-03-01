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
		assertThat(
				this.restTemplate.getForObject("http://localhost:" + port + "/api/v1/getUsers?name=Phil", String.class))
						.contains("philemail123@gmail.com");
	}

	@Test
	public void getUserByID() throws Exception {
		assertThat(this.restTemplate.getForObject("http://localhost:" + port + "api/v1/getID?id=7", String.class))
				.contains("7");
	}

	@Test
	public void getAllUsers() throws Exception {
		assertThat(this.restTemplate.getForObject("http://localhost:" + port + "api/v1/all", String.class))
				.contains("name");
	}

	@Test
	public void addNewUserToDatabase() throws Exception {

		MultiValueMap<String, Object> parts = new LinkedMultiValueMap<String, Object>();
		parts.add("name", "John Smith");
		parts.add("email", "jsmith12356@gmail.com");

		assertThat(this.restTemplate.postForEntity("http://localhost:" + port + "api/v1/add", parts, String.class))
				.asString().contains("Added user to the database");
	}

	
	//various inputs that could break everything
	
	@Test
	public void invalidInput() throws Exception {

		MultiValueMap<String, Object> parts = new LinkedMultiValueMap<String, Object>();
		parts.add("name", "Test Name 😍😍😍😍😍😍"); // invalid name
		parts.add("email", "testemail123@gma$il.com😍😍😍😍"); // invalid email
		assertThat(this.restTemplate.postForEntity("http://localhost:" + port + "api/v1/add", parts, String.class))
				.asString().contains("invalid name or email");
	}
	
	@Test
	public void invalidInput2() throws Exception {

		MultiValueMap<String, Object> parts = new LinkedMultiValueMap<String, Object>();
		parts.add("name", "𝓣𝓱𝓮 𝓺𝓾𝓲𝓬𝓴 𝓫𝓻𝓸𝔀𝓷 𝓯𝓸𝔁 𝓳𝓾𝓶𝓹𝓼 𝓸𝓿𝓮𝓻 𝓽𝓱𝓮 𝓵𝓪𝔃𝔂 𝓭𝓸𝓰"); // invalid name
		parts.add("email", "testemail123@gmail.com"); // invalid email
		assertThat(this.restTemplate.postForEntity("http://localhost:" + port + "api/v1/add", parts, String.class))
				.asString().contains("invalid name or email");
	}

	@Test
	public void invalidInput3() throws Exception {

		MultiValueMap<String, Object> parts = new LinkedMultiValueMap<String, Object>();
		parts.add("name", "<script\\x20type=\\\"text/javascript\\\">javascript:alert(1);</script>"); // invalid name
		parts.add("email", "totallylegitinput@gmail.com"); // invalid email
		assertThat(this.restTemplate.postForEntity("http://localhost:" + port + "api/v1/add", parts, String.class))
				.asString().contains("invalid name or email");
	}
	
	@Test
	public void invalidInput4() throws Exception {

		MultiValueMap<String, Object> parts = new LinkedMultiValueMap<String, Object>();
		parts.add("name", "<Roses are \u001b[0;31mred\u001b[0m, violets are \u001b[0;34mblue. Hope you enjoy terminal hue"); // invalid name
		parts.add("email", "totallylegitinput@gmail.com"); // invalid email
		assertThat(this.restTemplate.postForEntity("http://localhost:" + port + "api/v1/add", parts, String.class))
				.asString().contains("invalid name or email");
	}
	
	@Test
	public void invalidInput5() throws Exception {

		MultiValueMap<String, Object> parts = new LinkedMultiValueMap<String, Object>();
		parts.add("name", "1'; DROP TABLE users-- 1"); // invalid name
		parts.add("email", "totallylegitinput@gmail.com"); // invalid email
		assertThat(this.restTemplate.postForEntity("http://localhost:" + port + "api/v1/add", parts, String.class))
				.asString().contains("invalid name or email");
	}

}
