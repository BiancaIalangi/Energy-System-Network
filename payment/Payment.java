package payment;

import fileio.Consumers;
import fileio.CostsChanges;
import fileio.Distributor;
import fileio.InputData;
import fileio.MonthlyUpdates;

import java.util.Comparator;
import java.util.List;

public final class Payment {

    // number of rounds
    private final int numberOfTurns;

    // a list with all consumers
    private final List<Consumers> consumers;

    // a list with all distributors
    private final List<Distributor> distributors;

    // a list with all updates from rounds
    private final List<MonthlyUpdates> monthlyUpdates;

    public Payment(final InputData inputData) {
        numberOfTurns = inputData.getNumberOfTurns();
        consumers = inputData.getInitialData().getConsumers();
        distributors = inputData.getInitialData().getDistributors();
        monthlyUpdates = inputData.getMonthlyUpdates();
    }

    /**
     * @param round where we search a new distributor
     * @return the distributor with the smallest value
     */
    public Distributor findMinDistributor(final int round) {
        // calculate the value of tax for each distributor
        for (Distributor distributor : distributors) {
            distributor.setConsumersPrice(round);
        }
        // order distributors by their id and price (ascending order)
        Comparator<Distributor> comparator = Comparator.comparing(Distributor::getId);
        distributors.sort(comparator);
        Comparator<Distributor> comparator1 = Comparator.comparing(Distributor::getPayMonth);
        distributors.sort(comparator1);
        // if the distributor has no money or is bankrupt it won't accept new consumers
        for (Distributor d : distributors) {
            if (!d.isBankrupt() && d.getInitialBudget() != 0) {
                return d;
            }
        }
        return null;
    }

    /**
     * create a new contract from consumer c
     * that what to subscribe to distributor d
     * @param c consumer that needs a new contract
     * @param d where he want a new contract
     */
    public void createContract(final Consumers c, final Distributor d) {
        // generate new contract
        ContractFactory factory = ContractFactory.getInstance();
        Contract contract = factory.generateContract(c, d.getId(),
                d.getPayMonth(), d.getContractLength());
        // add in distributor's list of contracts
        d.addContract(contract);
        c.setContract(contract);
    }

    /**
     * check if the distributor can pay taxes or is bankrupt
     */
    public void checkDistributor() {
        for (Distributor distributor : distributors) {
            if (distributor.getInitialBudget() >= 0) {
                distributor.payTaxes();
                if (distributor.getInitialBudget() < 0) {
                    distributor.setBankrupt(true);
                }
            }
        }
    }

    /**
     * round before changes from numberOfTurns
     */
    public void roundZero() {
        // find the distributor with the min taxes
        Distributor distributor = findMinDistributor(-1);
        for (Consumers c : consumers) {
            assert distributor != null;
            // create contract for each consumer
            createContract(c, distributor);
            // consumer receive income
            c.setSum();
            // check if he can afford to pay taxes
            if (c.getInitialBudget() >= c.getContract().getPrice()) {
                // if he afford he pay the tax
                c.payTaxes(c.getContract().getPrice());
                for (Distributor d : distributors) {
                    if (d.getId() == c.getContract().getDistributorID()) {
                        // the distributor from the contract will receive the money
                        d.getPaid(c.getContract().getPrice());
                    }
                }
            } else {
                // add to debts
                c.setRestStatus(c.getContract().getPrice());
            }
            c.getContract().decRemainedContractMonths();
        }
        // check distributor's financial status
        checkDistributor();
    }

    /**
     * update changes from rounds
     * @param costsChanges changes to infrastructure and production
     */
    public void updateDistributor(final CostsChanges costsChanges) {
        for (Distributor d : distributors) {
            if (d.getId() == costsChanges.getId()) {
                d.setInitialInfrastructureCost(costsChanges.getInfrastructureCost());
                d.setInitialProductionCost(costsChanges.getProductionCost());
            }
        }
    }

