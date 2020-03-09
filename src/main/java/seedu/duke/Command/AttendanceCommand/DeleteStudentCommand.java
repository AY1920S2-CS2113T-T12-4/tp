package seedu.duke.Command.AttendanceCommand;

import seedu.duke.*;
import seedu.duke.Command.Command;

/**
 * Class to handle deletion of Student.
 */
public class DeleteStudentCommand extends Command {
    private int index;

    /**
     * Creates a new DeleteCommand with the given task.
     *
     * @param index The index of the task to be deleted.
     */

    public DeleteStudentCommand(int index) {
        this.index = index;
    }

    /**
     * Executes this command on the given task list and user interface.
     *
     * @param ui The user interface displaying events on the task list.
     * @param storage The duke.storage object containing task list.
     */

    @Override
    public void execute(Ui ui, Storage storage) throws DukeException {
        try {
            Student student = storage.getTasks().remove(index);
            Ui.displayDeleted(student);
        } catch (IndexOutOfBoundsException e) {
            throw new DukeException(ErrorMessage.OUT_OF_BOUNDS);
        }
        storage.write();
    }
}
