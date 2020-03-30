package seedu.event;

import seedu.attendance.Attendance;
import seedu.attendance.AttendanceList;
import seedu.exception.DukeException;
import seedu.performance.Performance;
import seedu.performance.PerformanceList;

import java.time.Instant;

public class Event {
    protected String name;
    protected DateTime datetime;
    protected String venue;
    protected AttendanceList attendanceList;
    protected PerformanceList performanceList;

    /**
     * Constructor with name, datetime, venue provided.
     * @param name name of event
     * @param datetime datetime of event
     * @param venue venue of event
     */
    public Event(String name, String datetime, String venue) throws DukeException {
        setName(name);
        setDatetime(datetime);
        setVenue(venue);
        this.attendanceList = new AttendanceList();
        this.performanceList = new PerformanceList();
    }

    /**
     * Returns the name of the event.
     * @return the name of the event
     */
    public String getName() {
        return this.name;
    }

    /**
     * Sets the name of the event. If the specified name is empty or {@code null},
     * and the original name is also empty or {@code null},
     * the name will take the form: event_(secondsSinceEpoch)
     * @param name the new name for the event
     * @throws DukeException when trying to overwrite a non-empty
     *      and non-null name with an empty or null name
     */
    public void setName(String name) throws DukeException {
        if (this.name == null || this.name.isEmpty()) {
            // if original name is empty or null
            if (name == null || name.isEmpty()) {
                // if new name is empty or null
                this.name = "event_" + Instant.now().getEpochSecond();
            } else {
                this.name = name;
            }
        } else {
            // if original name is not empty and null
            if (name == null || name.isEmpty()) {
                // if new name is empty or null
                throw new DukeException("Empty name");
            } else {
                // if new name is not empty and not null
                this.name = name;
            }
        }
    }

    /**
     * Returns the datetime of the event.
     * @return the datetime of the event
     */
    public String getDatetime() {
        return datetime.toString();
    }

    /**
     * Returns month of the event as an integer value, from 1 to 12.
     * @return Numerical value of month of the event.
     */
    public Integer getMonth() {
        return datetime.getMonth();
    }

    /**
     * Returns year of the event as an integer value.
     * @return Numerical value of the year of the event.
     */
    public Integer getYear() {
        return datetime.getYear();
    }

    public int getDay() {
        return datetime.getDate();
    }


    /**
     * Sets the datetime of the event.
     * @param datetime the new datetime for the event
     */
    public void setDatetime(String datetime) {
        this.datetime = new DateTime(datetime);
    }

    /**
     * Returns the venue of the event.
     * @return the venue of the event
     */
    public String getVenue() {
        return this.venue;
    }

    /**
     * Sets the venue of the event.
     * @param venue the new venue for the event
     */
    public void setVenue(String venue) {
        this.venue = venue;
    }

    public AttendanceList getAttendanceList() {
        return attendanceList;
    }

    public void setAttendanceList(AttendanceList attendanceList) {
        this.attendanceList = attendanceList;
    }

    public PerformanceList getPerformanceList() {
        return performanceList;
    }

    public void setPerformanceList(PerformanceList performanceList) {
        this.performanceList = performanceList;
    }

    @Override
    public String toString() {
        String output = "Event: " + getName();

        if (!getDatetime().equals("yyyy-MM-dd HHmm")) {
            output += (", time: " + getDatetime());
        }
        if (!getVenue().equals("")) {
            output += (", venue: " + getVenue());
        }

        return output;
    }

    /**
     * Returns a storage-compatible String representation of the event.
     * @return a storage-compatible String representation of the event
     */
    public String toStorable() {
        String type = this.getClass().getSimpleName();

        StringBuilder output = new StringBuilder(type + '|'
                + name + '|'
                + datetime.toStorable() + '|'
                + venue
                + ",");

        for (Attendance attendance : attendanceList.getAttendanceList()) {
            output.append(attendance.toString());
            output.append('|');
        }
        // add a space to prevent NoSuchElementException, in case no AttendanceList
        output.append(",");

        for (Performance performance : performanceList.getPerformanceList()) {
            output.append(performance.toString());
            output.append('|');
        }
        // add a space to prevent NoSuchElementException, in case no PerformanceList
        output.append(",");

        return output.toString();
    }

    /**
     * Returns an event based on its storage-compatible String representation.
     * Major components are split by {@code ,}, minor components are split by {@code |}.
     * @param representation a storage-compatible String representation of an event
     * @return an Event object
     */
    public static Event parseStorable(String representation) throws DukeException {
        Event newEvent;
        String[] tokens = representation.split(",");

        // name, datetime, venue
        String[] token1 = tokens[0].split("\\|");
        String type = token1[0];
        String name = token1[1];
        String datetime = token1[2];
        String venue;
        try {
            venue = token1[3];
        } catch (ArrayIndexOutOfBoundsException m) {
            venue = "";
        }

        switch (type) {
        case "Seminar":
            newEvent = new Seminar(name, datetime, venue);
            break;
        default:
            newEvent = new Event(name, datetime, venue);
        }

        // add attendance list, populate it
        AttendanceList attendanceList = new AttendanceList();
        try {
            String[] token2 = tokens[1].split("\\|");
            for (String attendance : token2) {
                String[] attendanceDetail = attendance.split(": ");
                assert attendanceDetail.length == 2 : "Name contains ': '";
                String person = attendanceDetail[0];
                String isPresent = attendanceDetail[1];
                Attendance newAttendance = new Attendance(person, isPresent);

                attendanceList.addToList(newAttendance, name);
            }
        } catch (ArrayIndexOutOfBoundsException m) {
            // Do nothing, as intended
        }

        // add performance list, populate it
        PerformanceList performanceList = new PerformanceList();
        try {
            String[] token3 = tokens[2].split("\\|");
            for (String performance : token3) {
                String[] performanceDetail = performance.split(": ");
                assert performanceDetail.length == 2 : "Name contains ': '";
                String person = performanceDetail[0];
                String result = performanceDetail[1];
                Performance newPerformance = new Performance(person, result);

                performanceList.addToList(newPerformance, name);
            }
        } catch (ArrayIndexOutOfBoundsException m) {
            // Do nothing, as intended
        }

        return newEvent;
    }
}