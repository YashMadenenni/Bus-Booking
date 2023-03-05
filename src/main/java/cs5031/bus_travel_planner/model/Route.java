package cs5031.bus_travel_planner;
import java.util.LinkedHashMap;

public class Route {
    private int routeId;
    private String routeName;
    private LinkedHashMap<String, Stop> stopList;
    private Stop routeStart;
    private Stop routeEnd;
    private String direction;

    public Route(String routeName, Stop routeStart, 
            Stop routeEnd, String direction) {
            
            this.routeName = routeName;
            this.routeStart = routeStart;
            this.routeEnd = routeEnd;
            this.direction = direction;
            this.stopList.put(routeStart.getStopName(), routeStart);
            this.stopList.put(routeEnd.getStopName(), routeEnd);
    }

    public String getRouteName() {
        return routeName;
    }
    public LinkedHashMap<String, Stop> getStopList() {
        return stopList;
    }
    public Stop getRouteStart() {
        return routeStart;
    }
    public Stop getRouteEnd() {
        return routeEnd;
    }
    public String getDirection() {
        return direction;
    }
}
