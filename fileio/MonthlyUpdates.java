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
    private List<DistributorChanges> distributorChanges;

    private List<ProducerChanges> producerChanges;

    public List<ProducerChanges> getProducerChanges() {
        return producerChanges;
    }

    public void setProducerChanges(List<ProducerChanges> producerChanges) {
        this.producerChanges = producerChanges;
    }

    public List<Consumers> getNewConsumers() {
        return newConsumers;
    }

    public void setNewConsumers(final List<Consumers> newConsumers) {
        this.newConsumers = newConsumers;
    }

    public List<DistributorChanges> getDistributorChanges() {
        return distributorChanges;
    }

    public void setDistributorChanges(List<DistributorChanges> distributorChanges) {
        this.distributorChanges = distributorChanges;
    }
}
