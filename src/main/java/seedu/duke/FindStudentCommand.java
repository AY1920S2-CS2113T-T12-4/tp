package seedu.duke;

import java.util.ArrayList;

public class FindStudentCommand extends Command {

    private String keyword;

    /**
     * Creates a new FindCommand with the given keyword.
     *
     * @param keyword The keyword to find.
     */

    public FindStudentCommand(String keyword) {
        this.keyword = keyword;
    }

    /**
     * Executes this command on the given task list and user interface.
     * Searches the student by name.
     *
     * @param ui The user interface displaying events on the task list.
     * @param storage The duke.storage object containing task list.
     */

    @Override
    public void execute(Ui ui, Storage storage) throws DukeException {
        ArrayList<Student> studentlist = storage.getTasks();
        ArrayList<Student> result = new ArrayList<>();
        for (Student student : studentlist) {
            if (student.getStudentName().contains(keyword)) {
                result.add(student);
            }
        }
        Ui.displaySearchResults(result);
    }
}
