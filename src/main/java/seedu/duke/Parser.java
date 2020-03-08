package seedu.duke;

/**
 * Parser to read String words.
 */
public class Parser {

    /**
     * Get Command word from userInput.
     *
     * @param userInput The userInput read by Ui.
     * @return The Command word.
     */

    public static String getCommandWord(String userInput) {
        return userInput.strip().split(" ")[0];
    }

    /**
     * Get index from userInput.
     *
     * @param userInput The userInput read by Ui.
     * @return The index.
     * @throws DukeException If userInput is undefined.
     */

    public static int getIndex(String userInput) throws DukeException {
        try {
            int index = Integer.parseInt(userInput.replaceAll("\\D+", ""));
            return index - 1;
        } catch (NumberFormatException e) {
            throw new DukeException(ErrorMessage.INVALID_FORMAT);
        }
    }

    /**
     * Get word from userInput.
     *
     * @param userInput The userInput read by Ui.
     * @return The word.
     * @throws DukeException If userInput is undefined.
     */

    public static String getWord(String userInput) throws DukeException {
        try {
            return userInput.strip().split(" ")[1];
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new DukeException(ErrorMessage.INVALID_FORMAT);
        }
    }


    /**
     * Create a student object.
     *
     * @param userInput The userInput from Ui.
     * @return Student Object.
     * @throws DukeException If the userInput is undefined.
     */

    public static Attendance createStudent(String userInput) throws DukeException {
        int indexOfDescription = userInput.indexOf("c/");
        int indexOfAttendance = userInput.indexOf("p/");
        int indexOfName = userInput.indexOf("n/");

        String description;
        String attendance;
        if (indexOfDescription == -1) {
            throw new DukeException("DESCRIPTION ERROR");
        }
        if (indexOfName == -1) {
            throw new DukeException("NO NAME");
        }

        if (indexOfAttendance == -1) {
            description = userInput.substring(userInput.lastIndexOf("c/") + 2, userInput.indexOf("n/")).strip();
            attendance = "NO";

        } else {
            description = userInput.substring(userInput.lastIndexOf("c/") + 2, userInput.indexOf("p/")).strip();
            attendance = userInput.substring(userInput.lastIndexOf("p/") + 2, userInput.indexOf("n/")).strip();
        }
        String name = userInput.substring(userInput.lastIndexOf("n/") + 2).strip();
        return new Attendance(name,description,attendance);
    }
}