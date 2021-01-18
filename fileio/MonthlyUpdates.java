package fileio;
import java.util.ArrayList;
import java.util.Comparator;
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

    public void updateDistributor(List<Distributor> distributors, DistributorChanges dChange) {
        for (Distributor d : distributors) {
            if (d.getId() == dChange.getId()) {
                d.setInitialInfrastructureCost(dChange.getInfrastructureCost());
            }
        }
    }

    public void updateProducer(List<Producer> producers) {
        if (!producerChanges.isEmpty()) {
            List<Distributor> newListDistributor = new ArrayList<>();
            for (ProducerChanges pChanges : producerChanges) {
                for (Producer p : producers) {
                    if (p.getId() == pChanges.getId()) {
                        p.setEnergyPerDistributor(pChanges.getEnergyPerDistributor());
                        for (Distributor d : p.getDistributorList()) {
                            if (!newListDistributor.contains(d)) {
                                newListDistributor.add(d);
                            }
                        }
                    }
                }
            }
            Comparator<Distributor> comparator = Comparator.comparing(Distributor::getId);
            newListDistributor.sort(comparator);
            findAndNotify(newListDistributor, producers);
        }
    }

    public void findAndNotify(List<Distributor> distributors, List<Producer> producers) {
        for (Distributor d : distributors) {
            for (Producer p : d.getProducerList()) {
                p.getDistributorList().remove(d);
                p.setModification();
                p.deleteObserver(d);
            }
            d.getProducerList().clear();
            d.applyStrategy(producers);
        }
    }

    public void updateConsumerDistributor(List<Consumers> consumers, List<Distributor> distributors) {
        if (!newConsumers.isEmpty()) {
            consumers.addAll(newConsumers);
        }

        if (!distributorChanges.isEmpty()) {
            for (DistributorChanges d : distributorChanges) {
                updateDistributor(distributors, d);
            }
        }
    }

    public List<Consumers> getNewConsumers() {
        return newConsumers;
    }

    public void setNewConsumers(List<Consumers> newConsumers) {
        this.newConsumers = newConsumers;
    }

    public List<DistributorChanges> getDistributorChanges() {
        return distributorChanges;
    }

    public void setDistributorChanges(List<DistributorChanges> distributorChanges) {
        this.distributorChanges = distributorChanges;
    }

    public List<ProducerChanges> getProducerChanges() {
        return producerChanges;
    }

    public void setProducerChanges(List<ProducerChanges> producerChanges) {
        this.producerChanges = producerChanges;
    }
}
