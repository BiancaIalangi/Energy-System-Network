package strategies;

import interaction.Distributor;
import interaction.Producer;

import java.util.Comparator;
import java.util.List;

public final class GreenStrategy extends StrategyProducer {

    /**
     * set producers from which the distributor takes energy
     * @param producers list with all producers
     * @param distributor that pick producers based on Green Strategy
     */
    public void strategyOrderProducer(final List<Producer> producers,
                                      final Distributor distributor) {
        // set for each producer if it is green energy or not
        producers.forEach(producer -> producer.setGreen(producer.getEnergyType().isRenewable()));

        Comparator<Producer> comparator1 = Comparator.comparing(Producer::getEnergyPerDistributor);
        producers.sort(comparator1.reversed());
        Comparator<Producer> comparator2 = Comparator.comparing(Producer::getPriceKW);
        producers.sort(comparator2);
        Comparator<Producer> comparator3 = Comparator.comparing(Producer::isGreen);
        producers.sort(comparator3.reversed());

        applyStrategy(producers, distributor);
    }
}
