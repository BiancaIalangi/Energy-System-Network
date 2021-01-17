package strategy;

import fileio.Distributor;
import fileio.Producer;

import java.util.List;

public interface StrategyProducer {
    void strategyOrderProducer(List<Producer> producers, Distributor distributor);
}
