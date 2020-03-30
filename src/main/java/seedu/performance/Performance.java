package seedu.performance;

import java.util.Comparator;

public class Performance {
    public String studentName;
    public String result;

    /**
     * Constructor for Performance.
     * @param studentName A string input by user, the name of student who scored
     *                      the result.
     * @param result      A string input by user, sets the result of the student.
     */
    public Performance(String studentName, String result) {
        this.result = result;
        this.studentName = studentName;
    }

    public String getStudent() {
        return studentName;
    }

    public String getResult() {
        return result;
    }

    /**
     * A comparator to sort the performance list by student name, in
     * alphabetical order.
     */
    public static Comparator<Performance> performanceListNameComparator = new Comparator<Performance>() {
        public int compare(Performance s1, Performance s2) {
            String listName1 = s1.getStudent().toUpperCase();
            String listName2 = s2.getStudent().toUpperCase();
            return listName1.compareTo(listName2);
        }
    };

    /**
     * A comparator to sort the performance list by student result, from
     * highest to lowest.
     */
    public static Comparator<Performance> performanceListGradeComparator = new Comparator<Performance>() {
        public int compare(Performance s1, Performance s2) {
            String listName1 = s1.getResult().toUpperCase();
            String listName2 = s2.getResult().toUpperCase();
            return listName1.compareTo(listName2);
        }
    };

    @Override
    public String toString() {
        return studentName + ": " + result;
    }
}
