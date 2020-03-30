package seedu.storage;

import seedu.exception.DukeException;
import seedu.ui.UI;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class FileIO {
    private FileReader fileToReadFrom;
    private FileWriter fileToWriteTo;
    private Scanner scanner;
    private File file;

    /**
     * Open files to be modified, and scanner to read from file.
     * @param directory the file to be opened
     * @throws DukeException if cannot initialise file
     */
    public FileIO(String directory) throws DukeException {
        this.file = open(directory);

        try {
            this.fileToReadFrom = new FileReader(file);
        } catch (IOException e) {
            throw new DukeException("FileIO: cannot initialise file");
        }

        this.scanner = new Scanner(fileToReadFrom);
    }

    /**
     * Opens a file, creating its relevant directory if necessary.
     * @param directory the abstract pathname of the file
     * @return a File object pointing to the file
     * @throws DukeException if directory already exists while creating it
     */
    protected File open(String directory) throws DukeException {
        File f = new File(directory);
        ensurePathExist(f);
        return f;
    }

    /**
     * Checks if the file denoted by this abstract pathname exists.
     * If it does not exist, create directories until that path.
     * @param f the abstract pathname
     * @throws DukeException if directory already exists while creating it
     */
    private void ensurePathExist(File f) throws DukeException {
        if (!f.exists()) {
            UI.display("Storage file not found.");
            try {
                new File(f.getParent()).mkdir();    // mkdir
                f.createNewFile();
                UI.display("A storage file is created.");
            } catch (IOException m) {
                throw new DukeException("FileIO: creating file that already exists");
            }
        }
    }

    // /**
    //  * Read in information that is required to reconstruct one Event
    //  * (which is three lines).
    //  * @return a String that consists of three lines
    //  * @throws DukeException if EOF is encountered
    //  */
    // public String readOneEvent() throws DukeException {
    //     return this.read() + this.read() + this.read();
    // }

    /**
     * Read a line from current file.
     * @return the next line
     * @throws DukeException if EOF is encountered
     */
    public String read() throws DukeException {
        if (!scanner.hasNext()) {
            throw new DukeException("FileIO: nothing to read anymore");
        }

        return scanner.nextLine();
    }

    /**
     * Write a string from start of file, replacing the content of file.
     * @param input the string to be written
     * @throws DukeException if {@code IOException} occurs
     */
    public void write(String input) throws DukeException {
        try {
            if (this.fileToWriteTo == null) {
                this.fileToWriteTo = new FileWriter(file);
            }
            fileToWriteTo.write(input);
        } catch (IOException m) {
            throw new DukeException("FileIO.write: " + m.getMessage());
        }
    }

    /**
     * Close everything in FileIO.
     * @throws DukeException if IOException occurs
     */
    public void close() throws DukeException {
        try {
            this.fileToWriteTo.close();
            this.fileToReadFrom.close();
            this.scanner.close();
        } catch (IOException m) {
            throw new DukeException("FileIO.close: " + m.getMessage());
        }
    }
}
