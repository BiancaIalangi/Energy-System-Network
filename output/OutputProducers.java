package output;

import entities.EnergyType;
import interaction.MonthlyStatus;
import java.util.ArrayList;
import java.util.List;

public final class OutputProducers {
    private final int id;

    private final int maxDistributors;

    private final double priceKW;

    private final EnergyType energyType;

    private final int energyPerDistributor;

    private final List<OutputMonthlyStatus> monthlyStats = new ArrayList<>();

    public OutputProducers(int id, int maxDistributors, double priceKW,
                           EnergyType energyType, int energyPerDistributor) {
        this.id = id;
        this.maxDistributors = maxDistributors;
        this.priceKW = priceKW;
        this.energyType = energyType;
        this.energyPerDistributor = energyPerDistributor;
    }

    public int getId() {
        return id;
    }

    public int getMaxDistributors() {
        return maxDistributors;
    }

    public double getPriceKW() {
        return priceKW;
    }

    public EnergyType getEnergyType() {
        return energyType;
    }

    public int getEnergyPerDistributor() {
        return energyPerDistributor;
    }

    public List<OutputMonthlyStatus> getMonthlyStats() {
        return monthlyStats;
    }

    /**
     * create output for monthly status to write in json
     * @param monthlyStat a list of distributors subscribed to this
     *                    producer in a certain month
     */
    public void addMonthlyStat(final MonthlyStatus monthlyStat) {
        OutputMonthlyStatus outputMonthlyStatus = new OutputMonthlyStatus(
                monthlyStat.getMonth(), monthlyStat.getDistributors());
        monthlyStats.add(outputMonthlyStatus);
    }
}
