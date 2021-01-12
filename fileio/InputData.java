package fileio;

import java.util.List;

public final class InputData {

    /**
     * number of rounds
     */
    private int numberOfTurns;

    /**
     * contains a list with consumers and
     * a list with distributors
     */
    private InitialData initialData;

    /**
     * contains the new consumers and updates
     * from distributors to their prices
     */
    private List<MonthlyUpdates> monthlyUpdates;

    public int getNumberOfTurns() {
        return numberOfTurns;
    }

    public void setNumberOfTurns(final int numberOfTurns) {
        this.numberOfTurns = numberOfTurns;
    }

    public InitialData getInitialData() {
        return initialData;
    }

    public void setInitialData(final InitialData initialData) {
        this.initialData = initialData;
    }

    public List<MonthlyUpdates> getMonthlyUpdates() {
        return monthlyUpdates;
    }

    public void setMonthlyUpdates(final List<MonthlyUpdates> monthlyUpdates) {
        this.monthlyUpdates = monthlyUpdates;
    }
}
