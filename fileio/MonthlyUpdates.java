package fileio;

import payment.Payment;

import java.util.Comparator;
import java.util.List;
import java.util.Observer;

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

    public void findAndNotifyProducer(List<Producer> producers, ProducerChanges pChanges, List<Distributor> distributors) {
        for (Producer p : producers) {
            if (p.getId() == pChanges.getId()) {
                p.setEnergyPerDistributor(pChanges.getEnergyPerDistributor());
                for (Distributor d : distributors)
                    if (d.getProducerList().contains(p)) {
                        for (Producer producer1 : d.getProducerList()) {
                            producer1.getDistributorList().remove(d);
                        }
                        d.getProducerList().clear();
//                        for (Producer producer : producers) {
//                            if (producer.getDistributorList().contains(d))
//                                producer.getDistributorList().remove(d);

//                        }
                        p.setModification();
                    }
            }
        }
            for (Distributor distributor : distributors) {
                if (distributor.getProductionCost() == 0) {
                    distributor.applyStrategy(producers);
                }
            }

    }


    public void updateProducer(List<Producer> producers,  List<Distributor> distributors) {
        if (!producerChanges.isEmpty()) {
            for (ProducerChanges p : producerChanges) {
                findAndNotifyProducer(producers, p, distributors);
            }
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
