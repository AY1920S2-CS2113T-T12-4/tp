package seedu.command.interpreter;

import seedu.attendance.AttendanceList;
import seedu.command.Command;
import seedu.command.attendance.AddAttendanceList;
import seedu.command.attendance.ClearAttendanceList;
import seedu.command.attendance.SortAttendanceListByName;
import seedu.command.attendance.ViewAttendanceList;
import seedu.command.attendance.SortAttendanceListByStatus;
import seedu.event.EventList;
import seedu.exception.PacException;
import seedu.ui.UI;

import java.io.IOException;
import java.util.logging.Logger;
import java.util.logging.ConsoleHandler;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.LogManager;

/**
 * To interpret the attendance command.
 */
public class AttendanceCommandInterpreter extends CommandInterpreter {

    AttendanceList attendances;
    String eventName;
    UI ui;

    public AttendanceCommandInterpreter(EventList eventList) {
        super(eventList);
        this.ui = new UI();
    }

    /**
     * Execute the command from userInput.
     *
     * @param commandDescription The userInput from the Ui.
     * @throws PacException If the command is undefined.
     */
    public Command decideCommand(String commandDescription) throws PacException {

        String commandType = getFirstWord(commandDescription);

        switch (commandType.toLowerCase().trim()) {
        case "add":
            try {
                eventName = ui.getEventNameForAttendance();
                attendances = getAttendance(eventName);
                return new AddAttendanceList(attendances, eventName);
            } catch (Exception e) {
                throw new PacException("Attendance Command Add failed.");
            }
        case "view":
            try {
                eventName = ui.getEventNameForAttendance();
                attendances = getAttendance(eventName);
                return new ViewAttendanceList(attendances);
            }  catch (Exception e) {
                throw new PacException("Attendance Command View failed.");
            }
        case "clear":
            try {
                eventName = ui.getEventNameForAttendance();
                attendances = getAttendance(eventName);
                return new ClearAttendanceList(attendances, eventName);
            } catch (Exception e) {
                throw new PacException("Attendance Command Clear failed.");
            }
        case "sort":
            try {
                switch (sortType()) {
                case "name":
                    try {
                        eventName = ui.getEventNameForAttendance();
                        attendances = getAttendance(eventName);
                        return new SortAttendanceListByName(attendances, eventName);
                    } catch (Exception e) {
                        throw new PacException("Attendance Command Sort By Name failed.");
                    }
                case "status":
                    try {
                        eventName = ui.getEventNameForAttendance();
                        attendances = getAttendance(eventName);
                        return new SortAttendanceListByStatus(attendances, eventName);
                    } catch (Exception e) {
                        throw new PacException("Attendance Command Sort By Status failed.");
                    }
                default:
                    throw new PacException("Unknown Attendance Sort Command");
                }
            } catch (Exception e) {
                throw new PacException("Attendance Command Sort failed.");
            }
        default:
            throw new PacException("Attendance: Unknown command.");
        }
    }

    private AttendanceList getAttendance(String eventName) throws PacException {
        return eventList.getEvent(eventName).getAttendanceList();
    }

    private String sortType() {
        UI.display("Please Key in either 'name' or 'status'.");
        ui.readUserInput();
        return ui.getUserInput().toLowerCase().trim();
    }
}
