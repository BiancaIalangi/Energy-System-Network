package fileio;

import java.util.List;

public final class MonthlyUpdates {

    /**
     * new consumers from a single round
     */
    private List<Consumers> newConsumers;

    /**
     * updates at prices from distributors from a single round
     */
    private List<CostsChanges> costsChanges;

    public List<Consumers> getNewConsumers() {
        return newConsumers;
    }

    public void setNewConsumers(final List<Consumers> newConsumers) {
        this.newConsumers = newConsumers;
    }

    public List<CostsChanges> getCostsChanges() {
        return costsChanges;
    }

    public void setCostsChanges(final List<CostsChanges> costsChanges) {
        this.costsChanges = costsChanges;
    }
}
