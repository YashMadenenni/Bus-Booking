package cs5031.bus_travel_planner;

import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.web.bind.annotation.RequestBody;
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
		BusModel busModel = new BusModel("src/test/resources/initialState.json");
	
	@Test
	public void testGetLocationsJson() throws Exception {
		mockMvc.perform(get("/locations")).andExpect(status().isOk());
	}

	@Test
	public void testGetSearchJson() throws Exception {
		mockMvc.perform(get("/buses?from=DRA&to=DRA&day=Tuesday&time=11:00")).andExpect(status().isOk());
	}

	@Test
	public void testsearchRouteForStop() throws Exception {
		mockMvc.perform(get("/buses?from=DRA")).andExpect(status().isOk());
	}

	@Test
	public void testsearchBusesForStopReturnRoute() throws Exception {
		mockMvc.perform(get("/buses?from=DRA&day=Tuesday&time=11:00")).andExpect(status().isOk());
	}

	@Test
	public void testsearchBusesOnADayForStop() throws Exception {
		mockMvc.perform(get("/buses?from=DRA&day=Tue")).andExpect(status().isOk());
	}

    @Test
    public void testGetSearchResults() throws Exception {
        mockMvc.perform(get("/buses?from=DRA&to=St.Andrews&day=Tuesday&time=11:00"))
                .andExpect(status().isOk())
                //  .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                 .andExpect(content().string(containsString("")));
    }

    @Test
    public void testGetRoutesForStop() throws Exception {
        mockMvc.perform(get("/buses?from=DRA"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().string(containsString("{\"searchResult\":[]}")));
    }

    @Test
    public void testGetBusesForStopReturnRoute() throws Exception {
        mockMvc.perform(get("/buses?from=DRA&day=Tuesday&time=11:00"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                 .andExpect(content().string(containsString("{\"searchResult\":[]}")));
    }

    @Test
    public void testGetBusesOnADayForStop() throws Exception {
        mockMvc.perform(get("/buses?from=DRA&day=Tue"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                 .andExpect(content().string(containsString("{\"searchResult\":[]}")));
    }


	// valid result
	
	@Test
    public void testAddStop() throws Exception {
        String jsonBody = "{route: \"99 UP\", stopName: \"Yash\", stopLocation: \"KY16 9LY\", timeTable: [{Time: \"Tuesday 18:19\"}]}";

        mockMvc.perform(MockMvcRequestBuilders.post("/buses/addRoute")
                .contentType(MediaType.APPLICATION_JSON)
                .content(jsonBody))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().string("success"));
    }


}
