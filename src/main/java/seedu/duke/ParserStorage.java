package seedu.duke;

import static seedu.duke.ErrorMessage.CORRUPTED_TASK;

/**
 * Parser for Storage related operations.
 */
public class ParserStorage {

    /**
     * Parses a task from String format back to task.
     *
     * @param line description of the task.
     * @return The corresponding task object.
     * @throws DukeException If the line is corrupted.
     */

    public static Student createTaskFromStorage(String line) throws DukeException {
        String[] studentParts = line.split("\\|");
        try {
            String studentName = studentParts[2].strip();
            String studentAttendance = studentParts[1].strip();
            String description = studentParts[0].strip();
            Attendance student = new Attendance(studentName,studentAttendance,description);
            return student;
        } catch (Exception e) {
            throw new DukeException(CORRUPTED_TASK);
        }
    }

    /**
     * Parses a task from task to String format.
     *
     * @param student The task.
     * @return The corresponding String format of the task object.
     */

    public static String toStorageString(Student student) throws DukeException {
        if (student instanceof Attendance) {
            return student.getStudentName() + " | " + ((Attendance) student).getAttendance()
                    + " | " + ((Attendance) student).getDescription();
        }
        throw new DukeException(CORRUPTED_TASK);
    }

}
