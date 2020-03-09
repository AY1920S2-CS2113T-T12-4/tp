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
            String studentName = studentParts[0].strip();
            String description = studentParts[1].strip();
            String studentAttendance = studentParts[2].strip();
            StudentAttendance student = new StudentAttendance(studentName,description,studentAttendance);
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
        if (student instanceof StudentAttendance) {
            return student.getStudentName() + " | "
                    + ((StudentAttendance) student).getDescription() + " | "
                    + ((StudentAttendance) student).getAttendance();
        }
        throw new DukeException(CORRUPTED_TASK);
    }

}
