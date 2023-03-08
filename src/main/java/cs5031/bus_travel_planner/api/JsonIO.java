package cs5031.bus_travel_planner;
import org.json.JSONObject;
import org.json.JSONArray;
import org.json.JSONException;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.io.File;

/**
The JsonIO class provides static methods for reading and writing JSON data to files and converting
JSON strings to JSON objects.
*/
public class JsonIO {

    /**
     * The initialFilePath constant holds the path to the initial state JSON file.
     */
    protected static final String initialFilePath =
        "src/main/resources/static/initialState.json";

    /**
     * The addStopJson method adds a stop to the JSON data.
     *
     * @param stop     the JSONObject representing the stop to add
     * @param json     the JSONObject containing the existing JSON data
     * @param request  a String representing the request to add the stop
     *
     * @return true if the stop was successfully added, false otherwise
     */
    public static boolean addStopJson(JSONObject stop, JSONObject json, String request) {

        String stopToAdd = null; 
        JSONObject jsonObject = null;
        String splitData[] = request.split(",", 2);

        try {
            stopToAdd = "{" + splitData[1];
        }
        catch (ArrayIndexOutOfBoundsException e) {
            return false;
        }

        try {
            jsonObject = new JSONObject(stopToAdd);
        }
        catch (JSONException err){
            return false;
        }

        String routeName[] = stop.getString("route").split("\\s+");
        JSONArray jsonRoutes = json.getJSONArray("routes");

        for(int i = 0; i < jsonRoutes.length(); ++i) {
            JSONObject jObj = jsonRoutes.getJSONObject(i);


            if(routeName[0].equals(jObj.getString("routeName")) &&
                    routeName[1].equals(jObj.getString("direction"))) {
                JSONArray jsonStops = jObj.getJSONArray("stopList");
                jsonStops.put(jsonObject);
            }
        }

        File myObj = new File(initialFilePath); 
        if (myObj.delete()) { 
            System.out.println("UPDATED JSON: " + myObj.getName());
        }

        try (PrintWriter out = new PrintWriter(new FileWriter(initialFilePath))) {
            out.write(json.toString());
            out.close();
        } catch (Exception e) {
            return false;
        } 
        return true;
    }

    /**
      Converts a JSON string to a JSONObject.
      @param jsonString the JSON string to be converted
      @return the JSONObject that represents the JSON string, or null if the conversion failed
     */
    public static JSONObject convertStringToJson(String jsonString) {
        JSONObject jsonObject = null;

        try {
            jsonObject = new JSONObject(jsonString);
        }catch (JSONException err){
            return null;
        }
        return jsonObject; 
    }
}
