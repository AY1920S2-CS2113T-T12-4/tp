package seedu.duke.Command.AttendanceCommand;


import seedu.duke.*;
import seedu.duke.Command.Command;

/**
 * Class representing a command to add a new student.
 */
public class AddStudentCommand extends Command {

    private final StudentAttendance student;

    public AddStudentCommand(StudentAttendance student) {
        this.student = student;
    }

    @Override
    public void execute(Ui ui, Storage storage) throws DukeException {
        Ui.displayAddStudent();
        storage.getTasks().add(student);
        storage.write();
    }
}
