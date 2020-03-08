package seedu.duke;

import static seedu.duke.Parser.*;

public class AttendanceList {
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
        case "addAttendance":
            return new AddStudentCommand(createStudent(userInput));
        case "list":
            return new ListAttendanceCommand();
        case "find":
            return new FindStudentCommand(getWord(userInput));
        case "EXIT":
            return new ExitCommand();
        default:
            throw new DukeException(ErrorMessage.UNKNOWN_COMMAND);
        }
    }
}
