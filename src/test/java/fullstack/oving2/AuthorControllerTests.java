package fullstack.oving2;

import fullstack.oving2.model.Address;
import fullstack.oving2.model.Author;
import org.junit.Before;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.*;
import static org.mockito.Mockito.when;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.MOCK;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = MOCK, classes = Application.class)
@AutoConfigureMockMvc
class AuthorControllerTests {
	@Autowired
	private MockMvc mockMvc;


	@Before
	public void beforeTests() {
		Address address = new Address(1234, "Testbyen", "Testgata 1");
		Author author = new Author("Test", "Testson", address, null);


	}

	@BeforeEach
	void beforeEveryTest() {

	}

	@Test
	void contextLoads() {
		try {
			mockMvc.perform(get("/").contentType(MediaType.APPLICATION_JSON))
					.andExpect(status().isOk())
					.andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
					.andExpect(jsonPath("$", hasSize(greaterThanOrEqualTo(1))))
					.andExpect(jsonPath("$[0].pic", is("Spongebob")));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
