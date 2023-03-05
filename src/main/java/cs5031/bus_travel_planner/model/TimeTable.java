package cs5031.bus_travel_planner;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;


public class TimeTable {
    private String dayofWeek;
    private String time;

    public TimeTable(String dayofWeek, String time) {
        this.dayofWeek = dayofWeek;
        this.time = time;
    }

    public String getDayofWeek() {
        return dayofWeek;
    }
    public String getTime() {
        return time;
    }
}
