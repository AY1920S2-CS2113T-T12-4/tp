package seedu.command;


import seedu.command.event.EditDateTime;
import seedu.command.event.EditName;
import seedu.command.event.ListSeminar;
import seedu.command.event.AddEvent;
import seedu.command.event.EditVenue;
import seedu.command.event.EditEvent;
import seedu.command.event.DeleteEvent;
import seedu.event.Seminar;
import seedu.event.Task;
import seedu.exception.DukeException;
import seedu.event.TaskList;
import seedu.parser.EventParser;

public class SeminarCommandInterpreter extends CommandInterpreter {
    protected EventParser eventParser;

    public SeminarCommandInterpreter(TaskList taskList) {
        super(taskList);
        this.eventParser = new EventParser();
    }

    @Override
    public Command decideCommand(String commandDescription) throws DukeException {
        Command command = null;
        Seminar seminar;
        int index;
        String name;
        String datetime;
        String venue;

        String commandType = getFirstWord(commandDescription);
        String commandParameters = getSubsequentWords(commandDescription);
        assert commandType == " " : "Event: Unknown command";
        assert commandType == " " : "Event: Command is null";
        switch (commandType) {
        case "add":
            seminar = eventParser.parseSeminar(commandParameters);
            command = new AddEvent(seminar, this.taskList);
            break;
        case "editname":
            index = eventParser.parseIndex(commandParameters);
            name = eventParser.parseEventName(commandParameters);
            command = new EditName(index, name, this.taskList);
            break;
        case "editdatetime":
            index = eventParser.parseIndex(commandParameters);
            datetime = eventParser.parseEventDateTime(commandParameters);
            command = new EditDateTime(index, datetime, this.taskList);
            break;
        case "editvenue":
            index = eventParser.parseIndex(commandParameters);
            venue = eventParser.parseVenue(commandParameters);
            command = new EditVenue(index, venue, this.taskList);
            break;
        case "editevent":
            index = eventParser.parseIndex(commandParameters);
            seminar = eventParser.parseSeminar(commandParameters);
            command = new EditEvent(index, seminar, this.taskList);
            break;
        case "delete":
            index = eventParser.parseIndex(commandParameters);
            command = new DeleteEvent(index, this.taskList);
            break;
        case "list":
            command = new ListSeminar(this.taskList);
            break;
        default:
            throw new DukeException("Event: Unknown command");
        }

        if (command == null) {
            throw new DukeException("Event: Command is null");
        }
        return command;
    }
}
