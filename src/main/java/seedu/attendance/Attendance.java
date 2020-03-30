package seedu.attendance;

import java.util.Comparator;

/**
 * Class representing attendance of a student.
 */
public class Attendance {

    protected String studentName;
    protected String isPresent;

    public Attendance(String studentName, String isPresent) {
        this.studentName = studentName;
        this.isPresent = "Absent";
        if (isPresent.toUpperCase().equals("Y")) {
            this.isPresent = "Present";
        }
    }

    /**
     * Retrieves the name of the student.
     * @return studentName the name of the student.
     */
    public String getStudentName() {
        return studentName;
    }

    /**
     * Retrieves the attendance status of the student.
     * @return isPresent the attendance status of the student.
     */
    public String getAttendanceStatus() {
        return isPresent;
    }

    @Override
    public String toString() {
        return studentName + ": " + isPresent;
    }

    public static Comparator<Attendance> attendanceStatusComparator = new Comparator<Attendance>() {
        public int compare(Attendance s1, Attendance s2) {
            String listName1 = s1.getAttendanceStatus().toUpperCase();
            String listName2 = s2.getAttendanceStatus().toUpperCase();
            return listName1.compareTo(listName2);
        }
    };

    public static Comparator<Attendance> attendanceListNameComparator = new Comparator<Attendance>() {
        public int compare(Attendance s1, Attendance s2) {
            String listName1 = s1.getStudentName().toUpperCase();
            String listName2 = s2.getStudentName().toUpperCase();
            return listName1.compareTo(listName2);
        }
    };
}
