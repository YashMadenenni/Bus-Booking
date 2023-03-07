package cs5031.bus_travel_planner;
import java.util.HashMap;
import java.io.FileNotFoundException;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONArray;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;


public class BusModel {
    private HashMap<String, Route> allRoutes;
    private String initialPath;

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

    public String getRoutesFromToStop(String srcStop, String dstStop) {

        JSONObject container = new JSONObject();
        JSONArray routeArray = new JSONArray();
        
        System.out.println("FIRST POINT "+ srcStop + dstStop);

        for(Route routeItem : allRoutes.values()) {
            Stop src = routeItem.getStopList().get(srcStop);
            Stop dst = routeItem.getStopList().get(dstStop);

            
            if(null != src &&
                    null != dst) {
            System.out.println("ROUTE " + routeItem.getRouteName() + routeItem.getDirection());
                System.out.println(src.getStopName());
                System.out.println(dst.getStopName());
                System.out.println();

                routeArray.put(
                        routeItem.getRouteName() + " " + routeItem.getDirection());
            }
        }

        container.put("searchResult", routeArray);

        return container.toString();
    }

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

    protected static JSONObject loadInitialState(String jsonPath) 
        throws IOException, JSONException {

            String jsonBody = new String(Files.readAllBytes(Paths.get(jsonPath)));
            return new JSONObject(jsonBody);
        }

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

    public void addStopToRoute(JSONObject json) {
        String routeName = json.getString("route");
            for(Route routeItem : allRoutes.values()) {
            }
        Route route = getAllRoutes().get(routeName);
        if(null != route) {
            route.addStop(parseStopJson(json));
            //writeToJson(route);
        }
    }

    public String getIndex() {
    String output = null;
        try {
            output = (loadInitialState(this.initialPath)).toString();
        }
        catch (IOException | JSONException e){
        }
        return output;
    }

/*
    private void writeToJson() {
        
       JSONObject obj = null;
       JSONArray routeArray = null;

        obj.put();        
    }
    */

    public HashMap<String, Route> getAllRoutes() {
        return allRoutes;
    }
}
