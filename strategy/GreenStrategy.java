package strategy;
import fileio.Distributor;
import fileio.Producer;

import java.util.Comparator;
import java.util.List;

public class GreenStrategy implements StrategyProducer {

    @Override
    public void strategyOrderProducer(List<Producer> producers, Distributor distributor) {
        Comparator<Producer> comparator = Comparator.comparing(Producer::getId);
        producers.sort(comparator);
        Comparator<Producer> comparator1 = Comparator.comparing(Producer::getEnergyPerDistributor);
        producers.sort(comparator1.reversed());
        Comparator<Producer> comparator2 = Comparator.comparing(Producer::getPriceKW);
        producers.sort(comparator2);
        Comparator<Producer> comparator3 = Comparator.comparing(Producer::getPriceKW);
        producers.sort(comparator3);
        for (Producer p : producers) {
            if (p.getEnergyType().isRenewable()) {
                if (distributor.needKW()) {
                    if (p.addDistributor(distributor)) {
                        distributor.addProducer(p);
                        distributor.addCurrKW(p.getEnergyPerDistributor());
                    }
                }
            }
        }

        if (distributor.getProducerList().isEmpty()) {
            for (Producer p : producers) {
                if (distributor.needKW()) {
                    if (p.addDistributor(distributor)) {
                        distributor.addProducer(p);
                        distributor.addCurrKW(p.getEnergyPerDistributor());
                    }
                }
            }
        }
    }
}
