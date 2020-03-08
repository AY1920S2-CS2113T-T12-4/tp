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
        case "listAttendance":
            return new ListAttendanceCommand();
        case "findAttendance":
            return new FindStudentCommand(getWord(userInput));
        case "deleteAttendance":
            return new DeleteStudentCommand(getIndex(userInput));
        case "EXIT":
        case "exit":
            return new ExitCommand();
        default:
            throw new DukeException(ErrorMessage.UNKNOWN_COMMAND);
        }
    }
}
