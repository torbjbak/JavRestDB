package fullstack.oving2;

import org.junit.After;
import org.junit.Before;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.hateoas.MediaTypes;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.greaterThanOrEqualTo;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.MOCK;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = MOCK, classes = Application.class)
@AutoConfigureMockMvc
class AuthorControllerTests {
	@Autowired
	private MockMvc mockMvc;
	Logger logger = LoggerFactory.getLogger(AuthorControllerTests.class);


	@Before
	public void beforeTests() {
		logger.debug("Initializing endpoint tests for AuthorController!");
		/*Address address = new Address(1234, "Testbyen", "Testgata 1");
		Author author = new Author("Test", "Testson", address, null);*/
	}

	@After
	public void afterTests() {
		logger.debug("Tests for AuthorController finished!");
	}

	@BeforeEach
	void beforeEveryTest() {

	}

	@Test
	void contextLoads() {
		try {
			mockMvc.perform(get("/authors")
					.contentType(MediaTypes.HAL_JSON))
					.andExpect(status().isOk())
					.andExpect(content().contentTypeCompatibleWith(MediaTypes.HAL_JSON))
					.andExpect(jsonPath("$._embedded.authorList", hasSize(greaterThanOrEqualTo(1))))
					.andExpect(jsonPath("$._embedded.authorList[0].persName", is("Ola")));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
