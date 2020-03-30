package seedu.attendance;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class AttendanceListTest {

    ArrayList<Attendance> attendanceList = new ArrayList<>();

    @Test
    void isEmpty() {
        assertEquals(true, attendanceList.isEmpty());
        attendanceList.add(new Attendance("John Doe", "Y"));
        assertEquals(false, attendanceList.isEmpty());
    }

    @Test
    void getAttendanceList() {
        assertEquals("[]", attendanceList.toString());
        attendanceList.add(new Attendance("John Doe", "Y"));
        assertEquals("[John Doe: Present]", attendanceList.toString());
    }
}
