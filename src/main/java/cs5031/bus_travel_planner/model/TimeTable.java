package cs5031.bus_travel_planner;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
Represents a timetable for a bus stop.
*/
public class TimeTable {
    private String dayofWeek;
    private String time;

    /**
    * Constructor for a TimeTable object.
    * @param dayofWeek The day of the week the timetable is for.
    * @param time The time specified in the timetable.
    */
    public TimeTable(String dayofWeek, String time) {
        this.dayofWeek = dayofWeek;
        this.time = time;
    }

    /**
    * Returns the day of the week the timetable is for.
    * @return The day of the week the timetable is for.
    */
    public String getDayofWeek() {
        return dayofWeek;
    }

    /**
    * Returns the time specified in the timetable.
    * @return The time specified in the timetable.
    */
    public String getTime() {
        return time;
    }
}
