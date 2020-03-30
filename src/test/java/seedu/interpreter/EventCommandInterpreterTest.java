package seedu.interpreter;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import seedu.command.Command;
import seedu.command.event.AddEvent;
import seedu.command.event.DeleteEvent;
import seedu.command.event.EditDateTime;
import seedu.command.event.EditEvent;
import seedu.command.event.EditName;
import seedu.command.event.EditVenue;
import seedu.command.event.ListEvent;
import seedu.command.interpreter.EventCommandInterpreter;
import seedu.event.EventList;
import seedu.exception.DukeException;

public class EventCommandInterpreterTest {
    EventCommandInterpreter eci;
    Command command;

    public EventCommandInterpreterTest() {
        eci = new EventCommandInterpreter(new EventList());
    }

    @Test
    void add() throws DukeException {
        assertTrue(eci.decideCommand("add n/hello") instanceof AddEvent);
        assertThrows(DukeException.class, () -> eci.decideCommand("add n/1 n/2"));
    }

    @Test
    void editName() throws DukeException {
        assertTrue(eci.decideCommand("editname i/1 n/new name") instanceof EditName);
        assertThrows(DukeException.class, () -> eci.decideCommand("editname n/new name"));
        assertThrows(DukeException.class, () -> eci.decideCommand("editname i/-5"));
        assertThrows(DukeException.class, () -> eci.decideCommand("editname i/0"));
        assertThrows(DukeException.class, () -> eci.decideCommand("editname i/1 i/2"));
        assertThrows(DukeException.class, () -> eci.decideCommand("editname i/10000 2"));        
    }

    @Test
    void editDateTime() throws DukeException {
        assertTrue(eci.decideCommand("editdatetime i/1 d/2020-11-11 t/1234") instanceof EditDateTime);
        assertThrows(DukeException.class, () -> eci.decideCommand("editdatetime d/date t/time"));
        assertThrows(DukeException.class, () -> eci.decideCommand("editdatetime i/1 d/date"));
        assertThrows(DukeException.class, () -> eci.decideCommand("editdatetime i/1 t/time"));
        assertThrows(DukeException.class, () -> eci.decideCommand("editdatetime d/date"));
        assertThrows(DukeException.class, () -> eci.decideCommand("editdatetime t/time"));
        assertThrows(DukeException.class, () -> eci.decideCommand("editdatetime i/-5"));
        assertThrows(DukeException.class, () -> eci.decideCommand("editdatetime i/0"));
        assertThrows(DukeException.class, () -> eci.decideCommand("editdatetime i/1 i/2"));
        assertThrows(DukeException.class, () -> eci.decideCommand("editdatetime i/10000 2"));        
    }

    @Test
    void editVenue() throws DukeException {
        assertTrue(eci.decideCommand("editvenue i/1 v/new venue") instanceof EditVenue);
        assertThrows(DukeException.class, () -> eci.decideCommand("editvenue v/new venue"));
        assertThrows(DukeException.class, () -> eci.decideCommand("editvenue i/-5"));
        assertThrows(DukeException.class, () -> eci.decideCommand("editvenue i/0"));
        assertThrows(DukeException.class, () -> eci.decideCommand("editvenue i/1 i/2"));
        assertThrows(DukeException.class, () -> eci.decideCommand("editvenue i/10000 2"));        
    }

    @Test
    void editEvent() throws DukeException {
        assertTrue(eci.decideCommand("editevent i/1 n/new name v/new venue") instanceof EditEvent);
    }

    @Test
    void delete() throws DukeException {
        assertTrue(eci.decideCommand("delete i/1") instanceof DeleteEvent);
        assertThrows(DukeException.class, () -> eci.decideCommand("delete i/-5"));
        assertThrows(DukeException.class, () -> eci.decideCommand("delete i/0"));
        assertThrows(DukeException.class, () -> eci.decideCommand("delete i/1 i/2"));
        assertThrows(DukeException.class, () -> eci.decideCommand("delete i/10000 2"));
    }

    @Test
    void list() throws DukeException {
        assertTrue(eci.decideCommand("list") instanceof ListEvent);
    }

    @Test
    void unknown() throws DukeException {
        assertThrows(DukeException.class, () -> eci.decideCommand(""));
        assertThrows(DukeException.class, () -> eci.decideCommand("..."));
    }

    @Test
    void missingArgument() throws DukeException {
        assertThrows(DukeException.class, () -> eci.decideCommand("add"));
        assertThrows(DukeException.class, () -> eci.decideCommand("editname"));
        assertThrows(DukeException.class, () -> eci.decideCommand("editdatetime"));
        assertThrows(DukeException.class, () -> eci.decideCommand("editvenue"));
        assertThrows(DukeException.class, () -> eci.decideCommand("editevent"));
        assertThrows(DukeException.class, () -> eci.decideCommand("delete"));
    }
}