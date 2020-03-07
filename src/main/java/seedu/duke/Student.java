package seedu.duke;

public class Student {

    protected static String studentName;

    public Student(String studentName) {
        this.studentName = studentName;
    }

    public static String getStudentName() {
        return studentName;
    }
}
