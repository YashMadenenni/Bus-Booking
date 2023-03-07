package cs5031.bus_travel_planner;
import java.util.LinkedHashMap;
import java.util.ArrayList;

/**
Represents a bus stop.
*/
public class Stop {
    private String stopName;
    private String stopLocation;
    private LinkedHashMap<String, TimeTable> stopTimings;

    /**
    * Constructor for a Stop object.
    * @param stopName The name of the stop.
    * @param stopLocation The location of the stop.
    */
    public Stop(String stopName, String stopLocation) {
        this.stopName = stopName;
        this.stopLocation = stopLocation;
        stopTimings = new LinkedHashMap<String, TimeTable>(); 
    }

    /**
    * Adds a TimeTable object to the stop's list of timings.
    * @param time The TimeTable object to add to the stop.
    */
    public void addTiming(TimeTable time) {
        stopTimings.put(time.getDayofWeek(), time);
    }

    /**
    * Returns the name of the stop.
    * @return The name of the stop.
    */
    public String getStopName() {
        return this.stopName;
    }

    /**
    * Returns the location of the stop.
    * @return The location of the stop.
    */
    public String getStopLocation() {
        return stopLocation;
    }

    /**
    * Returns a LinkedHashMap of all the timings for the stop, keyed by day of the week.
    * @return A LinkedHashMap of all the timings for the stop, keyed by day of the week.
    */
    public LinkedHashMap<String, TimeTable> getStopTimings() {
        return stopTimings;
    }
}
