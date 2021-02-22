package com.springdatabase;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;

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
	
	
}
