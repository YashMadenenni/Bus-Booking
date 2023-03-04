package cs5031.bus_travel_planner;

import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

@SpringBootTest
@AutoConfigureMockMvc
public class IndexControllerTest {

	@Autowired
	private MockMvc mockMvc;

	// @Test
	// public void shouldReturnDefaultMessage() throws Exception {
	// 	this.mockMvc.perform(get("/")).andDo(print()).andExpect(status().isOk())
	// 			.andExpect(content().string(containsString("Welocme to Bus Travel Planner!")));
	// }

	@Test
	public void testGetLocationsJson() throws Exception {
		mockMvc.perform(get("/locations")).andExpect(status().isOk());
	}

	@Test
	public void testGetSearchJson() throws Exception {
		mockMvc.perform(get("/buses?from=DRA&to=DRA&day=Tuesday&time=11:00")).andExpect(status().isOk());
	}

	@Test
	public void testAddRoute() throws Exception {
		mockMvc.perform(get("/buses?stop=DRA&route=99A")).andExpect(status().isOk());
	}

}
