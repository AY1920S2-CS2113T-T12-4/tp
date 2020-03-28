package seedu.command.attendance;

import seedu.attendance.Attendance;
import seedu.attendance.AttendanceList;
import seedu.command.Command;
import seedu.exception.DukeException;
import seedu.parser.AttendanceParser;
import seedu.ui.UI;

import java.util.ArrayList;

import static seedu.duke.Duke.studentListCollection;

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
     * @throws DukeException If studentNameList is empty.
     */
    public void addToList() throws DukeException {
        System.out.println("Would you like to import an existing student list? "
                + "If yes, input 'yes'. Else, input anything.");
        if (isByNameList()) {
            ui.printStudentListCollection();
            ArrayList<String> studentNameList = fetchAttendanceList();
            if (studentNameList.isEmpty()) {
                throw new DukeException("There is no existing student list.");
            }
            appendWithExistingList(studentNameList);
        } else {
            createNewList();
        }
    }

    /**
     * Create new attendanceList.
     * @throws DukeException If parameter provided is invalid.
     */
    private void createNewList() throws DukeException {
        int studentNumber = 0;
        String parameter = ui.getAttendancePerimeter();
        do {
            attendances.addToList(getAttendance(parameter), eventName);
            parameter = ui.getStringInput();
            studentNumber++;
        } while (!parameter.equals("done"));
        System.out.println("You have successfully added "
                + studentNumber + " to the attendance list.");
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
        System.out.println("AttendanceList added");
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
     * @throws DukeException If a string is given instead of an integer.
     */
    private ArrayList<String> fetchAttendanceList() throws DukeException {
        ui.readIndexPrompt();
        ui.readUserInput();
        try {
            int index = Integer.parseInt(ui.getUserInput());
            return studentListCollection.get(index - 1).getStudentList();
        } catch (Exception e) {
            throw new DukeException("Invalid Format");
        }
    }

    /**
     * Method to create a new attendance to be inserted in the attendanceList.
     * @param parameter the student name and attendance status.
     * @return attendance of a student.
     * @throws DukeException If the parameter given is invalid.
     */
    private Attendance getAttendance(String parameter) throws DukeException {
        return new AttendanceParser().parseAttendance(parameter);
    }

    @Override
    public void execute() throws DukeException {
        try {
            addToList();
        } catch (Exception e) {
            throw new DukeException("Attendance List fail to add. If you wish to add attendance again,\n"
                    + "please type the command 'attendance add' again");
        }

    }
}