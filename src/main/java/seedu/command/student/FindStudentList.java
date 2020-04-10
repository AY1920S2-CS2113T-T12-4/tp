package seedu.command.student;

import seedu.student.StudentList;
import seedu.command.Command;
import seedu.exception.PacException;
import seedu.ui.DisplayList;
import seedu.ui.UI;

import java.util.ArrayList;

import static seedu.pac.Pac.studentListCollection;

/**
 * Class representing a student related command to find an existing studentList by list name.
 */
public class FindStudentList extends Command {

    private String name;
    protected ArrayList<StudentList> searchResults = new ArrayList<>();
    protected DisplayList displayList = new DisplayList();
    protected UI ui = new UI();


    /**
     * Method to find an existing student list from studentListCollection by list name.
     * @throws PacException    PacException is thrown when there is an out of bound index.
     */
    protected void find() throws PacException {
        if (studentListCollection.isEmpty()) {
            UI.displayStudentListCollectionEmpty();
        } else {
            displayStudentList();
            UI.display("\nPlease state the list name you are searching for");
            ui.readUserInput();
            name = ui.getUserInput();
            for (int i = 0; i < studentListCollection.size(); i++) {
                if (studentListCollection.get(i).getListName().toLowerCase().contains(name.toLowerCase())) {
                    searchResults.add(studentListCollection.get(i));
                }
            }
            if (searchResults.isEmpty()) {
                UI.display("Nothing match you description : " + name);
            } else {
                UI.display("You have " + searchResults.size() + " matches:");
                displayList.printSearchResults(searchResults);
            }
        }
    }


    /**
     * Displays studentListCollection.
     */
    private void displayStudentList() {
        ui.display("Displaying all student list: ");
        ui.printStudentListCollection();
    }

    @Override
    public void execute() throws PacException {
        find();
    }


}
