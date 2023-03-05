package cs5031.bus_travel_planner;
import java.util.ArrayList;

public class BusModel {
    private ArrayList<Route> allRoutes;

    public BusModel() {
        if(!loadInitialState("initialState.json")) {
        }
    }

    public boolean loadInitialState(String jsonPath) {
        return false;
    }
}
