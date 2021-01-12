package payment;

import fileio.Consumers;

public final class Contract {

    // the owner of this contract
    private Consumers consumer;

    // the distributor's id whom is paying
    private int distributorID;

    // the price consumer signed to pay per month
    private int price;

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

    public void setPrice(final int price) {
        this.price = price;
    }

    public int getRemainedContractMonths() {
        return remainedContractMonths;
    }

    public void setRemainedContractMonths(final int remainedContractMonths) {
        this.remainedContractMonths = remainedContractMonths;
    }

    /**
     * decrement months
     */
    public void decRemainedContractMonths() {
        remainedContractMonths--;
    }
}
