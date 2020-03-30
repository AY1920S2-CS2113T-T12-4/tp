package seedu;

import java.util.ArrayList;

public class StudentListCollection extends ArrayList<StudentList> {

    @Override
    public String toString() {
        StringBuilder data = new StringBuilder();
        for (int i = 0; i < this.size(); ++i) {
            data.append(this.get(i).toString() + System.lineSeparator());
        }
        return data.toString();
    }
}
