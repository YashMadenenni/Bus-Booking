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
    private Route firstRoute;
    private Stop firstStop;

    @BeforeEach
        public void setup() {
            testModel = new BusModel("src/test/resources/initialState.json");
            firstRoute = testModel.getAllRoutes().get("99 UP");
            firstStop = firstRoute.getStopList().get("St. Andrews Main Stop");
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

    //TC6
    @Test
        public void testJsonToRouteConversionSizeCheck() {
            assertEquals(2,testModel.getAllRoutes().size());
        }

    //TC7
    @Test
        public void testJsonToRouteConversionCheckFirstRouteName() {
            assertEquals("99", firstRoute.getRouteName());
        }

    //TC8
    @Test
        public void testJsonToRouteConversionCheckFirstRouteDirection() {
            assertEquals("UP", firstRoute.getDirection());
        }

    //TC9
    @Test
        public void testJsonToRouteConversionCheckFirstRouteStopSize() {
            assertNotEquals(0, firstRoute.getStopList().size());
        }

    //TC10
    @Test
        public void testJsonToRouteConversionCheckFirstRouteFirstStopName() {
            assertEquals("St. Andrews Main Stop", firstStop.getStopName()); 
        }

    //TC11
    @Test
        public void testJsonToRouteConversionCheckFirstRouteFirstStopLocation() {
            assertEquals("KY16 9UX", firstStop.getStopLocation()); 
        }

    //TC12
    @Test
        public void testJsonToRouteConversionCheckFirstRouteFirstStopTimeTableSize() {
            assertEquals(7, firstStop.getStopTimings().size()); 
        }

    //TC13
    @Test
        public void testJsonToRouteConversionCheckFirstRouteFirstStopMonTime() {
            assertEquals("12:00", firstStop.getStopTimings().get("Monday").getTime());
        }
    //TC14
    @Test
        public void testJsonToRouteConversionCheckFirstRouteFirstStopTuesTime() {
            assertEquals("12:05", firstStop.getStopTimings().get("Tuesday").getTime());
        }
    //TC15
    @Test
        public void testJsonToRouteConversionCheckFirstRouteFirstStopWedTime() {
            assertEquals("12:10", firstStop.getStopTimings().get("Wednesday").getTime());
        }
    //TC16
    @Test
        public void testJsonToRouteConversionCheckFirstRouteFirstStopThuTime() {
            assertEquals("13:00", firstStop.getStopTimings().get("Thursday").getTime());
        }
    //TC17
    @Test
        public void testJsonToRouteConversionCheckFirstRouteFirstStopFriTime() {
            assertEquals("14:00", firstStop.getStopTimings().get("Friday").getTime());
        }
    //TC18
    @Test
        public void testJsonToRouteConversionCheckFirstRouteFirstStopSatTime() {
            assertEquals("15:00", firstStop.getStopTimings().get("Saturday").getTime());
        }
    //TC19
    @Test
        public void testJsonToRouteConversionCheckFirstRouteFirstStopSunTime() {
            assertEquals("16:00", firstStop.getStopTimings().get("Sunday").getTime());
        }
    //TC20
    @Test
        public void testResultRouteNameSearchRoutesForGivenStop() {
            String result = testModel.getRoutesFromStop("Market Street");
            assertTrue(result.contains("99 UP"));
        }
    //TC21
    @Test
        public void testResultRouteNameSecondSearchRoutesForGivenStop() {
            String result = testModel.getRoutesFromStop("Market Street");
            assertTrue(result.contains("99 DOWN"));
        }
    //TC22
    @Test
        public void testResultRouteNameNegativeSearchRoutesForGivenStop() {
            String result = testModel.getRoutesFromStop("Market Street");
            assertFalse(result.contains("99 DOWn"));
        }
    //TC23
    @Test
        public void testResultRouteNameNegativeSecondSearchRoutesForGivenStop() {
            String result = testModel.getRoutesFromStop("Market Street");
            assertFalse(result.contains("99 up"));
        }
    //TC24
    @Test
        public void testResultRouteNameSearchRoutesForGivenDayAndStop() {
            String result = testModel.getRoutesFromStop("Market Street", "Monday");
            assertTrue(result.contains("99 UP"));
        }
    //TC25
    @Test
        public void testResultRouteNameSecondSearchRoutesForGivenDayAndStop() {
            String result = testModel.getRoutesFromStop("Market Street", "Monday");
            assertTrue(result.contains("99 DOWN"));
        }
    //TC26
    @Test
        public void testResultRouteTimeSearchRoutesForGivenDayAndStop() {
            String result = testModel.getRoutesFromStop("Market Street", "Monday");
            assertTrue(result.contains("12:05"));
        }
    //TC27
    @Test
        public void testResultRouteTimeSecondSearchRoutesForGivenDayAndStop() {
            String result = testModel.getRoutesFromStop("Market Street", "Monday");
            assertTrue(result.contains("12:00"));
        }
    //TC28
    @Test
        public void testResultRouteTimeNegativeSearchRoutesForGivenDayAndStop() {
            String result = testModel.getRoutesFromStop("Market Street", "Monday");
            assertFalse(result.contains("12:10"));
        }
    //TC29
    @Test
        public void testResultRouteTimeSecondNegativeSearchRoutesForGivenDayAndStop() {
            String result = testModel.getRoutesFromStop("Market Street", "Monday");
            assertFalse(result.contains("12:20"));
        }
    //TC30
    @Test
        public void testResultRouteNameNegativeSearchRoutesForGivenDayAndStop() {
            String result = testModel.getRoutesFromStop("Market Street", "Monday");
            assertTrue(result.contains("12:00"));
        }
    //TC31
    @Test
        public void testResultRouteNameNegativeSecondSearchRoutesForGivenDayAndStop() {
            String result = testModel.getRoutesFromStop("Market Street", "Monday");
            assertTrue(result.contains("12:00"));
        }
    //TC32
    @Test
        public void testResultRouteNameSearchRoutesForGivenDayTimeAndStop() {
            String result = testModel.getRoutesFromStop("Market Street", "Monday", "12:00");
            assertTrue(result.contains("99 DOWN"));
        }
    //TC33
    @Test
        public void testResultRouteNameSecondSearchRoutesForGivenDayTimeAndStop() {
            String result = testModel.getRoutesFromStop("Market Street", "Monday", "12:05");
            assertTrue(result.contains("99 UP"));
        }
}
