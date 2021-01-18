package strategies;
import fileio.Distributor;
import fileio.Producer;
import java.util.Comparator;
import java.util.List;

public final class QuantityStrategy extends StrategyProducer {

    public void strategyOrderProducer(final List<Producer> producers,
                                      final Distributor distributor) {

        Comparator<Producer> comparator1 = Comparator.comparing(Producer::getEnergyPerDistributor);
        producers.sort(comparator1.reversed());
        applyStrategy(producers, distributor);
    }
}
