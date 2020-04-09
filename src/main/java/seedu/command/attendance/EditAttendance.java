package seedu.command.attendance;

import seedu.attendance.AttendanceList;
import seedu.command.Command;
import seedu.exception.PacException;
import seedu.ui.DisplayTable;
import seedu.ui.UI;

public class EditAttendance extends Command {

    protected DisplayTable displayTable;
    protected UI ui;
    protected AttendanceList attendanceList;
    protected String eventName;
    protected int index;


    public EditAttendance(AttendanceList attendances) {
        this.attendanceList = attendances;
        this.ui = new UI();
        this.displayTable = new DisplayTable();
    }

    /**
     * To determine the index of the attendance the user wishes to edit.
     */
    private void getIndex() {
        UI.display("Please state the index of the student you wish to edit");
        ui.readUserInput();
        this.index = Integer.parseInt(ui.getUserInput()) - 1;
    }

    /**
     * To display the selected attendance.
     */
    private void displayAttendance() {
        displayTable.printHeaderOfThree("index", "Name of Student", "Status");
        displayTable.printBodyOfThree(1, attendanceList.getAttendanceList().get(index).getStudentName(),
                attendanceList.getAttendanceList().get(index).getStatus());
    }

    /**
     * Method to set new Name.
     */
    private void editName() {
        UI.display("What do you want to change the name to?");
        ui.readUserInput();
        String studentName = ui.getUserInput();
        if (attendanceList.isDuplicate(studentName)) {
            UI.display("Duplicate name found. Please try again.");
            editName();
        } else {
            attendanceList.getAttendanceList().get(index).setName(studentName);
        }
    }

    /**
     * Method to set new Status.
     */
    private void editStatus() {
        UI.display("What do you want to change the status to? [Y/N]");
        ui.readUserInput();
        String studentStatus = ui.getUserInput();
        attendanceList.getAttendanceList().get(index).setStatus(studentStatus);
    }

    /**
     * Method to edit the existing attendance.
     */
    private void edit() {
        attendanceList.displayAttendanceList();
        getIndex();
        displayAttendance();
        if (setNewName()) {
            editName();
        } else {
            editStatus();
        }
        attendanceList.displayAttendanceList();
    }

    /**
     * To determine if the user want to set new.
     * @return True if the user intends to change name.
     *         False if the user intends to change status.
     */
    private boolean setNewName() {
        UI.display("Do you wish to change the `name` or change the `status`");
        ui.readUserInput();
        String input = ui.getUserInput();
        if (input.equals("name")) {
            return true;
        } else if (input.equals("status")) {
            return false;
        } else {
            UI.display("I do not understand your message. Please try again");
            return setNewName();
        }
    }

    @Override
    public void execute() throws PacException {
        edit();
    }
}
