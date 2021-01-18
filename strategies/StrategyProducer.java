package strategies;
import fileio.Distributor;
import fileio.Producer;
import java.util.List;

public abstract class StrategyProducer {
    public void applyStrategy(final List<Producer> producers,
                              final Distributor distributor) {
        List<Producer> copyListProducer = java.util.List.copyOf(producers);

        for (Producer p : copyListProducer) {
            if (distributor.needKW()) {
                if (p.canAddObserver(distributor)) {
                    distributor.addProducer(p);
                    p.addObserver(distributor);
                    distributor.addCurrKW(p.getEnergyPerDistributor());
                }
            }
        }
    }
    public abstract void strategyOrderProducer(List<Producer> producers,
                                               Distributor distributor);
}
