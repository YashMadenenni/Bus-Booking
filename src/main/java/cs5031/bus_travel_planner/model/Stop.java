package cs5031.bus_travel_planner;
import java.util.LinkedHashMap;
import java.util.ArrayList;

public class Stop {
    private String stopName;
    private String stopLocation;
    private LinkedHashMap<String, TimeTable> stopTimings;

    public Stop(String stopName, String stopLocation) {
        this.stopName = stopName;
        this.stopLocation = stopLocation;
        stopTimings = new LinkedHashMap<String, TimeTable>(); 
    }

    public void addTiming(TimeTable time) {
        stopTimings.put(time.getDayofWeek(), time);
    }

    public String getStopName() {
        return this.stopName;
    }

    public String getStopLocation() {
        return stopLocation;
    }

    public LinkedHashMap<String, TimeTable> getStopTimings() {
        return stopTimings;
    }
}
