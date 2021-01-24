package interaction;
import payment.Contract;

public final class Consumers {

    private  int id;

    private int initialBudget;

    private int monthlyIncome;

    private boolean bankrupt;

    // consumer's debts
    private int restStatus;

    // the contract assigned to this consumer
    private Contract contract;

    public Contract getContract() {
        return contract;
    }

    public void setContract(final Contract contract) {
        this.contract = contract;
    }

    public int getId() {
        return id;
    }

    public void setId(final int id) {
        this.id = id;
    }

    public int getInitialBudget() {
        return initialBudget;
    }

    public int getMonthlyIncome() {
        return monthlyIncome;
    }

    public int getRestStatus() {
        return restStatus;
    }

    public void setRestStatus(final int restStatus) {
        this.restStatus = restStatus;
    }

    public boolean isBankrupt() {
        return bankrupt;
    }

    public void setBankrupt(final boolean bankrupt) {
        this.bankrupt = bankrupt;
    }

    public boolean getBankrupt() {
        return bankrupt;
    }

    /**
     * set the current sum that
     * consumer has
     */
    public void setSum() {
        initialBudget += monthlyIncome;
    }

    /**
     * consumer pay the taxes
     * @param taxes the value that consumer
     *              will pay
     */
    public void payTaxes(final int taxes) {
        initialBudget -= taxes;
    }

    /**
     * @param taxes the value that consumer needs
     *              to pay
     * @return if the consumer afford to pay
     * those taxes
     */
    public boolean canPay(final int taxes) {
        return initialBudget - taxes >= 0;
    }
}
