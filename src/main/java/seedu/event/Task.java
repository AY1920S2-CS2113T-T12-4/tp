package seedu.event;

import seedu.exception.DukeException;

import java.time.Instant;
import java.util.ArrayList;

public class Task {
    protected String name;
    protected DateTime datetime;
    protected String venue;
    private ArrayList<String> participantList;
    private ArrayList<String> attendanceList;

    public Task() throws DukeException {
        setName("");
        setDatetime("");
        setVenue("");
        this.participantList = new ArrayList<>();
        this.attendanceList = new ArrayList<>();
    }

    public Task(String name, String datetime, String venue) throws DukeException {
        this();
        setName(name);
        setDatetime(datetime);
        setVenue(venue);
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

    @Override
    public String toString() {
        return "";
    }

}
