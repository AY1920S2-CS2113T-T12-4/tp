package seedu.command.performance;

import seedu.StudentList;
import seedu.command.Command;
import seedu.exception.DukeException;
import seedu.parser.PerformanceParser;
import seedu.performance.Performance;
import seedu.performance.PerformanceList;
import seedu.ui.DisplayList;
import seedu.ui.UI;

import static seedu.duke.Duke.studentListCollection;

public class AddPerformance extends Command {
    private UI ui;
    private DisplayList displayList;
    PerformanceList performances;
    String eventName;

    /**
     * Constructor for AddPerformanceCommand. Takes PerformanceList, performances of the
     * event to be modified, and String eventName, name of the event that owns the
     * performance list.
     */
    public AddPerformance(PerformanceList performances, String eventName) {
        this.performances = performances;
        this.eventName = eventName;
        this.ui = new UI();
        this.displayList = new DisplayList();
    }

    /**
     * Add the performance to performance list.
     * The user is to choose whether to add the performance by importing a list
     * or adding manually.
     */
    public void addToList() throws DukeException {
        boolean isByNameList = ui.isImportList();
        if (isByNameList) {
            addByList();
        }
        if (!isByNameList) {
            addManually();
        }
    }

    /**
     * This method gets the user to manually add a performance.
     * The user has to input name of student and grade for each
     * performance to be added. This method tells the user
     * whether the performance is added successfully with a
     * reply message.
     *
     * @throws DukeException A DukeException is thrown when the
     *                       performance format input is incorrect
     *                       and cannot be added successfully.
     */
    private void addManually() throws DukeException {
        int studentNumber = 0;
        String parameter = ui.getPerformanceParameter();
        while (!parameter.equals("done")) {
            performances.addToList(getPerformance(parameter), eventName);
            studentNumber++;
            parameter = ui.getStringInput();
        }
        System.out.println("You have successfully added "
                + studentNumber + " result(s) to the performance list.");
    }

    /**
     * This method get the user to select the index of student list to import
     * and return the list.
     * @return The student list selected by user.
     * @throws DukeException Throws DukeException when there is no student list
     *                       exist in the student list collection.
     */
    private StudentList getList() throws DukeException {
        if (studentListCollection.isEmpty()) {
            throw new DukeException("There is no existing student list.");
        }
        int listIndex = displayList.getStudentListIndex();
        return studentListCollection.get(listIndex - 1);
    }

    /**
     * This method get the user to input student's performance one by one
     * with the student list imported.
     * @throws DukeException Throws DukeException when there is no student list
     *      *                       exist in the student list collection.
     */
    private void addByList() throws DukeException {
        StudentList studentList = getList();
        for (String studentName : studentList.getStudentList()) {
            performances.addToList(new Performance(studentName,
                    ui.getResultOfStudent(studentName)), eventName);
        }
    }

    /**
     * It process the data input by student and returns
     * a Performance base on the input.
     * @return A Performance of student
     * @throws DukeException throws DukeException when the user input
     *                       is insufficient or incorrect.
     */
    private Performance getPerformance(String parameter) throws DukeException {
        return new PerformanceParser().parsePerformance(parameter);
    }

    @Override
    public void execute() throws DukeException {
        addToList();
    }
}