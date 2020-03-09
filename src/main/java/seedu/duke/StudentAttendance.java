package seedu.duke;

public class StudentAttendance extends Student {

    protected String description;
    protected String hasAttended;


    /**
     * The class that handles the attendance.
     *
     * @param description The description of the lesson.
     * @param hasAttended The status of the attendance.
     * @param studentName The name of the student.
     */
    public StudentAttendance(String studentName, String description, String hasAttended) {
        super(studentName);
        this.description = description;
        this.hasAttended = hasAttended;
    }

    /**
     * To retrieve the description of the lesson.
     *
     * @return The description of the lesson.
     */
    public String getDescription() {
        return description;
    }

    /**
     * To retrieve the status of the attendance.
     *
     * @return The status of the attendance.
     */
    public String getAttendance() {
        return hasAttended;
    }

    @Override
    public String toString() {
        return super.toString() + " " + description + " " + hasAttended;
    }

}
