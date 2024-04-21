package com.example.mauku;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.mauku.web.CatController;
import com.example.mauku.web.UserController;

@SpringBootTest
class MaukuApplicationTests {

	@Autowired
	private CatController catController;

	@Autowired
	private UserController userController;

	@Test
	void contextLoads() throws Exception {
		assertThat(catController).isNotNull();
		assertThat(userController).isNotNull();
	}


}
