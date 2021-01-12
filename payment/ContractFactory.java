package payment;

import fileio.Consumers;

public final class ContractFactory {
    private static ContractFactory generateFactory = null;

    private ContractFactory() { }

    /**
     * singleton for ContractFactory
     * @return a ContractFactory if it does not exist
     */
    public static ContractFactory getInstance() {
        if (generateFactory == null) {
            generateFactory = new ContractFactory();
        }
        return generateFactory;
    }

    /**
     * create a new contract
     * @param consumer consumer that wants a new contract
     * @param distributorID where the contract is assigned
     * @param price the price that consumer assign to pay
     * @param remainedContractMonths duration of contract
     * @return the new contract
     */
    public Contract generateContract(final Consumers consumer, final int distributorID,
                                     final int price, final int remainedContractMonths) {

        return new Contract(consumer, distributorID, price, remainedContractMonths);
    }
}
