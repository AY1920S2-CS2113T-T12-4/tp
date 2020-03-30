package seedu.command.calendar;

import seedu.event.Event;
import seedu.event.EventList;
import seedu.event.Seminar;
import seedu.exception.DukeException;
import seedu.ui.DisplayTable;
import seedu.ui.UI;
import java.util.ArrayList;

public class CalendarList {
    private static final int NO_OF_MONTHS = 6;
    private UI ui;

    public CalendarList() {
        this.ui = new UI();
    }

    /**
     * Separates events from eventList into diff lists corresponding to its semester, year and month,
     * displaying the events in a calendar form.
     *
     * @param eventList List of all the events.
     * @param year Year which the user input.
     * @param semester Semester which the user input.
     * @throws DukeException If list is empty.
     */
    public void showEvents(EventList eventList, int year, int semester) throws DukeException {
        ArrayList<Event> semesterList = getSemesterEvents(eventList, semester);
        ArrayList<Event> yearList = getAcademicYearEvents(semesterList, year);
        ArrayList<ArrayList<String>> monthList = getMonthEvents(yearList);
        displayCalendar(monthList, year, semester);
    }

    /**
     * Gets events that fall under the correct semester. ie. s/1 would mean events from semester 1.
     *
     * @param eventList List of all the events.
     * @param semester Semester that user input.
     * @return Events under the required semester.
     * @throws DukeException If no events can be found.
     */
    private ArrayList<Event> getSemesterEvents(EventList eventList, int semester) throws DukeException {
        ArrayList<Event> list;
        switch (semester) {
        case 1:
            list = getSemesterOneEvents(eventList);
            break;
        case 2:
            list = getSemesterTwoEvents(eventList);
            break;
        default:
            throw new DukeException("Could not find any events");
        }
        return list;
    }

    /**
     * Gets semester one events only.
     *
     * @param eventList List of all the events.
     * @return list of events that fall under semester 1, ie. jul - dec
     * @throws DukeException If unable to find the index of the event.
     */
    private ArrayList<Event> getSemesterOneEvents(EventList eventList) throws DukeException {
        ArrayList<Event> list = new ArrayList<>();
        for (int i = 0; i < eventList.list.size(); i++) {
            Event event = eventList.find(i);
            if (event.getMonth() > 6) {
                list.add(event);
            }
        }
        return list;
    }

    /**
     * Gets semester two events only.
     *
     * @param eventList List of all the events.
     * @return list of events that fall under semester 2, ie. jan - jun.
     * @throws DukeException If unable to find the index of the event.
     */
    private ArrayList<Event> getSemesterTwoEvents(EventList eventList) throws DukeException {
        ArrayList<Event> list = new ArrayList<>();
        for (int i = 0; i < eventList.list.size(); i++) {
            Event event = eventList.find(i);
            if (event.getMonth() > 0 && event.getMonth() < 7) {
                list.add(event);
            }
        }
        return list;
    }


    /**
     * Gets events that fall under the correct year. i.e sem 1 of ay/18-19 is in 2018.
     *
     * @param semesterList List of events that fall under the correct semester.
     * @param year Year that the user wants to see the events under.
     * @return List with events that fall under the correct year.
     */
    private ArrayList<Event> getAcademicYearEvents(ArrayList<Event> semesterList, int year) throws DukeException {
        ArrayList<Event> yearList = new ArrayList<>();
        for (Event event : semesterList) {
            if (event.getYear().equals(year)) {
                yearList.add(event);
            }
        }
        if (yearList.isEmpty()) {
            throw new DukeException("Unable to find any events for this time period.");
        }
        return yearList;
    }

    /**
     * Gets events and sorts them into their correct months using a 2d ArrayList.
     *
     *
     * @param yearList list events that falls under the correct year.
     * @return 2d ArrayList monthList that has all the events sorted into the correct month.
     * @throws DukeException If the month cannot be found.
     */
    private ArrayList<ArrayList<String>> getMonthEvents(ArrayList<Event> yearList)
            throws DukeException {

        ArrayList<ArrayList<String>> monthList = initializeMonthList();
        for (Event event : yearList) {
            int month = event.getMonth();
            String description = getEventDescription(event);
            switch (month) {
            case 1:
            case 7:
                monthList.get(0).add(description);
                break;
            case 2:
            case 8:
                monthList.get(1).add(description);
                break;
            case 3:
            case 9:
                monthList.get(2).add(description);
                break;
            case 4:
            case 10:
                monthList.get(3).add(description);
                break;
            case 5:
            case 11:
                monthList.get(4).add(description);
                break;
            case 6:
            case 12:
                monthList.get(5).add(description);
                break;
            default:
                throw new DukeException("Month not found");
            }
        }
        return monthList;
    }

