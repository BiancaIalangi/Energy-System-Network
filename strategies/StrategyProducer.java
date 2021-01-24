package strategies;
import interaction.Distributor;
import interaction.Producer;
import java.util.List;

public abstract class StrategyProducer {
    /**
     * add producers in distributor's list until he does not need to
     * @param producers a list with all producers
     * @param distributor that needs to pick producers
     */
    public final void applyStrategy(final List<Producer> producers,
                              final Distributor distributor) {
        List<Producer> copyListProducer = java.util.List.copyOf(producers);

        for (Producer p : copyListProducer) {
            // check if distributor need more energy
            if (distributor.needKW()) {
                // check if producer p can add this distributor
                if (p.canAddObserver(distributor)) {
                    distributor.addProducer(p);
                    p.addObserver(distributor);
                    // update current energy
                    distributor.addCurrKW(p.getEnergyPerDistributor());
                }
            }
        }
    }

    /**
     * selection of producers based on a certain strategy
     * @param producers list with all producers
     * @param distributor that pick producers
     */
    public abstract void strategyOrderProducer(List<Producer> producers,
                                               Distributor distributor);
}
