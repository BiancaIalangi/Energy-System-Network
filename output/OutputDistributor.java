package output;

import payment.Contract;
import strategies.EnergyChoiceStrategyType;

import java.util.ArrayList;
import java.util.List;

public final class OutputDistributor {

    private int id;

    private int energyNeededKW;

    private int contractCost;

    private int budget;

    private boolean isBankrupt;

    private EnergyChoiceStrategyType producerStrategy;

    private List<OutputContract> contracts = new ArrayList<>();

    /**
     * create output for contracts to write in json
     * @param c contract from input
     */
    public void addPaymentContract(final Contract c) {
        OutputContract contract = new OutputContract(c.getConsumer().getId(),
                c.getPrice(), c.getRemainedContractMonths());
        this.contracts.add(contract);
    }

    public int getId() {
        return id;
    }

    public void setId(final int id) {
        this.id = id;
    }

    public int getBudget() {
        return budget;
    }

    public void setBudget(final int budget) {
        this.budget = budget;
    }

    public List<OutputContract> getContracts() {
        return contracts;
    }

    public void setContracts(final List<OutputContract> contracts) {
        this.contracts = contracts;
    }

    public int getEnergyNeededKW() {
        return energyNeededKW;
    }

    public void setEnergyNeededKW(int energyNeededKW) {
        this.energyNeededKW = energyNeededKW;
    }

    public int getContractCost() {
        return contractCost;
    }

    public void setContractCost(int contractCost) {
        this.contractCost = contractCost;
    }

    public EnergyChoiceStrategyType getProducerStrategy() {
        return producerStrategy;
    }


    public boolean getIsBankrupt() {
        return isBankrupt;
    }

    public void setBankrupt(final boolean bankrupt) {
        this.isBankrupt = bankrupt;
    }

    public void setProducerStrategy(EnergyChoiceStrategyType producerStrategy) {
        this.producerStrategy = producerStrategy;
    }
}
