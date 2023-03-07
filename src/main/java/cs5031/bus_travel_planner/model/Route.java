package cs5031.bus_travel_planner;
import java.util.LinkedHashMap;
import org.json.JSONObject;
import org.json.JSONArray;

/**
Represents a bus route.
*/
public class Route {
    private String routeName;
    private LinkedHashMap<String, Stop> stopList;
    private String direction;

    /**
    * Constructor for a Route object.
    * @param routeName The name of the route.
    * @param direction The direction the route is traveling in.
    */
    public Route(String routeName, String direction) {
            
            this.routeName = routeName;
            this.direction = direction;
            stopList = new LinkedHashMap<String, Stop>();
    }

    /**
    * Adds a Stop object to the route's list of stops.
    * @param stopToAdd The Stop object to add to the route.
    */
    public void addStop(Stop stopToAdd) {
        stopList.put(stopToAdd.getStopName(), stopToAdd);
    }

    /**
    * Returns the name of the route.
    * @return The name of the route.
    */
    public String getRouteName() {
        return routeName;
    }

    /**
    * Returns a LinkedHashMap of all the stops on the route, keyed by stop name.
    * @return A LinkedHashMap of all the stops on the route, keyed by stop name.
    */
    public LinkedHashMap<String, Stop> getStopList() {
        return stopList;
    }

    /**
    * Returns the direction the route is traveling in.
    * @return The direction the route is traveling in.
    */
    public String getDirection() {
        return direction;
    }
}
