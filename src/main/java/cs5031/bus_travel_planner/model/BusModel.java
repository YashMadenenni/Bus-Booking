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

        JSONObject json = null;
        try {
            json = loadInitialState(jsonPath); 
        }
        catch (IOException | JSONException e){
        }

        if(null != json) {
            allRoutes = processJsonObject(json);       
        }
    }

    protected static JSONObject loadInitialState(String jsonPath) 
        throws IOException, JSONException {

            String jsonBody = new String(Files.readAllBytes(Paths.get(jsonPath)));
            return new JSONObject(jsonBody);
        }

    protected ArrayList<Route> processJsonObject(JSONObject json) {
        ArrayList<Route> allRoutes = new ArrayList<Route>();
        return allRoutes;
    }

    public ArrayList<Route> getAllRoutes() {
        return allRoutes;
    }
}