    /**
     * Gets event description with the day, eventType and the eventName, also makes sure the
     * length of the string is less than 19 characters.
     *
     * @param event event from the eventList
     * @return description of the event
     */
    private String getEventDescription(Event event) {
        String day = getDayDescription(event);
        String typeOfEvent = getEventType(event);
        String description = day + " " + typeOfEvent + event.getName();
        if (description.length() > 18) {
            return description.substring(0, 16) + "...";
        }
        return description;
    }

    /**
     * Gets description of the day as a String.
     *
     * @param event event of the day to be found.
     * @return The day's description as a String.
     */
    private String getDayDescription(Event event) {
        int day = event.getDay();
        String dayDescription = Integer.toString(event.getDay());
        switch (day) {
        case 1:
        case 21:
        case 31:
            dayDescription += "st";
            break;
        case 2:
        case 22:
            dayDescription += "nd";
            break;
        case 3:
        case 23:
            dayDescription += "rd";
            break;
        default:
            dayDescription += "th";
        }
        return dayDescription;
    }

    /**
     * Gets type of the event as a String.
     *
     * @param event An event from the eventList.
     * @return String containing type of event.
     */
    private String getEventType(Event event) {
        if (event instanceof Seminar) {
            return "[S]: ";
        } else {
            return "[E]: ";
        }
    }

    private ArrayList<ArrayList<String>> initializeMonthList() {
        ArrayList<ArrayList<String>> list = new ArrayList<>(NO_OF_MONTHS);
        for (int k = 0; k < NO_OF_MONTHS; k++) {
            list.add(new ArrayList<>());
        }
        return list;
    }

    private void displayCalendar(ArrayList<ArrayList<String>> monthList, int year, int semester) {
        if (semester == 1) {
            ui.printCalendarHeader(year, year + 1, semester);
        } else {
            ui.printCalendarHeader(year - 1, year, semester);
        }
        printMonths(monthList);
    }

    /**
     * Prints monthList after making it a complete 2d ArrayList, ie, equal number of rows and columns.
     *
     * @param monthList 2d ArrayList that does not have a equal number of rows and columns.
     */
    private void printMonths(ArrayList<ArrayList<String>> monthList) {
        ArrayList<String> eventDescriptionList = initializeDescriptionList();
        int maxNumberOfEvents = getMaxNumberOfEvents(monthList);
        monthList = make2DArray(monthList, maxNumberOfEvents);
        displayEvents(eventDescriptionList, monthList, maxNumberOfEvents);

    }

    /**
     * Takes events from the 2d ArrayList and puts it in a 1d ArrayList for as many times as the max number of events
     * in a particular month to be printed.
     *
     * @param eventDescriptionList Empty 1d ArrayList for it to be filled with events corresponding to the 6 months
     * @param monthList 2d ArrayList, containing all the events corresponding to each month.
     * @param maxNumberOfEvents Max number of events in a particular month which determines the loop count.
     */
    private void displayEvents(ArrayList<String> eventDescriptionList,
                             ArrayList<ArrayList<String>> monthList, int maxNumberOfEvents) {
        for (int k = 0; k < maxNumberOfEvents; k++) {
            for (int j = 0; j < monthList.size(); j++) {
                if (!monthList.get(j).get(k).equals("")) {
                    eventDescriptionList.add(j, monthList.get(j).get(k));
                }
            }
            DisplayTable.printBodyOfSix(eventDescriptionList);
            eventDescriptionList = removePreviousElements(eventDescriptionList);
        }
    }

    /**
     * Gets the maximum number of events in any particular month.
     *
     * @param list 2d ArrayList of all the events corresponding to its month.
     * @return Integer of maximum number of events.
     */
    private int getMaxNumberOfEvents(ArrayList<ArrayList<String>> list) {
        int maxNumberOfEvents = 0;
        for (ArrayList<String> events : list) {
            if (events.size() > maxNumberOfEvents) {
                maxNumberOfEvents = events.size();
            }
        }
        return maxNumberOfEvents;
    }

    private ArrayList<String> initializeDescriptionList() {
        ArrayList<String> list = new ArrayList<>(6);
        for (int h = 0; h < NO_OF_MONTHS; h++) {
            list.add("");
        }
        return list;
    }

    private ArrayList<String> removePreviousElements(ArrayList<String> list) {
        for (int h = 0; h < NO_OF_MONTHS; h++) {
            list.replaceAll(s -> "");
        }
        return list;
    }

    private ArrayList<ArrayList<String>> make2DArray(ArrayList<ArrayList<String>> list, int maxNumberOfEvents) {
        for (ArrayList<String> strings : list) {
            if (strings.size() < maxNumberOfEvents) {
                int size = strings.size();
                for (int j = 0; j < maxNumberOfEvents - size; j++) {
                    strings.add("");
                }
            }
        }
        return list;
    }

}
