package seedu.command.calendar;

import seedu.command.Command;
import seedu.event.EventList;
import seedu.exception.DukeException;

public class AddSemester extends Command {
    private EventList eventList;
    private int year;
    private int semester;
    private CalendarList calendarList;

    public AddSemester(EventList eventList, Integer year, int semester) {
        this.eventList = eventList;
        this.year = year;
        this.semester = semester;
        this.calendarList = new CalendarList();
    }

    @Override
    public void execute() throws DukeException {
        calendarList.showEvents(eventList, year, semester);
    }
}
