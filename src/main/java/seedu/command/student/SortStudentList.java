package seedu.command.student;

import seedu.StudentList;
import seedu.command.Command;
import seedu.exception.DukeException;

import static seedu.duke.Duke.studentListCollection;

/**
 * Class representing a student related command to sort all studentList by alphabetical order.
 */
public class SortStudentList extends Command {

    protected StudentList studentList;

    private void sort() {
        for (int i = 0; i < studentListCollection.size(); i++) {
            studentListCollection.get(i).sortAscending();
        }
    }

    @Override
    public void execute() throws DukeException {
        sort();
    }
}
