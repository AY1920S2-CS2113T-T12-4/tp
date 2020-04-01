package seedu.command.performance;

import seedu.command.Command;
import seedu.exception.PacException;
import seedu.performance.Performance;
import seedu.performance.PerformanceList;
import seedu.ui.UI;

public class DeletePerformance extends Command {
    PerformanceList performances;
    Performance performance;
    String eventName;
    UI ui;

    /**
     * Constructor for DeletePerformanceCommand. Takes in performance list and
     * the name of event that the list is under.
     * @param performances A PerformanceList of students.
     */
    public DeletePerformance(PerformanceList performances, String eventName) {
        this.ui = new UI();
        this.performances = performances;
        this.eventName = eventName;
    }

    private Performance getPerformance() throws PacException {
        String studentName = ui.getStudentName("delete his/her performance");
        Performance performance = performances.getPerformance(studentName);
        return performance;
    }

    private void deleteFromList() throws PacException {
        performance = getPerformance();
        performances.deletePerformance(performance, eventName);
    }

    @Override
    public void execute() throws PacException {
        deleteFromList();
    }
}