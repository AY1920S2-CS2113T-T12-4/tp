package seedu.command;

import seedu.StudentList;
import seedu.command.student.AddStudent;
import seedu.command.student.ListStudent;
import seedu.event.EventList;
import seedu.exception.DukeException;
import seedu.ui.UI;

public class StudentCommandInterpreter extends CommandInterpreter {
    StudentList studentList;
    UI ui;

    public StudentCommandInterpreter(EventList eventList) {
        super(eventList);
        this.ui = new UI();
    }

    public Command decideCommand(String commandDescription) throws DukeException {
        String commandType = getFirstWord(commandDescription);
        String commandParameters = getSubsequentWords(commandDescription);
        switch (commandType) {
            case "add":
                return new AddStudent(studentList);
            case "list":
                return new ListStudent();
            default:
                throw new DukeException("Performance: Unknown command.");
        }
    }


}
