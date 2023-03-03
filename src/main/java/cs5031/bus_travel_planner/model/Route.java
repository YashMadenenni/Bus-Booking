package cs5031.bus_travel_planner;

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

    public getRouteName() {
        return routeName;
    }
    public getStopList() {
        return stopList;
    }
    public getRouteStart() {
        return routeStart;
    }
    public getRouteEnd() {
        return routeEnd;
    }
    public getDirection() {
        return direction;
    }


}
