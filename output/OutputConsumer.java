package output;

public final class OutputConsumer {
    private final int id;

    private final boolean isBankrupt;

    private final int budget;

    public OutputConsumer(int id, boolean isBankrupt, int budget) {
        this.id = id;
        this.isBankrupt = isBankrupt;
        this.budget = budget;
    }

    public int getId() {
        return id;
    }

    public int getBudget() {
        return budget;
    }

    public boolean getIsBankrupt() {
        return isBankrupt;
    }
}
