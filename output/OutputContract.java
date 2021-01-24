package output;

public final class OutputContract {

    private final int consumerId;

    private final int price;

    private final int remainedContractMonths;

    public OutputContract(final int consumerId, final int price, final int remainedContractMonths) {
        this.consumerId = consumerId;
        this.price = price;
        this.remainedContractMonths = remainedContractMonths;
    }

    public int getConsumerId() {
        return consumerId;
    }

    public int getPrice() {
        return price;
    }

    public int getRemainedContractMonths() {
        return remainedContractMonths;
    }
}
