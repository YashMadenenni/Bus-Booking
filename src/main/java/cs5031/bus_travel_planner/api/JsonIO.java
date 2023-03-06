package cs5031.bus_travel_planner;
import org.json.JSONObject;
import org.json.JSONArray;
import java.io.FileWriter;
import java.io.PrintWriter;

public class JsonIO {
    private static final String responseFilePath = 
    "src/main/resources/static/buses.json"; 
    private static final String initialFilePath =
    "src/main/resources/static/initialState.json"; 

    public static void convertStringToJson(String jsonString) {
        try (PrintWriter out = new PrintWriter(new FileWriter(responseFilePath))) {
            out.write(jsonString);
        } catch (Exception e) {} 
    }
}
