package strategies;

import fileio.Distributor;
import fileio.Producer;

import java.util.Comparator;
import java.util.List;

public final class GreenStrategy extends StrategyProducer {
    public void strategyOrderProducer(final List<Producer> producers,
                                      final Distributor distributor) {
        for (Producer p : producers) {
            p.setGreen(p.getEnergyType().isRenewable());
        }
        Comparator<Producer> comparator1 = Comparator.comparing(Producer::getEnergyPerDistributor);
        producers.sort(comparator1.reversed());
        Comparator<Producer> comparator2 = Comparator.comparing(Producer::getPriceKW);
        producers.sort(comparator2);
        Comparator<Producer> comparator3 = Comparator.comparing(Producer::isGreen);
        producers.sort(comparator3.reversed());
        applyStrategy(producers, distributor);
    }
}
