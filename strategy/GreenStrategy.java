package strategy;

import fileio.Distributor;
import fileio.Producer;

import java.util.Comparator;
import java.util.List;

public class GreenStrategy implements StrategyProducer {

    @Override
    public void strategyOrderProducer(List<Producer> producers, Distributor distributor) {
        for (Producer p : producers) {
                p.setGreen(p.getEnergyType().isRenewable());
        }


        Comparator<Producer> comparator1 = Comparator.comparing(Producer::getEnergyPerDistributor);
        producers.sort(comparator1.reversed());
        Comparator<Producer> comparator2 = Comparator.comparing(Producer::getPriceKW);
        producers.sort(comparator2);
        Comparator<Producer> comparator3 = Comparator.comparing(Producer::isGreen);
        producers.sort(comparator3.reversed());
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
