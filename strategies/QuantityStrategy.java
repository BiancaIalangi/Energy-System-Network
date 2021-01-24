package strategies;
import interaction.Distributor;
import interaction.Producer;
import java.util.Comparator;
import java.util.List;

public final class QuantityStrategy extends StrategyProducer {

    /**
     * set producers from which the distributor takes energy
     * @param producers list with all producers
     * @param distributor that pick producers based on Quantity Strategy
     */
    public void strategyOrderProducer(final List<Producer> producers,
                                      final Distributor distributor) {

        producers.sort(Comparator.comparing(Producer::getEnergyPerDistributor).reversed());

        applyStrategy(producers, distributor);
    }
}
