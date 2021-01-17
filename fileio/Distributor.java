package fileio;

import payment.Contract;
import strategies.EnergyChoiceStrategyType;
import strategy.StrategyProducer;

import java.util.*;

public final class Distributor implements Observer {

    private int id;

    private int contractLength;

    private int initialBudget;

    private int initialInfrastructureCost;

    private int energyNeededKW;

    private int currKW;

    private String producerStrategy;

    private StrategyProducer strategyProducer;

    private int productionCost = 0;

    private int contractCost = 0;

    private List<Producer> producerList = new ArrayList<>();

    // how much a consumer has to pay
    private int payMonth;

    // profit per month
    private int profitMonth;

    // taxes that distributor has to pay per month
    private int costsMonth;

    private boolean bankrupt;

    // database of contracts (assigned to this distributor)
    private List<Contract> contracts = new ArrayList<>();

    public void applyStrategy(List<Producer> producers) {
        strategyProducer.strategyOrderProducer(producers, this);
    }

    public StrategyProducer getStrategyProducer() {
        return strategyProducer;
    }

    public void setStrategyProducer(StrategyProducer strategyProducer) {
        this.strategyProducer = strategyProducer;
    }

    public int getCurrKW() {
        return currKW;
    }

    public void addCurrKW(int addKW) {
        currKW += addKW;
    }

    public int getEnergyNeededKW() {
        return energyNeededKW;
    }

    public void setEnergyNeededKW(int energyNeededKW) {
        this.energyNeededKW = energyNeededKW;
    }

    public String getProducerStrategy() {
        return producerStrategy;
    }

    public void setProducerStrategy(String producerStrategy) {
        this.producerStrategy = producerStrategy;
    }

    public EnergyChoiceStrategyType setEnergyType() {
        if (producerStrategy.equals("GREEN"))
            return EnergyChoiceStrategyType.GREEN;
        if (producerStrategy.equals("PRICE"))
            return EnergyChoiceStrategyType.PRICE;
        return EnergyChoiceStrategyType.QUANTITY;
    }

    public int getId() {
        return id;
    }

    public void setId(final int id) {
        this.id = id;
    }

    public int getContractLength() {
        return contractLength;
    }

    public void setContractLength(final int contractLength) {
        this.contractLength = contractLength;
    }

    public int getInitialBudget() {
        return initialBudget;
    }

    public void setInitialBudget(final int initialBudget) {
        this.initialBudget = initialBudget;
    }

    public int getInitialInfrastructureCost() {
        return initialInfrastructureCost;
    }

    public void setInitialInfrastructureCost(final int initialInfrastructureCost) {
        this.initialInfrastructureCost = initialInfrastructureCost;
    }

    public int getPayMonth() {
        return payMonth;
    }

    public boolean isBankrupt() {
        return bankrupt;
    }

    public void setBankrupt(final boolean bankrupt) {
        this.bankrupt = bankrupt;
    }

    public List<Contract> getContracts() {
        return contracts;
    }

    public void setContracts(final List<Contract> contracts) {
        this.contracts = contracts;
    }

    /**
     * calculate the final price if there are no clients (per month)
     */
    public void setNoConsumerPrice() {
        payMonth = initialInfrastructureCost + productionCost + profitMonth;
    }

    /**
     * final price (per month)
     * @param round from numberOfTurns
     */
    public void setConsumersPrice(final int round) {
        //setContractCost();
        setProductionCost();
        profitMonth = (int) Math.round(Math.floor(0.2 * productionCost));
        // calculate the price if there are no consumers
        // or before rounds (which is round 0)
        if (contracts.size() == 0 || round == -1) {
            setNoConsumerPrice();
        } else {
            payMonth = (int) Math.round(Math.floor((float)(initialInfrastructureCost
                    / contracts.size()) + productionCost + profitMonth));
        }
    }

    /**
     * calculate the ProductionCost (per Month)
     */
    public void setCosts() {
        //if there are no consumers assigned to this distributor
        if (contracts.size() == 0) {
            costsMonth = 0;
        } else {
            // if there are consumers assigned to this distributor
            costsMonth = productionCost * contracts.size();
        }
    }

    /**
     * create a new contract and add in
     * distributor's database
     * @param contract the new contract that needs to
     *                 be added in distributor's database
     */
    public void addContract(final Contract contract) {
        contracts.add(contract);
    }

    /**
     * add to current budget, money earned
     * @param taxes paid by a consumer
     *              to this distributor
     */
    public void getPaid(final int taxes) {
        initialBudget += taxes;
    }

    /**
     * pay monthly expenses
     */
    public void payTaxes() {
        setCosts();
        initialBudget = initialBudget - initialInfrastructureCost;

        // update current budget
        initialBudget -= costsMonth;
    }

    public List<Producer> getProducerList() {
        return producerList;
    }

    public void setProducerList(List<Producer> producerList) {
        this.producerList = producerList;
    }

    public void addProducer(Producer producer) {
        producerList.add(producer);
    }

    public boolean existProducer (Producer p) {
        return producerList.contains(p);
    }

    public void setProductionCost() {
        double cost = 0;
        for (Producer producer : producerList) {
            cost += producer.getEnergyPerDistributor() * producer.getPriceKW();
        }
        productionCost = (int)Math.round(Math.floor((float)cost/10));
    }

    public void setContractCost() {
        contractCost = initialInfrastructureCost + productionCost;
    }

    public boolean needKW() {
        return currKW <= energyNeededKW;
    }

    public int getProductionCost() {
        return productionCost;
    }

    @Override
    public void update(Observable o, Object arg) {
        productionCost = 0;
        currKW = 0;
    }

    @Override
    public String toString() {
        return "Distributor{" +
                "id=" + id +
                ", initialInfrastructureCost=" + initialInfrastructureCost +
                '}';
    }
}
