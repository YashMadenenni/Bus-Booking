package cs5031.bus_travel_planner;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;


public class TimeTable {
    private String dayofWeek;
    private LocalDateTime time;
    private int routeId;

    public String getDayofWeek() {
        return dayofWeek;
    }
    public LocalDateTime getTime() {
        return time;
    }
    public int getRouteId() {
        return routeId;
    }
}
