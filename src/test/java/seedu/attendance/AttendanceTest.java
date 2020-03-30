package seedu.attendance;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class AttendanceTest {

    Attendance attendanceTestUnit = new Attendance("John Doe", "Y");

    @Test
    void getStudentName() {
        assertEquals("John Doe", attendanceTestUnit.getStudentName());
    }

    @Test
    void getAttendanceStatus() {
        assertEquals("Present", attendanceTestUnit.getAttendanceStatus());
    }

    @Test
    void getAttendance() {
        assertEquals("John Doe: Present", attendanceTestUnit.toString());
    }
}
