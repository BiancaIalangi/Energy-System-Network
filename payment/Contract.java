package payment;

import interaction.Consumers;

public final class Contract {

    // the owner of this contract
    private final Consumers consumer;

    // the distributor's id whom is paying
    private final int distributorID;

    // the price consumer signed to pay per month
    private final int price;

    // how many months remained to pay
    private int remainedContractMonths;

    public Contract(final Consumers consumer, final int distributorID,
                    final int price, final int remainedContractMonths) {
        this.consumer = consumer;
        this.distributorID = distributorID;
        this.price = price;
        this.remainedContractMonths = remainedContractMonths;
    }

    public Consumers getConsumer() {
        return consumer;
    }

    public int getDistributorID() {
        return distributorID;
    }

    public int getPrice() {
        return price;
    }

    public int getRemainedContractMonths() {
        return remainedContractMonths;
    }

    /**
     * decrement months
     */
    public void decRemainedContractMonths() {
        remainedContractMonths--;
    }
}
