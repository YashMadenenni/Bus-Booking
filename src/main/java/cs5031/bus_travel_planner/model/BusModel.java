package cs5031.bus_travel_planner;
import java.util.HashMap;
import java.io.FileNotFoundException;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONArray;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

/**
The BusModel class represents the model in the bus travel planner application.
It reads a JSON file containing data about bus routes and stops, and provides methods to search for
routes based on various criteria, add stops to routes, and return the full index of routes and stops.
*/
public class BusModel {
    private HashMap<String, Route> allRoutes;

    /**
    * The String initialPath stores the path to the JSON file that is used to initialize the model.
    */
    private String initialPath;

    /**
    * Constructor for the BusModel class. Reads the JSON file at the given path and processes it into
    * Route and Stop objects that are stored in the allRoutes HashMap.
    * @param jsonPath The path to the JSON file that contains the initial data for the application.
    */
    public BusModel(String jsonPath) {

        this.initialPath = jsonPath;
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

    /**
    * Searches for all the routes that include the given stop.
    * @param stop The name of the stop to search for.
    * @return A JSON-encoded string containing an array of route names and directions that include the given stop.
    */
    public String getRoutesFromStop(String stop) {

        JSONObject container = new JSONObject();
        JSONArray routeArray = new JSONArray();

        for(Route routeItem : allRoutes.values()) {
            if(null != routeItem.getStopList().get(stop)) {
                routeArray.put(
                        routeItem.getRouteName() + " " + routeItem.getDirection());
            }
        }

        container.put("searchResult", routeArray);

        return container.toString();
    }

    /**
    * Searches for all the routes that include both the given source stop and destination stop.
    * @param srcStop The name of the source stop to search for.
    * @param dstStop The name of the destination stop to search for.
    * @return A JSON-encoded string containing an array of route names and directions that include both the
    * source stop and destination stop.
    */
    public String getRoutesFromToStop(String srcStop, String dstStop) {

        JSONObject container = new JSONObject();
        JSONArray routeArray = new JSONArray();
        

        for(Route routeItem : allRoutes.values()) {
            Stop src = routeItem.getStopList().get(srcStop);
            Stop dst = routeItem.getStopList().get(dstStop);

            
            if(null != src &&
                    null != dst) {

                routeArray.put(
                        routeItem.getRouteName() + " " + routeItem.getDirection());
            }
        }

        container.put("searchResult", routeArray);

        return container.toString();
    }

    /**
    * Searches for all the routes that include the given stop, on the given day and at the given time.
    * @param stop The name of the stop to search for.
    * @param day The day of the week to search for.
    * @param time The time of day to search for.
    * @return A JSON-encoded string containing an array of route names and directions that include the given stop,
    * on the given day and at the given time.
    */
    public String getRoutesFromStop(String stop, String day, String time) {

        JSONObject container = new JSONObject();
        JSONArray routeArray = new JSONArray();

        for(Route routeItem : allRoutes.values()) {
            Stop matchedStop = routeItem.getStopList().get(stop);
            if(null != matchedStop) {

                TimeTable timeTable = matchedStop.getStopTimings().get(day); 
                if(null != timeTable) {
                    if(timeTable.getTime().equals(time)) {
                        routeArray.put(
                                routeItem.getRouteName() + " " + routeItem.getDirection());
                    }
                }
            }
        }

        container.put("searchResult", routeArray);

        return container.toString();
    }

    /**
    * Searches for all the routes that include the given stop, on the given day.
    * @param stop The name of the stop to search for.
    * @param day The day of the week to search for.
    * @return A JSON-encoded string containing an array of route names, directions, and departure times from the
    * given stop on the given day.
    */
    public String getRoutesFromStop(String stop, String day) {

        JSONObject container = new JSONObject();
        JSONArray routeArray = new JSONArray();

        for(Route routeItem : allRoutes.values()) {
            Stop matchedStop = routeItem.getStopList().get(stop);
            if(null != matchedStop) {

                TimeTable timeTable = matchedStop.getStopTimings().get(day); 
                if(null != timeTable) {
                    routeArray.put(
                            routeItem.getRouteName() + " " + 
                            routeItem.getDirection() + " " +
                            timeTable.getDayofWeek() + " " +
                            timeTable.getTime());
                }
            }
        }

        container.put("searchResult", routeArray);

        return container.toString();
    }

    /**
    * Helper method to load the initial state of the application from a JSON file.
    * @param jsonPath The path to the JSON file to load.
    * @return A JSONObject containing the data from the JSON file.
    * @throws IOException if an I/O error occurs while reading the file.
    * @throws JSONException if the JSON data is invalid or cannot be parsed.
    */
    protected static JSONObject loadInitialState(String jsonPath) 
        throws IOException, JSONException {

            String jsonBody = new String(Files.readAllBytes(Paths.get(jsonPath)));
            return new JSONObject(jsonBody);
        }

    /**
    * Helper method to process a JSONObject representing the initial state of the application into Route and
    * Stop objects that are stored in the allRoutes HashMap.
    * @param json A JSONObject containing the initial state of the application.
    * @return A HashMap containing all the Route objects in the application, keyed by a String that represents
    * the route name and direction.
    */
    private HashMap<String, Route> processJsonObject(JSONObject json) {

        HashMap<String, Route> allRoutes = new HashMap<String, Route>();
        
        JSONArray jsonRoutes = json.getJSONArray("routes");

        for(int i = 0; i < jsonRoutes.length(); ++i) {
            JSONObject jObj = jsonRoutes.getJSONObject(i);

            String routeName = jObj.getString("routeName");
            String direction = jObj.getString("direction");

            Route newRoute = new Route(routeName, direction);

            JSONArray jsonStops = jObj.getJSONArray("stopList");

            for(int j = 0; j < jsonStops.length(); ++j) {
                JSONObject stopObj = jsonStops.getJSONObject(j);
                
                Stop stopToAdd = parseStopJson(stopObj);

                newRoute.addStop(stopToAdd);
            }
            
            String name = newRoute.getRouteName() + " " + newRoute.getDirection();
            allRoutes.put(name, newRoute);
        }

        return allRoutes;
    }
    /**
    * Helper method to parse a JSONObject representing a Stop object.
    * @param stopObj A JSONObject representing a Stop object.
    * @return A Stop object containing the data from the input JSONObject.
    */
    private Stop parseStopJson(JSONObject stopObj) {
        Stop stopToAdd = null;

        String stopName = stopObj.getString("stopName");
        String stopLocation = stopObj.getString("stopLocation");

        stopToAdd = new Stop(stopName, stopLocation);

        JSONArray timeTable = stopObj.getJSONArray("timeTable");

        for(int k = 0; k < timeTable.length(); ++k) {
            JSONObject timeObj = timeTable.getJSONObject(k);

            String time = timeObj.getString("Time");
            String[] timeSplit = time.split("\\s+");

            TimeTable newTimeTable = new TimeTable(timeSplit[0], timeSplit[1]);

            stopToAdd.addTiming(newTimeTable);
        }
        return stopToAdd;
    }

    /**
    * Adds a stop to a route specified in a JSON object.
    * @param json A JSONObject containing the name of the route and the data for the new stop.
    */
    public boolean addStopToRoute(JSONObject json) {
        String routeName = json.getString("route");

        Route route = getAllRoutes().get(routeName);
        if(null != route) {
            route.addStop(parseStopJson(json));
            return true;
        }
        return false;
    }

    /**
    * Returns the full index of routes and stops in the application.
    * @return A JSON-encoded string containing all the routes and stops in the application.
    */
    public String getIndex() {
    String output = null;
        try {
            output = (loadInitialState(this.initialPath)).toString();
        }
        catch (IOException | JSONException e){
        }
        return output;
    }

    /**
    * Returns the allRoutes HashMap.
    * @return The HashMap containing all the Route objects in the application, keyed by a String that represents
    * the route name and direction.
    */
    public HashMap<String, Route> getAllRoutes() {
        return allRoutes;
    }
}
