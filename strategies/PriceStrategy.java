package strategies;

import interaction.Distributor;
import interaction.Producer;

import java.util.Comparator;
import java.util.List;

public final class PriceStrategy extends StrategyProducer {

    /**
     * set producers from which the distributor takes energy
     * @param producers list with all producers
     * @param distributor that pick producers based on Price Strategy
     */
    public void strategyOrderProducer(final List<Producer> producers,
                                      final Distributor distributor) {

        Comparator<Producer> comparator = Comparator.comparing(Producer::getId);
        producers.sort(comparator);
        Comparator<Producer> comparator1 = Comparator.comparing(Producer::getEnergyPerDistributor);
        producers.sort(comparator1.reversed());
        Comparator<Producer> comparator2 = Comparator.comparing(Producer::getPriceKW);
        producers.sort(comparator2);

        applyStrategy(producers, distributor);
    }
}
