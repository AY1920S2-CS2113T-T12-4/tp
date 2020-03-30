package seedu;

import seedu.ui.DisplayTable;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

/**
 * Class representing a studentList.
 */
public class StudentList {

    public ArrayList<String> studentList;
    public String listName;

    public StudentList(String listName) {
        this.listName = listName;
        studentList = new ArrayList<>();
    }

    /**
     * Adds a new student name to studentList.
     * @param studentName The name of the student.
     */
    public void addToList(String studentName) {
        studentList.add(studentName);
    }

    /**
     * Retrieves the existing studentList.
     * @return studentList
     */
    public ArrayList<String> getStudentList() {
        return studentList;
    }

    /**
     * Retrieves the listName.
     * @return listName
     */
    public String getListName() {
        return listName;
    }

    public void sortAscending() {
        Collections.sort(studentList);
    }

    /**
     * Display the existing student names in the studentList.
     */
    public void showList() {
        DisplayTable displayTable = new DisplayTable();
        displayTable.printHeaderOfTwo("List", listName);
        int index = 1;
        for (String name: studentList) {
            displayTable.printBodyOfTwo(index, name);
            index++;
        }

    }

    public static Comparator<StudentList> listNameComparator = new Comparator<StudentList>() {
        public int compare(StudentList s1, StudentList s2) {
            String listName1 = s1.getListName().toUpperCase();
            String listName2 = s2.getListName().toUpperCase();
            return listName1.compareTo(listName2);
        }
    };

    @Override
    public String toString() {
        String data = listName;
        for (String studentName : studentList) {
            data = data.concat("|" + studentName);
        }
        return data;
    }

    /**
     * Returns a studentList based on its String representation.
     * @param representation a String representation of a studentList
     * @return a studentList
     */
    public static StudentList parseString(String representation) {
        String[] tokens = representation.split("\\|");
        assert tokens.length > 0 : "Not a studentList";

        String listName = tokens[0];
        StudentList newStudentList = new StudentList(listName);
        for (int i = 1; i < tokens.length; ++i) {
            newStudentList.addToList(tokens[i]);
        }
        return newStudentList;
    }
}
