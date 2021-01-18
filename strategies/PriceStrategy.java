package strategies;

import fileio.Distributor;
import fileio.Producer;

import java.util.Comparator;
import java.util.List;

public final class PriceStrategy extends StrategyProducer {

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
