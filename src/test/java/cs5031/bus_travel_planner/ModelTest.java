package cs5031.bus_travel_planner;
import org.junit.jupiter.api.Test;

import java.io.FileNotFoundException;
import java.io.IOException;
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
        public void shouldNotOpenJson() throws IOException {
            IOException thrown = 
                assertThrowsExactly(IOException.class, () -> {
                        BusModel.loadInitialState("src/test/resources/wrongpath.json");
                        });
        }
}
