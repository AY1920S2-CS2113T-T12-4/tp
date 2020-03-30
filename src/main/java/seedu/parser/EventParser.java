package seedu.parser;

import seedu.event.Event;
import seedu.event.Seminar;
import seedu.exception.DukeException;

public class EventParser {
    private String name;
    private String date;
    private String time;
    private String venue;
    private int index;


    public EventParser() {
        this.name = "";
        this.date = "";
        this.time = "";
        this.venue = "";
        this.index = -1;
    }

    public String getDateTime() {
        return date + " " + time;
    }

    public int getIndex() {
        return index;
    }

    public void parseTokens(String parameters) throws DukeException {
        String[] tokens = parameters.split(" ");
        splitByEventFlags(tokens);
    }


    /**
     * Returns the index in a string of parameters with this format:
     * [i/INDEX] ... .
     * @param parameters original parameters
     * @return the index in a string of parameters
     */
    public int parseIndex(String parameters) throws DukeException {
        String[] tokens = parameters.split(" ");
        splitByEventFlags(tokens);
        return index;
    }

    /**
     * Parses string passed in to obtain name of the event.
     *
     * @param parameters Input String by user in the format: i/[INDEX] n/[EVENTNAME]
     * @return Name of event as a string.
     */
    public String parseEventName(String parameters) throws DukeException {
        String[] tokens = parameters.split(" ");
        splitByEventFlags(tokens);
        return name;
    }

    /**
     * Parses input string to obtain date and time of the event.
     *
     * @param parameters Input String by user in the format: i/[INDEX] d/[EVENTDATE] t/[EVENTTIME]
     * @return Date and time of the event as a string.
     */
    public String parseEventDateTime(String parameters) throws DukeException {
        String[] tokens = parameters.split(" ");
        splitByEventFlags(tokens);
        return date + " " + time;
    }

    /**
     * Parses input string to obtain venue of the event.
     *
     * @param parameters Input String by user in the format: i/[INDEX] v/[EVENTVENUE]
     * @return Venue of the event as a String
     */
    public String parseVenue(String parameters) throws DukeException {
        String[] tokens = parameters.split(" ");
        splitByEventFlags(tokens);
        return venue;
    }

    /**
     * Parse parameters based on the following format:
     * n/EVENTNAME [t/EVENTTIME] [d/EVENTDATE] [v/EVENTVENUE].
     * @param parameters original parameters
     * @return an Event object with the relevant information
     */
    public Event parseEvent(String parameters) throws DukeException {
        String[] tokens = parameters.split(" ");
        splitByEventFlags(tokens);
        String datetime = date + " " + time;
        if (name.equals("") && venue.equals("")) {
            throw new DukeException("EventParser: Invalid arguments");
        }
        return new Event(name, datetime, venue);
    }

    public Seminar parseSeminar(String parameters) throws DukeException {
        String[] tokens = parameters.split(" ");
        splitByEventFlags(tokens);
        String datetime = date + " " + time;
        if (name.equals("") && venue.equals("")) {
            throw new DukeException("EventParser: Invalid arguments");
        }
        return new Seminar(name, datetime, venue);
    }

    private void splitByEventFlags(String[] tokens) throws DukeException {
        String mostRecent = null;
        for (String token : tokens) {
            if (token.length() < 2) {
                if (mostRecent == null) {
                    throw new DukeException("EventParser: Flag is too short");
                } else if (validFlagToAppend(mostRecent)) {
                    append(mostRecent, token);
                }
            } else {
                switch (token.substring(0, 2).toLowerCase()) {
                case "n/":
                    ensureNotDuplicateFlag(name, "EventParser: Duplicate name flag");
                    name += token.substring(2);
                    mostRecent = "name";
                    break;
                case "t/":
                    ensureNotDuplicateFlag(time, "EventParser: Duplicate time flag");
                    time += token.substring(2);
                    mostRecent = "time";
                    break;
                case "d/":
                    ensureNotDuplicateFlag(date, "EventParser: Duplicate date flag");
                    date += token.substring(2);
                    mostRecent = "date";
                    break;
                case "v/":
                    ensureNotDuplicateFlag(venue, "EventParser: Duplicate venue flag");
                    venue += token.substring(2);
                    mostRecent = "venue";
                    break;
                case "i/":
                    ensureNotDuplicateIndex(index, "EventParser: Duplicate index flag");
                    try {
                        index = Integer.parseInt(token.substring(2));
                    } catch (NumberFormatException m) {
                        throw new DukeException("EventParser: Parameter is not an integer");
                    }
                    mostRecent = "index";
                    break;
                default:
                    // assumes that all valid flags have been processed before this line
                    if (isUnknownFlag(token)) {
                        throw new DukeException("EventParser: Unknown flag");
                    }
                    if (mostRecent == null) {
                        throw new DukeException("EventParser: Parameter is provided without flag");
                    }
                    if (validFlagToAppend(mostRecent)) {
                        append(mostRecent, token);
                    }
                }
            }
        }
    }

    private boolean validFlagToAppend(String flag) {
        return flag.equals("name") || flag.equals("venue");
    }

    /**
     * Append a string to the most recently added parameter.
     * @param mostRecent the most recently added parameter
     * @param token the string to be appended
     */
    private void append(String mostRecent, String token) throws DukeException {
        if (token.isEmpty() || token.equals(" ")) {
            return;
        }
        switch (mostRecent) {
        case "name":
            name += name.isEmpty() ? token : (" " + token);
            break;
        case "venue":
            venue += venue.isEmpty() ? token : (" " + token);
            break;
        default:
            throw new DukeException("EventParser: Invalid flag to append to");
        }
    }

    private void ensureNotDuplicateFlag(String name, String message) throws DukeException {
        if (!name.isEmpty()) {
            throw new DukeException(message);
        }
    }

    private void ensureNotDuplicateIndex(int index, String message) throws DukeException {
        if (index != -1) {
            throw new DukeException(message);
        }
    }

    /**
     * Returns {@code true} if the input contains a flag.
     * It is assumed that all valid flags have been handled
     * before the execution of this function.
     * @param input the token to be checked
     * @return {@code true} if the input contains a flag
     */
    private boolean isUnknownFlag(String input) {
        return input.charAt(1) == '/';
    }
}