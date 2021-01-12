package output;

import payment.Contract;

import java.util.ArrayList;
import java.util.List;

public final class OutputDistributor {

    private int id;

    private int budget;

    private boolean isBankrupt;

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

    public boolean getIsBankrupt() {
        return isBankrupt;
    }

    public void setBankrupt(final boolean bankrupt) {
        isBankrupt = bankrupt;
    }

    public List<OutputContract> getContracts() {
        return contracts;
    }

    public void setContracts(final List<OutputContract> contracts) {
        this.contracts = contracts;
    }
}
