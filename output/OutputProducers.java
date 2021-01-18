package output;

import entities.EnergyType;
import fileio.MonthlyStatus;
import java.util.ArrayList;
import java.util.List;

public final class OutputProducers {
    private int id;

    private int maxDistributors;

    private double priceKW;

    private EnergyType energyType;

    private int energyPerDistributor;

    private List<OutputMonthlyStatus> monthlyStats = new ArrayList<>();

    public void addMonthlyStat(final MonthlyStatus monthlyStat) {
        OutputMonthlyStatus outputMonthlyStatus = new OutputMonthlyStatus(
                monthlyStat.getMonth(), monthlyStat.getDistributors());
        monthlyStats.add(outputMonthlyStatus);
    }

    public int getId() {
        return id;
    }

    public void setId(final int id) {
        this.id = id;
    }

    public int getMaxDistributors() {
        return maxDistributors;
    }

    public void setMaxDistributors(final int maxDistributors) {
        this.maxDistributors = maxDistributors;
    }

    public double getPriceKW() {
        return priceKW;
    }

    public void setPriceKW(final double priceKW) {
        this.priceKW = priceKW;
    }

    public EnergyType getEnergyType() {
        return energyType;
    }

    public void setEnergyType(final EnergyType energyType) {
        this.energyType = energyType;
    }

    public int getEnergyPerDistributor() {
        return energyPerDistributor;
    }

    public void setEnergyPerDistributor(final int energyPerDistributor) {
        this.energyPerDistributor = energyPerDistributor;
    }

    public List<OutputMonthlyStatus> getMonthlyStats() {
        return monthlyStats;
    }

    public void setMonthlyStats(final List<OutputMonthlyStatus> monthlyStats) {
        this.monthlyStats = monthlyStats;
    }
}
