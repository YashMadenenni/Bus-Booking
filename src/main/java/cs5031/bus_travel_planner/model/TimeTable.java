package cs5031.bus_travel_planner;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;


public class TimeTable {
    private String dayofWeek;
    private LocalDateTime time;

    public TimeTable(String dayofWeek, LocalDateTime time) {
        this.dayofWeek = dayofWeek;
        this.time = time;
    }

    public String getDayofWeek() {
        return dayofWeek;
    }
    public LocalDateTime getTime() {
        return time;
    }
}
