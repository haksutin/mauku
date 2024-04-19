package com.example.mauku;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.context.WebApplicationContext;

import com.example.mauku.domain.AppUserRepository;
import com.example.mauku.domain.SignupForm;
import com.example.mauku.web.CatController;
import com.example.mauku.web.UserController;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
class MaukuApplicationTests {

	@LocalServerPort
    private int port;

	@Autowired
	private CatController catController;

	@Autowired
	private UserController userController;

	@Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    private AppUserRepository userRepository;

	@Autowired
    private MockMvc mockMvc;

    @Autowired
    private WebApplicationContext context;

	@Test
	void contextLoads() throws Exception {
		assertThat(catController).isNotNull();
		assertThat(userController).isNotNull();
	}

	@Test
    void testRegisterNewUser() throws Exception {
        SignupForm signupForm = new SignupForm();
        signupForm.setUsername("testuser");
        signupForm.setPassword("testpassword");
        signupForm.setPasswordCheck("testpassword");

        ResponseEntity<String> responseEntity = restTemplate.postForEntity("http://localhost:" + port + "/saveuser",
                signupForm, String.class);

        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.FOUND);
        assertThat(responseEntity.getHeaders().getLocation().toString()).isEqualTo("http://localhost:" + port + "/login");

        // Verify that the user is saved in the database
        assertThat(userRepository.findByUsername("testuser")).isNotNull();
    }

}
