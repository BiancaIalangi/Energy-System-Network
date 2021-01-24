package fileio;

import interaction.Consumers;
import interaction.Distributor;
import interaction.Producer;

import java.util.ArrayList;

public final class InitialData {

    private ArrayList<Consumers> consumers;

    private ArrayList<Distributor> distributors;

    private ArrayList<Producer> producers;

    public ArrayList<Consumers> getConsumers() {
        return consumers;
    }

    public void setConsumers(final ArrayList<Consumers> consumers) {
        this.consumers = consumers;
    }

    public ArrayList<Distributor> getDistributors() {
        return distributors;
    }

    public void setDistributors(final ArrayList<Distributor> distributors) {
        this.distributors = distributors;
    }

    public ArrayList<Producer> getProducers() {
        return producers;
    }

    public void setProducers(final ArrayList<Producer> producers) {
        this.producers = producers;
    }

}
