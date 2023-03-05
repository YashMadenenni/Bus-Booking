package cs5031.bus_travel_planner;
import java.util.LinkedHashMap;
import java.util.ArrayList;

public class Stop {
    private String stopName;
    private String stopLocation;
    private ArrayList<TimeTable> stopTimings;

    public Stop(String stopName, String stopLocation) {
        this.stopName = stopName;
        this.stopLocation = stopLocation;
        stopTimings = new ArrayList<TimeTable>();
    }

    public void addTiming(TimeTable time) {
        stopTimings.add(time);
    }

    public String getStopName() {
        return this.stopName;
    }

    public String getStopLocation() {
        return stopLocation;
    }

    public ArrayList<TimeTable> getStopTimings() {
        return stopTimings;
    }
}
