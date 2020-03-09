package seedu.duke.Command.AttendanceCommand;

import seedu.duke.Command.Command;
import seedu.duke.DukeException;
import seedu.duke.Storage;
import seedu.duke.Ui;

/**
 * Class representing a command to exit the program.
 */
public class ExitCommand extends Command {

    /**
     * Executes this command on the given task list and user interface.
     *
     * @param ui The user interface displaying events on the task list.
     * @param storage The duke.storage object containing task list.
     */

    @Override
    public void execute(Ui ui, Storage storage) throws DukeException {
        System.out.println("EXIT");
    }
}
