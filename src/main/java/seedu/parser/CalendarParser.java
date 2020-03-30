package seedu.parser;

import seedu.exception.DukeException;

public class CalendarParser {

    public CalendarParser() {
    }

    public static String[] parseDescription(String parameters) throws DukeException {
        String[] tokens = parameters.split(" ");
        if (tokens.length != 2 || tokens[0].length() != 3 || tokens[1].length() != 8) {
            throw new DukeException("Please provide the semester and the academic year in this format: "
                    + "s/1 ay/19-20");
        }
        return tokens;
    }

    public static int parseAcademicYear(String[] academicYear, int year) throws DukeException {
        int calendarYear;
        for (String yr : academicYear) {
            if (yr.length() > 2 || yr.length() < 1) {
                throw new DukeException("Please provide a valid year in this format: ay/19-20");
            }
        }
        try {
            if (year == 1) {
                calendarYear = Integer.parseInt(academicYear[0]);
            } else {
                calendarYear = Integer.parseInt(academicYear[1]);
            }
        } catch (NumberFormatException e) {
            throw new DukeException("Please provide an integer");
        }
        return calendarYear;
    }


    public static int getSemester(String description) throws DukeException {
        String[] tokens = parseDescription(description);
        int semester;
        if (tokens[0].substring(0,2).equals("s/")) {
            try {
                semester = Integer.parseInt(tokens[0].substring(2));
            } catch (NumberFormatException e) {
                throw new DukeException("Please provide a integer");
            }
        } else {
            throw new DukeException("unknown flag");
        }
        return semester;
    }

    public static int getYear(String description, int year) throws DukeException {
        String[] tokens = parseDescription(description);
        int calendarYear;
        if (tokens[1].substring(0,3).equals("ay/")) {
            String[] academicYear = tokens[1].substring(3).split("-");
            try {
                if (academicYear.length != 2) {
                    throw new DukeException("Please provide two numbers for ay, eg. ay/18-19");
                } else if (Integer.parseInt(academicYear[1]) - Integer.parseInt(academicYear[0]) != 1) {
                    throw new DukeException("Please provide a valid ay, eg. ay/18-19");
                }
            } catch (NumberFormatException e) {
                throw new DukeException("Please provide two numbers for ay, eg. ay/18-19");
            }
            calendarYear = parseAcademicYear(academicYear, year);
        } else {
            throw new DukeException("Unknown flag");
        }
        return calendarYear;
    }
}
