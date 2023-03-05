package cs5031.bus_travel_planner;
import org.junit.jupiter.api.Test;

import java.io.FileNotFoundException;
import java.io.IOException;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONArray;
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

    //TC1
    @Test
        public void shouldNotOpenJson() {
            NoSuchFileException thrown = 
                assertThrowsExactly(NoSuchFileException.class, () -> {
                        BusModel.loadInitialState("src/test/resources/wrongpath.json");
                        });
        }

    //TC2
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

    //TC3
    @Test
        public void testReadStringJson() throws IOException, JSONException {
            JSONObject result = null;
            try {
                result = BusModel.loadInitialState("src/test/resources/test.json");
            }
            catch (IOException | JSONException e) {
            }
            assertNotNull(result);
            
            String name = result.getString("name");
            assertEquals("test", name);
        }

    //TC4
    @Test
        public void testReadArraySizeJson() throws IOException, JSONException {
            JSONObject result = null;
            try {
                result = BusModel.loadInitialState("src/test/resources/test.json");
            }
            catch (IOException | JSONException e) {
            }
            assertNotNull(result);
            
            JSONArray array = result.getJSONArray("array");
            assertEquals(3, array.length());
        }

    //TC5
    @Test
        public void testReadArrayValuesJson() throws IOException, JSONException {
            JSONObject result = null;
            try {
                result = BusModel.loadInitialState("src/test/resources/test.json");
            }
            catch (IOException | JSONException e) {
            }
            assertNotNull(result);
            
            JSONArray array = result.getJSONArray("array");
            for(int i = 0; i < array.length(); ++i) {
                assertEquals(i+1, array.getJSONObject(i).getInt("num"));
            }
        }


}
