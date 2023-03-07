package cs5031.bus_travel_planner;
import org.json.JSONObject;
import org.json.JSONArray;
import org.json.JSONException;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.io.File;

public class JsonIO {

    protected static final String initialFilePath =
        "src/main/resources/static/initialState.json";

    public static void addStopJson(JSONObject stop, JSONObject json, String request) {

        String splitData[] = request.split(",", 2);
        String stopToAdd = "{" + splitData[1];
        JSONObject jsonObject = null;
        try {
            jsonObject = new JSONObject(stopToAdd);
        }catch (JSONException err){
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
            System.out.println("Deleted the file: " + myObj.getName());
        }

        try (PrintWriter out = new PrintWriter(new FileWriter(initialFilePath))) {
            out.write(json.toString());
            out.close();
        } catch (Exception e) {} 



        //System.out.println(json.toString());
    }

    public static JSONObject convertStringToJson(String jsonString) {
        JSONObject jsonObject = null;
        try {
            jsonObject = new JSONObject(jsonString);
        }catch (JSONException err){
        }
        return jsonObject; 
    }
}
