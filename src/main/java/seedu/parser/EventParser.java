package seedu.parser;

import seedu.event.DateTime;
import seedu.event.Event;
import seedu.exception.DukeException;

public class EventParser {
    private String name;
    private String date;
    private String time;
    private String venue;

    public EventParser() {
        this.name = "";
        this.date = "";
        this.time = "";
        this.venue = "";
    }

    /**
     * Returns the index in a string of parameters with this format:
     * [i/INDEX] ... .
     * @param parameters original parameters
     * @return the index in a string of parameters
     */
    public int parseIndex(String parameters) throws DukeException {
        String[] tokens = parameters.split(" ");
        int index = -1;
        for (String token: tokens) {
            switch (token.substring(0, 2)) {
            case "i/":
                index = Integer.parseInt(parameters.substring(2));
                break;
            default:
                // does nothing, as intended
                break;
            }
        }

        if (index == -1) {
            throw new DukeException("index not found");
        }
        return index;
    }

    // TODO: [r/FREQ[/TIME or /DAY]]
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

        return new Event(name, datetime, venue);
    }

    private void splitByEventFlags(String[] tokens) throws DukeException {
        String mostRecent = null;
        for (String token : tokens) {
            switch (token.substring(0, 2)) {
            case "n/":
                ensureNotDuplicateFlag(name, "duplicate name flag");
                name += token.substring(2);
                mostRecent = name;
                break;
            case "t/":
                ensureNotDuplicateFlag(time, "duplicate time flag");
                time += token.substring(2);
                mostRecent = time;
                break;
            case "d/":
                ensureNotDuplicateFlag(date, "duplicate date flag");
                date += token.substring(2);
                mostRecent = date;
                break;
            case "v/":
                ensureNotDuplicateFlag(venue, "duplicate venue flag");
                venue += token.substring(2);
                mostRecent = venue;
                break;
            default:
                // assumes that all valid flags have been processed before this line
                if (isUnknownFlag(token)) {
                    throw new DukeException("unknown flag");
                }
                if (mostRecent == null) {
                    throw new DukeException("parameter without flag");
                }
                mostRecent += (" " + token);
            }
        }
    }

    private void ensureNotDuplicateFlag(String name, String message) throws DukeException {
        if (!name.isEmpty()) {
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