    /**
     * a basic round
     * updates consumers and distributors
     */
    public void basicRound() {
        roundZero();
        for (int i = 0; i < numberOfTurns; i++) {
            // updates consumers' list
            if (!monthlyUpdates.get(i).getNewConsumers().isEmpty()) {
                consumers.addAll(monthlyUpdates.get(i).getNewConsumers());
            }
            // updates changes made by distributors in a round
            if (!monthlyUpdates.get(i).getCostsChanges().isEmpty()) {
                for (CostsChanges costsChanges : monthlyUpdates.get(i).getCostsChanges()) {
                    updateDistributor(costsChanges);
                }
            }

            // calculate price for each distributor
            for (Distributor distributor : distributors) {
                distributor.setConsumersPrice(i);
            }

            // find the distributor with the smallest price
            Distributor goodDistributor = findMinDistributor(i);

            // remove expired contracts and no debts
            for (Distributor distributor : distributors) {
                distributor.getContracts().removeIf(c -> c.getRemainedContractMonths() == 0
                        && c.getConsumer().getRestStatus() == 0);
            }

            for (Consumers cons : consumers) {
                //check if he is bankrupt
                if (!cons.isBankrupt()) {
                    // create a contract if he doesn't has or has an expired contract
                    if ((cons.getContract() == null || cons.getContract().
                            getRemainedContractMonths() <= 0) && cons.getRestStatus() == 0) {
                        assert goodDistributor != null;
                        createContract(cons, goodDistributor);
                    }
                }
            }

            for (Consumers c : consumers) {
                //check if he is bankrupt
                if (!c.getBankrupt()) {
                    // consumer receive income
                    c.setSum();
                    // if he has debts
                    if (c.getRestStatus() != 0) {
                        // and the contract didn't expire
                        if (c.getContract().getRemainedContractMonths() != 0) {
                            // and he can afford to pay
                            if (c.canPay((int) 2.2 * c.getContract().getPrice())) {
                                c.payTaxes((int) 2.2 * c.getContract().getPrice());
                                for (Distributor d : distributors) {
                                    if (d.getId() == c.getContract().getDistributorID()) {
                                        d.getPaid((int) 2.2 * c.getContract().getPrice());
                                    }
                                }
                                c.getContract().decRemainedContractMonths();
                                c.setRestStatus(0);
                            } else {
                                // if can't afford consumer is bankrupt
                                c.setBankrupt(true);
                            }
                        } else {
                            // debt from old contract
                            int oldRest = (int) Math.round(Math.floor(1.2 * c.getRestStatus()));
                            int idOldRest = c.getContract().getDistributorID();
                            // find the distributor to pay the debt
                            for (Distributor d : distributors) {
                                if (d.getId() == c.getContract().getDistributorID()) {
                                    d.getContracts().remove(c.getContract());
                                }
                            }
                            // the consumer has no contract anymore
                            c.setContract(null);
                            assert goodDistributor != null;
                            // create new contract
                            createContract(c, goodDistributor);
                            // check if he can afford to pay the debt and the new tax
                            if (c.canPay(oldRest + c.getContract().getPrice())) {
                                // pay debt to old distributor
                                distributors.get(idOldRest).getPaid(oldRest);
                                for (Distributor d : distributors) {
                                    if (d.getId() == c.getContract().getDistributorID()) {
                                        // pay tax to new distributor
                                        d.getPaid(c.getContract().getPrice());
                                    }
                                }
                            } else {
                                // if he can't afford he is bankrupt
                                c.setBankrupt(true);
                            }
                        }
                    } else {
                        if (!c.canPay(c.getContract().getPrice())) {
                            // if he has no debts and can't afford to pay this tax
                            // set as consumer's debt
                            c.setRestStatus(c.getContract().getPrice());
                        } else {
                                c.payTaxes(c.getContract().getPrice());
                                for (Distributor d : distributors) {
                                    if (d.getId() == c.getContract().getDistributorID()) {
                                        d.getPaid(c.getContract().getPrice());
                                    }
                                }
                        }
                        c.getContract().decRemainedContractMonths();
                    }
                }
            }
            // check distributor's financial status
            checkDistributor();

            // remove all contracts that have bankrupt consumers
            for (Distributor distributor : distributors) {
                distributor.getContracts().removeIf(
                        contract -> contract.getConsumer().isBankrupt());
            }
        }
    }
}
