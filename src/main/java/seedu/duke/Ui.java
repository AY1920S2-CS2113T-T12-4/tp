package seedu.duke;

import java.util.ArrayList;
import java.util.Scanner;

public class Ui {

    private Scanner sc;

    public Ui() {
        sc = new Scanner(System.in);
    }

    public static final void displayError(String error) {
        System.out.println(error);
    }

    public static final void displayAddStudent() {
        System.out.println("Added");
    }

    public static void displayNumberOfStudents(ArrayList<Student> studentlist) {
        System.out.print(studentlist.size());
    }

    /**
     * Display the names of students in the namelist.
     *
     * @param studentlist The namelist of students.
     */
    public static void displayAttendanceList(ArrayList<Student> studentlist) {
        if (!studentlist.isEmpty()) {
            for (int i = 0; i < studentlist.size(); i++) {
                System.out.print("\t");
                System.out.print(i + 1);
                System.out.print(". ");
                System.out.println(studentlist.get(i));
            }
            displayNumberOfStudents(studentlist);
        } else {
            System.out.println("\tEMPTY!!");
        }
    }

    /**
     * Display the search results.
     *
     * @param resultlist The results from the search.
     */
    public static void displaySearchResults(ArrayList<Student> resultlist) {
        if (!resultlist.isEmpty()) {
            for (int i = 0; i < resultlist.size(); i++) {
                System.out.print("\t");
                System.out.print(i + 1);
                System.out.print(". ");
                System.out.println(resultlist.get(i));
            }
            displayNumberOfResultsFound(resultlist);
        } else {
            System.out.println("\tItem not found!!!");
        }
    }

    private static void displayNumberOfResultsFound(ArrayList<Student> resultlist) {
        System.out.println(resultlist.size());
    }

    public static void displayDeleted(Student student) {
        System.out.println("DELETED");
        System.out.println(Student.getStudentName());
    }

    public String readCommand() {
        sc = new Scanner(System.in);
        return sc.nextLine().strip();
    }

    public void displayMarkAttendance(Student student) {
        System.out.println(student.getStudentName() + "Attended");
    }

    public void displayAttendance(StudentAttendance student) {
        System.out.println(student.getStudentName() + "Already M");
    }
}
