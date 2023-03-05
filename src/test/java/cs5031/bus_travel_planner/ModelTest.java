package cs5031.bus_travel_planner;
import org.junit.jupiter.api.Test;

import java.io.FileNotFoundException;
import java.io.IOException;
import org.json.JSONException;
import org.json.JSONObject;
import java.nio.file.NoSuchFileException;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;

public class ModelTest {
    private BusModel testModel;

    @BeforeEach
        public void setup() {
            testModel = new BusModel("src/test/resources/initialState.json");
        }

    @Test
        public void shouldNotOpenJson() {
            NoSuchFileException thrown = 
                assertThrowsExactly(NoSuchFileException.class, () -> {
                        BusModel.loadInitialState("src/test/resources/wrongpath.json");
                        });
        }

    @Test
        public void shouldOpenJson() throws IOException, JSONException {
            JSONObject result = null;
            try {
                result = BusModel.loadInitialState("src/test/resources/test.json");
            }
            catch (IOException | JSONException e) {
            }

            assertNotNull(result);
        }
}
