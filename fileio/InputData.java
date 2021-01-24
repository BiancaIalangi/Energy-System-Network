package fileio;

import interaction.MonthlyUpdates;

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

    public InitialData getInitialData() {
        return initialData;
    }

    public List<MonthlyUpdates> getMonthlyUpdates() {
        return monthlyUpdates;
    }

}
