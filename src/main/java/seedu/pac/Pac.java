package seedu.pac;

import java.io.IOException;
import java.util.logging.ConsoleHandler;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.LogManager;
import java.util.logging.Logger;

import seedu.student.StudentListCollection;
import seedu.command.Bye;
import seedu.command.interpreter.CommandInterpreter;
import seedu.command.Command;
import seedu.event.EventList;
import seedu.exception.PacException;
import seedu.storage.Storage;
import seedu.ui.UI;

public class Pac {
    public static final Logger logger = Logger.getLogger(Pac.class.getName());

    protected UI ui;
    protected CommandInterpreter interpreter;
    protected EventList eventList;
    public static StudentListCollection studentListCollection;
    protected Storage eventStorage;
    protected Storage studentListCollectionStorage;

    public Pac() throws PacException {
        setupLogger();

        ui = new UI();

        eventStorage = new Storage("./data/eventlist.txt");
        eventList = eventStorage.loadEventList();
        if (eventList == null) {
            eventList = new EventList();
        }

        studentListCollectionStorage = new Storage("./data/studentlist.txt");
        studentListCollection = studentListCollectionStorage.loadStudentListCollection();
        if (studentListCollection == null) {
            studentListCollection = new StudentListCollection();
        }

        interpreter = new CommandInterpreter(eventList);
    }

    private void setupLogger() {
        LogManager.getLogManager().reset();
        logger.setLevel(Level.ALL); // print ALL log
        ConsoleHandler ch = new ConsoleHandler();
        ch.setLevel(Level.SEVERE);  // only print SEVERE log to console
        logger.addHandler(ch);
        try {
            FileHandler fh = new FileHandler("Pac.log", true);
            fh.setLevel(Level.FINE);    // print FINE log and more severe log to log file
            logger.addHandler(fh);
        } catch (IOException m) {
            logger.severe("File logger not working");
            UI.display(m.getMessage());
        }
    }

    public void run() throws PacException {
        ui.setUserName();
        Command command = null;
        do {
            ui.readUserInput();
            try {
                String input = ui.getUserInput().trim();
                if (input.isEmpty()) {
                    throw new PacException("Please provide a valid command.");
                }

                command = interpreter.decideCommand(input);
                command.execute();
            } catch (PacException m) {
                logger.log(Level.WARNING, "PacException at Pac.run()");
                UI.display(m.getMessage());
            }
        } while (isNotBye(command));

        eventStorage.saveEventList(eventList);
        eventStorage.close();

        studentListCollectionStorage.saveStudentListCollection(studentListCollection);
        studentListCollectionStorage.close();

        ui.close();
    }

    boolean isNotBye(Command command) {
        return !(command instanceof Bye);
    }

    /**
     * Main entry-point for the java.Pac.Pac application.
     */
    public static void main(String[] args) {
        try {
            Pac pac = new Pac();
            pac.run();
        } catch (PacException m) {
            UI.display(m.getMessage());
        }
    }
}
