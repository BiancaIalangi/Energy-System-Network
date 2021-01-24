package output;

import payment.Contract;
import strategies.EnergyChoiceStrategyType;

import java.util.ArrayList;
import java.util.List;

public final class OutputDistributor {

    private final int id;

    private final int energyNeededKW;

    private final int contractCost;

    private final int budget;

    private final boolean isBankrupt;

    private final EnergyChoiceStrategyType producerStrategy;

    private final List<OutputContract> contracts = new ArrayList<>();

    public OutputDistributor(int id, int energyNeededKW, int contractCost, int budget,
                             boolean isBankrupt, EnergyChoiceStrategyType producerStrategy) {
        this.id = id;
        this.energyNeededKW = energyNeededKW;
        this.contractCost = contractCost;
        this.budget = budget;
        this.isBankrupt = isBankrupt;
        this.producerStrategy = producerStrategy;
    }

    public int getId() {
        return id;
    }

    public int getBudget() {
        return budget;
    }

    public List<OutputContract> getContracts() {
        return contracts;
    }

    public int getEnergyNeededKW() {
        return energyNeededKW;
    }

    public int getContractCost() {
        return contractCost;
    }

    public EnergyChoiceStrategyType getProducerStrategy() {
        return producerStrategy;
    }

    public boolean getIsBankrupt() {
        return isBankrupt;
    }

    /**
     * create output for contracts to write in json
     * @param c contract from input
     */
    public void addPaymentContract(final Contract c) {
        OutputContract contract = new OutputContract(c.getConsumer().getId(),
                c.getPrice(), c.getRemainedContractMonths());
        this.contracts.add(contract);
    }
}
