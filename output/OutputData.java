package output;
import interaction.Consumers;
import interaction.Distributor;
import fileio.InputData;
import interaction.MonthlyStatus;
import interaction.Producer;
import payment.Contract;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public final class OutputData {

    private final List<OutputConsumer> consumers = new ArrayList<>();
    private final List<OutputDistributor> distributors = new ArrayList<>();
    private final List<OutputProducers> energyProducers = new ArrayList<>();

    public OutputData(final InputData inputData) {
        // create output for distributors to write in json
        for (Distributor distributor : inputData.getInitialData().getDistributors()) {
            OutputDistributor outputD = new OutputDistributor(distributor.getId(),
                    distributor.getEnergyNeededKW(), distributor.getPayMonth(),
                    distributor.getInitialBudget(),  distributor.isBankrupt(),
                    distributor.getProducerStrategy());

            for (Contract contract : distributor.getContracts()) {
                outputD.addPaymentContract(contract);
            }
            distributors.add(outputD);
        }

        // create output for consumers to write in json
        for (Consumers consumer : inputData.getInitialData().getConsumers()) {
            OutputConsumer outputC = new OutputConsumer(consumer.getId(), consumer.isBankrupt(),
                    consumer.getInitialBudget());
            consumers.add(outputC);
        }

        // create output for producers to write in json
        for (Producer producer : inputData.getInitialData().getProducers()) {
            OutputProducers outputP = new OutputProducers(producer.getId(),
                    producer.getMaxDistributors(), producer.getPriceKW(),
                    producer.getEnergyType(), producer.getEnergyPerDistributor());

            for (MonthlyStatus monthlyStatus : producer.getMonthlyStatusList()) {
                outputP.addMonthlyStat(monthlyStatus);
            }
            energyProducers.add(outputP);
        }

        // order consumers by their id (ascending order)
        consumers.sort(Comparator.comparing(OutputConsumer::getId));

        // order distributor by their id (ascending order)
        distributors.sort(Comparator.comparing(OutputDistributor::getId));

        // order producers by their id (ascending order)
        energyProducers.sort(Comparator.comparing(OutputProducers::getId));

        // show monthly status chronological for each producer
        energyProducers.forEach(outputProducers -> outputProducers.getMonthlyStats().
                forEach(outputMonthlyStatus -> outputMonthlyStatus.getDistributorsIds().
                        sort(Integer::compareTo)));

    }

    public List<OutputConsumer> getConsumers() {
        return consumers;
    }

    public List<OutputDistributor> getDistributors() {
        return distributors;
    }

    public List<OutputProducers> getEnergyProducers() {
        return energyProducers;
    }
}
