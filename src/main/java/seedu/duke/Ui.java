package seedu.duke;

import java.lang.reflect.Array;
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

    public String readCommand() {
        sc = new Scanner(System.in);
        return sc.nextLine().strip();
    }

    public void displayMarkAttendance(Student student) {
        System.out.println(student.getStudentName() + "Attended");
    }

    public void displayAttendance(Attendance student) {
        System.out.println(student.getStudentName() + "Already M");
    }
}
