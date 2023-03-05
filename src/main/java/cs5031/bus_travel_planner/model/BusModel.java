package cs5031.bus_travel_planner;
import java.util.ArrayList;
import java.io.FileNotFoundException;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;


public class BusModel {
    private ArrayList<Route> allRoutes;

    public BusModel(String jsonPath) {
        try {
            loadInitialState(jsonPath); 
        }
        catch (IOException | JSONException e){
        }
    }

    protected static JSONObject loadInitialState(String jsonPath) 
        throws IOException, JSONException {
            return new JSONObject();
        }
}
