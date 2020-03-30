package seedu.duke;

import org.junit.jupiter.api.Test;
import seedu.command.Bye;
import seedu.exception.DukeException;

import static org.junit.jupiter.api.Assertions.assertFalse;

class DukeTest {
    Duke duke;

    public DukeTest() {
        try {
            duke = new Duke();
        } catch (DukeException m) {
            System.out.println(m.getMessage());
        }
    }

    @Test
    void checkBye() {
        assertFalse(duke.isNotBye(new Bye()));
    }
}