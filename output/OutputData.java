package output;

import payment.Contract;
import fileio.Consumers;
import fileio.Distributor;
import fileio.InputData;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public final class OutputData {

    private List<OutputConsumer> consumers = new ArrayList<>();
    private List<OutputDistributor> distributors = new ArrayList<>();

    public OutputData(final InputData inputData) {
        // create output for distributors to write in json
        for (Distributor distributor : inputData.getInitialData().getDistributors()) {
            OutputDistributor outputDistributor = new OutputDistributor();

            outputDistributor.setBankrupt(distributor.isBankrupt());
            outputDistributor.setBudget(distributor.getInitialBudget());
            outputDistributor.setId(distributor.getId());
            for (Contract contract : distributor.getContracts()) {
                outputDistributor.addPaymentContract(contract);
            }
            distributors.add(outputDistributor);
        }
        // create output for consumers to write in json
        for (Consumers consumer : inputData.getInitialData().getConsumers()) {
            OutputConsumer cons = new OutputConsumer();
            cons.setId(consumer.getId());
            cons.setBankrupt(consumer.isBankrupt());
            cons.setBudget(consumer.getInitialBudget());
            consumers.add(cons);
        }

        // order consumers by their id (ascending order)
        Comparator<OutputConsumer> comparator = Comparator.comparing(OutputConsumer::getId);
        consumers.sort(comparator);

        // order distributor by their id (ascending order)
        Comparator<OutputDistributor> comparator1 = Comparator.comparing(OutputDistributor::getId);
        distributors.sort(comparator1);
    }

    public List<OutputConsumer> getConsumers() {
        return consumers;
    }

    public void setConsumers(final List<OutputConsumer> consumers) {
        this.consumers = consumers;
    }

    public List<OutputDistributor> getDistributors() {
        return distributors;
    }

    public void setDistributors(final List<OutputDistributor> distributors) {
        this.distributors = distributors;
    }
}
