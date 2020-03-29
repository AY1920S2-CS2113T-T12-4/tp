package seedu.command.attendance;

import seedu.attendance.AttendanceList;
import seedu.command.Command;
import seedu.exception.DukeException;
import seedu.ui.UI;

/**
 * Class representing an attendance related command to sort the attendanceList of a specific event.
 * Sorts the attendanceList in alphabetical order.
 */
public class SortAttendanceListByName extends Command {

    protected UI ui;
    protected AttendanceList attendances;

    public SortAttendanceListByName(AttendanceList attendances) {
        this.attendances = attendances;
        this.ui = new UI();
    }

    /**
     * Method to sort an attendance list according to name.
     */
    private void sort() {
        if (attendances.isEmpty()) {
            ui.displayAttendanceMessage("An empty list cannot be sorted");
        } else {
            attendances.sort();
            ui.displayAttendanceMessage("sorted by name");
        }
    }

    @Override
    public void execute() throws DukeException {
        sort();
    }
}