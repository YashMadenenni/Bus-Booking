package cs5031.bus_travel_planner;
import java.util.LinkedHashMap;
import org.json.JSONObject;
import org.json.JSONArray;

public class Route {
    private String routeName;
    private LinkedHashMap<String, Stop> stopList;
    private String direction;

    public Route(String routeName, String direction) {
            
            this.routeName = routeName;
            this.direction = direction;
            stopList = new LinkedHashMap<String, Stop>();
    }

    public void addStop(Stop stopToAdd) {
        stopList.put(stopToAdd.getStopName(), stopToAdd);
    }

    public String getRouteName() {
        return routeName;
    }
    public LinkedHashMap<String, Stop> getStopList() {
        return stopList;
    }
    public String getDirection() {
        return direction;
    }
}
