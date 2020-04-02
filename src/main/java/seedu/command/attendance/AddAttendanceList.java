package seedu.command.attendance;

import seedu.attendance.Attendance;
import seedu.attendance.AttendanceList;
import seedu.command.Command;
import seedu.exception.PacException;
import seedu.student.StudentList;
import seedu.ui.UI;

import java.util.ArrayList;

import static seedu.pac.Pac.studentListCollection;

/**
 * Class representing an attendance related command to add an attendanceList for a specific event.
 * attendanceList by default will create a new list.
 * However, attendanceList can be added using an existing studentList in the studentListCollection.
 * If there is an existing attendanceList, this command will append to the end of the list.
 */
public class AddAttendanceList extends Command {

    protected AttendanceList attendances;
    protected String eventName;
    private UI ui;

    public AddAttendanceList(AttendanceList attendances, String eventName) {
        this.attendances = attendances;
        this.eventName = eventName;
        this.ui = new UI();
    }

    /**
     * Method to decide whether user wants to create a new list or use an existing student list.
     * @throws PacException If studentNameList is empty.
     */
    public void addToList() throws PacException {
        UI.display("Would you like to import an existing student list? "
                + "If yes, input 'yes'. Else, input anything.");
        if (isByNameList()) {
            if (studentListCollection.isEmpty()) {
                UI.display("There is no existing student list to import");
            } else {
                ui.printStudentListCollection();
                ArrayList<String> studentNameList = fetchAttendanceList();
                appendWithExistingList(studentNameList);
            }
        } else {
            createNewList();
        }
    }

    /**
     * Create new attendanceList.
     * @throws PacException If parameter provided is invalid.
     */
    private void createNewList() throws PacException {
        int studentNumber = 0;
        String name = "";
        String status = "";
        StudentList newStudentList = new StudentList(eventName);
        while (!status.equals("done")) {
            UI.display("Please key in student name.");
            ui.readUserInput();
            name = ui.getUserInput();
            if (name.equals("done")) {
                break;
            }
            UI.display("Please key in the student's attendance status [Y/N].");
            ui.readUserInput();
            status = ui.getUserInput();
            attendances.addToList(new Attendance(name,status), eventName);
            newStudentList.addToList(name);
            studentNumber++;
        }
        studentListCollection.push(newStudentList);
        UI.display("You have successfully added "
                + studentNumber + " to the attendance list.\n");
    }

    /**
     * Append attendanceList with an existing student list.
     * @param studentNameList the existing student list selected.
     */
    private void appendWithExistingList(ArrayList<String> studentNameList) {
        for (String studentName: studentNameList) {
            attendances.addToList(new Attendance(studentName,
                    ui.getAttendanceStatusOfStudent(studentName)), eventName);
        }
        UI.display("AttendanceList added");
    }

    /**
     * Check the user input.
     * @return true if the user wants to import an existing list.
     * @return false if the user wants to create a new list.
     */
    private boolean isByNameList() {
        String reply = ui.getStringInput();
        if (reply.contains("yes")) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * Method to fetch studentList from studentListCollection.
     * User can select base on the index given.
     * @return A studentList selected from the studentListCollection.
     * @throws PacException If a string is given instead of an integer.
     */
    private ArrayList<String> fetchAttendanceList() throws PacException {
        UI.display("Please state the index of the studentList that you wish to import");
        ui.readUserInput();
        try {
            int index = Integer.parseInt(ui.getUserInput());
            return studentListCollection.get(index - 1).getStudentList();
        } catch (Exception e) {
            throw new PacException("Invalid Format");
        }
    }

    @Override
    public void execute() throws PacException {
        try {
            addToList();
        } catch (Exception e) {
            throw new PacException("Attendance List fail to add. If you wish to add attendance again,\n"
                    + "please type the command 'attendance add' again");
        }

    }
}