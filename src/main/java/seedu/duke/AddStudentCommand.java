package seedu.duke;


/**
 * Class representing a command to add a new student.
 */
public class AddStudentCommand extends Command {

    private final Attendance student;

    public AddStudentCommand(Attendance student) {
        this.student = student;
    }

    @Override
    public void execute(Ui ui, Storage storage) throws DukeException {
        Ui.displayAddStudent();
        storage.getTasks().add(student);
        storage.write();
    }
}
