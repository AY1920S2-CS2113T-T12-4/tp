package seedu.duke;


/**
 * Class representing a command to display existing task.
 */
public class ListAttendanceCommand extends Command {
    /**
     * Executes this command on the given attendance list and user interface.
     *
     * @param ui The user interface displaying events on the task list.
     * @param storage The duke.storage object containing task list.
     */
    @Override
    public void execute(Ui ui, Storage storage) throws DukeException {
        Ui.displayAttendanceList(storage.getTasks());
    }
}
