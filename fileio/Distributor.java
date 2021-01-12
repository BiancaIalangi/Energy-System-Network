package fileio;

import payment.Contract;
import java.util.ArrayList;
import java.util.List;

public final class Distributor {

    private int id;

    private int contractLength;

    private int initialBudget;

    private int initialInfrastructureCost;

    private int initialProductionCost;

    // how much a consumer has to pay
    private int payMonth;

    // profit per month
    private int profitMonth;

    // taxes that distributor has to pay per month
    private int costsMonth;

    private boolean bankrupt;

    // database of contracts (assigned to this distributor)
    private List<Contract> contracts = new ArrayList<>();

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

    public int getInitialProductionCost() {
        return initialProductionCost;
    }

    public void setInitialProductionCost(final int initialProductionCost) {
        this.initialProductionCost = initialProductionCost;
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
        payMonth = initialInfrastructureCost + initialProductionCost + profitMonth;
    }

    /**
     * final price (per month)
     * @param round from numberOfTurns
     */
    public void setConsumersPrice(final int round) {
        profitMonth = (int) Math.round(Math.floor(0.2 * initialProductionCost));
        // calculate the price if there are no consumers
        // or before rounds (which is round 0)
        if (contracts.size() == 0 || round == -1) {
            setNoConsumerPrice();
        } else {
            payMonth = (int) Math.round(Math.floor((initialInfrastructureCost
                    / (float) contracts.size()) + initialProductionCost + profitMonth));
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
            costsMonth = initialProductionCost * contracts.size();
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

}
