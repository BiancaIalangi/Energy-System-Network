package interaction;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public final class MonthlyUpdates {

    /**
     * new consumers from a single round
     */
    private List<Consumers> newConsumers;

    /**
     * updates distributors' price from a single round
     */
    private List<DistributorChanges> distributorChanges;

    /**
     * updates at energy per distributor from a single round
     */
    private List<ProducerChanges> producerChanges;

    public List<Consumers> getNewConsumers() {
        return newConsumers;
    }


    public List<DistributorChanges> getDistributorChanges() {
        return distributorChanges;
    }


    public List<ProducerChanges> getProducerChanges() {
        return producerChanges;
    }

    /**
     * apply the updates to the distributor that needs to be updated
     * @param distributors a list with all distributors
     * @param dChange the distributor that needs to be updated
     */
    public void updateDistributor(List<Distributor> distributors, DistributorChanges dChange) {
        for (Distributor d : distributors) {
            if (d.getId() == dChange.getId()) {
                d.setInitialInfrastructureCost(dChange.getInfrastructureCost());
            }
        }
    }

    /**
     * apply the updates to producers that need to be updated
     * @param producers a list with all producers
     */
    public void updateProducer(List<Producer> producers) {
        if (!producerChanges.isEmpty()) {
            // create a list with all distributors that need to update their
            // producer list
            List<Distributor> needUpdateDistributors = new ArrayList<>();
            for (ProducerChanges pChanges : producerChanges) {
                for (Producer p : producers) {
                    if (p.getId() == pChanges.getId()) {
                        // update producer
                        p.setEnergyPerDistributor(pChanges.getEnergyPerDistributor());
                        for (Distributor d : p.getDistributorList()) {
                            if (!needUpdateDistributors.contains(d)) {
                                needUpdateDistributors.add(d);
                            }
                        }
                    }
                }
            }
            needUpdateDistributors.sort(Comparator.comparing(Distributor::getId));
            findAndNotify(needUpdateDistributors, producers);
        }
    }

    /**
     * update producers' distributor list (just to producers that updated their
     * energy per distributor) and update distributors' producer list
     * @param distributors a list with all distributors
     * @param producers a list with all producers
     */
    public void findAndNotify(List<Distributor> distributors, List<Producer> producers) {
        for (Distributor d : distributors) {
            for (Producer p : d.getProducerList()) {
                // remove distributor d from all the producers he is assigned
                p.getDistributorList().remove(d);
                // notify the distributor he has no producers
                p.setModification();
                // Observer pattern; delete distributor d from Observer List
                p.deleteObserver(d);
            }
            // clear distributor's producer list
            d.getProducerList().clear();
            // distributor reapply the strategy to find the right producers
            d.applyStrategy(producers);
        }
    }

    /**
     * apply the updates to the distributor that needs to be updated
     * apply the updates to the consumer that needs to be updated
     * @param consumers a list with all consumers
     * @param distributors a list with all distributors
     */
    public void updateConsumerDistributor(List<Consumers> consumers,
                                          List<Distributor> distributors) {
        if (!newConsumers.isEmpty()) {
            consumers.addAll(newConsumers);
        }

        if (!distributorChanges.isEmpty()) {
            for (DistributorChanges d : distributorChanges) {
                updateDistributor(distributors, d);
            }
        }
    }
}
