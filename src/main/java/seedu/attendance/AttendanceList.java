package seedu.attendance;

import seedu.ui.DisplayTable;
import seedu.ui.UI;

import java.util.ArrayList;
import java.util.Collections;

import static seedu.attendance.Attendance.attendanceListNameComparator;
import static seedu.attendance.Attendance.attendanceStatusComparator;

/**
 * Class representing attendance list of student's attendance status.
 */
public class AttendanceList {

    protected ArrayList<Attendance> attendanceList = new ArrayList<>();
    protected UI ui = new UI();
    private DisplayTable displayTable = new DisplayTable();

    /**
    * To fetch the existing attendanceList.
    * @return attendanceList.
    */
    public ArrayList<Attendance> getAttendanceList() {
        return attendanceList;
    }

    /**
     * To check if there is an existing name in the attendanceList.
     * @param name the name of the student to be added into the attendanceList.
     * @return  If there is an existing name in the attendanceList, return true.
     *          ELse, return false.
     */
    public boolean isDuplicate(String name) {
        String existingStudentName;
        String newStudentName = name.toLowerCase();
        for (int i = 0; i < attendanceList.size(); i++) {
            existingStudentName = attendanceList.get(i).getStudentName().toLowerCase();
            if (existingStudentName.equals(newStudentName)) {
                return true;
            }
        }
        return false;
    }

    /**
     * To add to the existing attendanceList in the selected event.
     * @param attendance the existing attendanceList in the event.
     * @param eventName the name of the event.
     */
    public void addToList(Attendance attendance, String eventName) {
        attendanceList.add(attendance);
        ui.addAttendanceMessage(attendance.studentName, attendance.getStatus(), eventName);
    }

    /**
     * To load to the existing attendanceList of the specific event from storage.
     * @param attendance the attendanceList in the event.
     */
    public void loadFromStorage(Attendance attendance) {
        attendanceList.add(attendance);
    }

    /**
     * To display the attendanceList in table form.
     */
    public void displayAttendanceList() {
        int index = 1;
        displayTable.printHeaderOfThree("index", "Name of Student", "Status");
        for (Attendance attendance : attendanceList) {
            displayTable.printBodyOfThree(index, attendance.getStudentName(), attendance.getStatus());
            index++;
        }
    }

    /**
     * Check whether the attendanceList is empty.
     * @return the status of attendanceList.
     */
    public boolean isEmpty() {
        return attendanceList.isEmpty();
    }

    /**
     * Clear the attendanceList.
     */
    public void clearList() {
        attendanceList.clear();
    }

    /**
     * To sort the attendanceList by the name of the student alphabetically.
     */
    public void sortByName() {
        Collections.sort(attendanceList,attendanceListNameComparator);
    }


    /**
     * To sort the attendanceList by the status of the student.
     */
    public void sortByStatus() {
        Collections.sort(attendanceList,attendanceStatusComparator);
    }


    public void findAttendance(String keyword) {
        int index = 1;
        ArrayList<Attendance> searchResults = new ArrayList<>();
        for (Attendance attendance: attendanceList) {
            if (attendance.getStudentName().toLowerCase().contains(keyword)) {
                searchResults.add(attendance);
            }
        }
        UI.display("Search Results");
        displayTable.printHeaderOfThree("Index", "Name of Student", "Status");
        for (Attendance attendance: searchResults) {
            displayTable.printBodyOfThree(index, attendance.getStudentName(), attendance.getStatus());
            index++;
        }
    }
}
