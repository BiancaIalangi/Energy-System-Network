package output;

public final class OutputConsumer {
    private int id;

    private boolean isBankrupt;

    private int budget;

    public int getId() {
        return id;
    }

    public void setId(final int id) {
        this.id = id;
    }

    public int getBudget() {
        return budget;
    }

    public void setBudget(final int initialBudget) {
        this.budget = initialBudget;
    }

    public boolean getIsBankrupt() {
        return isBankrupt;
    }

    public void setBankrupt(final boolean bankrupt) {
        this.isBankrupt = bankrupt;
    }
}
