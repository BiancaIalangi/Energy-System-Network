package fileio;

import entities.EnergyType;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;

public final class Producer extends Observable {
    private int id;

    private EnergyType energyType;

    private int maxDistributors;

    private double priceKW;

    private int energyPerDistributor;

    private final List<Distributor> distributorList = new ArrayList<>();

    private List<MonthlyStatus> monthlyStatusList = new ArrayList<>();

    private boolean isGreen;

    public boolean isGreen() {
        return isGreen;
    }

    public void setGreen(final boolean green) {
        isGreen = green;
    }

    public int getId() {
        return id;
    }

    public void setId(final int id) {
        this.id = id;
    }

    public void setEnergyType(final EnergyType energyType) {
        this.energyType = energyType;
    }

    public EnergyType getEnergyType() {
        return energyType;
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

    public int getEnergyPerDistributor() {
        return energyPerDistributor;
    }

    public void setEnergyPerDistributor(final int energyPerDistributor) {
        this.energyPerDistributor = energyPerDistributor;
    }

    public List<Distributor> getDistributorList() {
        return distributorList;
    }

    public List<MonthlyStatus> getMonthlyStatusList() {
        return monthlyStatusList;
    }

    public void setMonthlyStatusList(final List<MonthlyStatus> monthlyStatusList) {
        this.monthlyStatusList = monthlyStatusList;
    }

    public void upgradeMonthlyStatusList(final int month) {
        List<Distributor> copyDistributorList = java.util.List.copyOf(distributorList);
        monthlyStatusList.add(new MonthlyStatus(month, copyDistributorList));
    }

    public boolean canAddObserver(final Distributor distributor) {
        if (distributorList.size() == maxDistributors) {
            return false;
        }
        distributorList.add(distributor);
        return true;
    }

    public void setModification() {
        setChanged();
        notifyObservers();
    }
}
