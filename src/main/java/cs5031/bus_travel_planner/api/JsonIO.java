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

    public static void addStopJson(JSONObject stop, JSONObject json) {

JSONArray successObject=new JSONArray();
successObject.put(stop.toString());

        System.out.println(successObject.toString());



        String routeName[] = stop.getString("route").split("\\s+");
        JSONObject stopToAdd = (JSONObject)stop.remove("route");
        JSONArray jsonRoutes = json.getJSONArray("routes");

        for(int i = 0; i < jsonRoutes.length(); ++i) {
            JSONObject jObj = jsonRoutes.getJSONObject(i);


            if(routeName[0].equals(jObj.getString("routeName"))) {
                JSONArray jsonStops = jObj.getJSONArray("stopList");
                //jsonStops.put("{}");
        System.out.println(jsonStops.toString());
            }
        }

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
