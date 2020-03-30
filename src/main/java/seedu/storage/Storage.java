package seedu.storage;

import seedu.StudentList;
import seedu.StudentListCollection;
import seedu.event.Event;
import seedu.event.EventList;
import seedu.exception.DukeException;

public class Storage {
    protected FileIO fileIO;

    /**
     * Creates a Storage object that saves to the specified directory.
     * Note that an EventList object has to be passed for each relevant Storage function.
     * @param directory the filepath to save to
     * @throws DukeException if cannot initialise filepath
     */
    public Storage(String directory) throws DukeException {
        this.fileIO = new FileIO(directory);
    }

    /**
     * Load all events to EventList.
     * @return an EventList object with all events loaded
     * @throws DukeException if EOF is encountered
     */
    public EventList loadEventList() throws DukeException {
        EventList eventList = new EventList();
        String input = null;
        do {
            try {
                input = fileIO.read();
                Event newEvent = Event.parseStorable(input);
                eventList.add(newEvent);
            } catch (DukeException m) {
                if (m.getMessage().equals("FileIO: nothing to read anymore")) {
                    break;
                }
            }
        } while (!input.isBlank());

        return eventList;
    }

    /**
     * Save all events to Storage.
     * @param eventList the list of events to be stored
     * @throws DukeException if IOException occurs
     */
    public void saveEventList(EventList eventList) throws DukeException {
        for (Event event : eventList.list) {
            fileIO.write(event.toStorable() + System.lineSeparator());
        }
    }
    
    /**
     * Load all studentLists to a StudentListCollection object.
     * @return a StudentListCollection object with all studentLists loaded
     */
    public StudentListCollection loadStudentListCollection() {
        StudentListCollection studentListCollection = new StudentListCollection();
        String input = null;
        do {
            try {
                input = fileIO.read();
                StudentList newStudentList = StudentList.parseString(input);
                studentListCollection.add(newStudentList);
            } catch (DukeException m) {
                if (m.getMessage().equals("FileIO: nothing to read anymore")) {
                    break;
                }
            }
        } while (!input.isBlank());

        return studentListCollection;
    }

    /**
     * Save all studentLists to Storage.
     * @param studentListCollection the list of studentlLsts to be stored
     * @throws DukeException if IOException occurs
     */
    public void saveStudentListCollection(StudentListCollection studentListCollection) 
        throws DukeException {
        fileIO.write(studentListCollection.toString());
    }

    /**
     * Close every opened objects.
     * @throws DukeException if IOException occurs
     */
    public void close() throws DukeException {
        fileIO.close();
    }
}
