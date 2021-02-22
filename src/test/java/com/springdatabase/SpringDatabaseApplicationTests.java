package com.springdatabase;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.springdatabase.controllers.MainController;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class SpringDatabaseApplicationTests {


	@Autowired
	private MainController controller;

	@Test
	public void contextLoads() throws Exception {
		assertThat(controller).isNotNull();
	}
}
