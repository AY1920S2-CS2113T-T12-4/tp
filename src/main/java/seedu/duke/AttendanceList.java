package seedu.duke;

import static seedu.duke.Parser.createStudent;
import static seedu.duke.Parser.getCommandWord;

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
        case "EXIT":
            return new ExitCommand();
        default:
            throw new DukeException(ErrorMessage.UNKNOWN_COMMAND);
        }
    }
}
