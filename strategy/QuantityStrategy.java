package strategy;

import fileio.Distributor;
import fileio.Producer;

import java.util.Comparator;
import java.util.List;

public class QuantityStrategy implements StrategyProducer{

    @Override
    public void strategyOrderProducer(List<Producer> producers, Distributor distributor) {

        Comparator<Producer> comparator1 = Comparator.comparing(Producer::getEnergyPerDistributor);
        producers.sort(comparator1.reversed());
        List<Producer> copyListProducer = java.util.List.copyOf(producers);
        for (Producer p : copyListProducer) {
            if (distributor.needKW()) {
                if (p.canAddObserver(distributor)) {
                    p.addObserver(distributor);
                    distributor.addProducer(p);
                    distributor.addCurrKW(p.getEnergyPerDistributor());
                }
            }
        }
    }
}
