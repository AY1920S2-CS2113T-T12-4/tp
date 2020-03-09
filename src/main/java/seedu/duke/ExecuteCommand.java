package seedu.duke;

import seedu.duke.Command.AttendanceCommand.*;
import seedu.duke.Command.Command;

import static seedu.duke.AttendanceParser.getCommandWord;
import static seedu.duke.AttendanceParser.getIndex;
import static seedu.duke.AttendanceParser.getWord;
import static seedu.duke.AttendanceParser.createStudentAttendance;

public class ExecuteCommand {
    /**
     * The class to handle the execution of the command for attendance.
     *
     * @param userInput The input from the user command.
     * @return The new object.
     * @throws DukeException The exception due to invalid command.
     */
    public static Command executeCommand(String userInput) throws DukeException {
        String commandWord = getCommandWord(userInput);
        switch (commandWord) {
        case "add":
            return new AddStudentCommand(createStudentAttendance(userInput));
        case "list":
            return new ListAttendanceCommand();
        case "find":
            return new FindStudentCommand(getWord(userInput));
        case "delete":
            return new DeleteStudentCommand(getIndex(userInput));
        case "exit":
            return new ExitCommand();
        default:
            throw new DukeException(ErrorMessage.UNKNOWN_COMMAND);
        }
    }
}
