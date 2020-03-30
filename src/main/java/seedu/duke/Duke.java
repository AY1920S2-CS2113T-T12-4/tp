package seedu.duke;

import java.io.IOException;
import java.util.logging.ConsoleHandler;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.LogManager;
import java.util.logging.Logger;

import seedu.StudentListCollection;
import seedu.command.Bye;
import seedu.command.interpreter.CommandInterpreter;
import seedu.command.Command;
import seedu.event.EventList;
import seedu.exception.DukeException;
import seedu.ui.UI;

public class Duke {
    public static final Logger logger = Logger.getLogger(Duke.class.getName());

    protected UI ui;
    protected CommandInterpreter interpreter;
    protected EventList eventList;
    public static StudentListCollection studentListCollection;

    public Duke() {
        setupLogger();

        ui = new UI();
        eventList = new EventList();  //TODO: new Storage().load()

        interpreter = new CommandInterpreter(eventList);
        studentListCollection = new StudentListCollection();
    }

    private void setupLogger() {
        LogManager.getLogManager().reset();
        logger.setLevel(Level.ALL); // print ALL log
        ConsoleHandler ch = new ConsoleHandler();
        ch.setLevel(Level.SEVERE);  // only print SEVERE log to console
        logger.addHandler(ch);
        try {
            FileHandler fh = new FileHandler("Duke.log", true);
            fh.setLevel(Level.FINE);    // print FINE log and more severe log to log file
            logger.addHandler(fh);
        } catch (IOException m) {
            logger.severe("File logger not working");
            System.out.println(m.getMessage());
        }
    }

    public void run() {
        ui.setUserName();
        Command command = null;
        do {
            ui.readUserInput();
            try {
                String input = ui.getUserInput().trim();
                if (input.isEmpty()) {
                    throw new DukeException("Please provide a valid command.");
                }

                command = interpreter.decideCommand(input);
                command.execute();
            } catch (DukeException m) {
                logger.log(Level.WARNING, "DukeException at main()");
                System.out.println(m.getMessage());
            }
        } while (isNotBye(command));
        ui.close();
    }

    boolean isNotBye(Command command) {
        return !(command instanceof Bye);
    }

    /**
     * Main entry-point for the java.duke.Duke application.
     */
    public static void main(String[] args) {
        new Duke().run();
    }
}
