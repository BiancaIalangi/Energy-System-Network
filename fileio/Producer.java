package fileio;

import entities.EnergyType;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;

public class Producer extends Observable {
    private int id;

    private EnergyType energyType;

    private int maxDistributors;

    private double priceKW;

    private int energyPerDistributor;

    private List<Distributor> distributorList = new ArrayList<>();

    private List<MonthlyStatus> monthlyStatusList = new ArrayList<>();

    private boolean isGreen;

    public boolean isGreen() {
        return isGreen;
    }

    public void setGreen(boolean green) {
        isGreen = green;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setEnergyType(EnergyType energyType) {
        this.energyType = energyType;
    }

    public EnergyType getEnergyType() {
        return energyType;
    }

    public int getMaxDistributors() {
        return maxDistributors;
    }

    public void setMaxDistributors(int maxDistributors) {
        this.maxDistributors = maxDistributors;
    }

    public double getPriceKW() {
        return priceKW;
    }

    public void setPriceKW(double priceKW) {
        this.priceKW = priceKW;
    }

    public int getEnergyPerDistributor() {
        return energyPerDistributor;
    }

    public void setEnergyPerDistributor(int energyPerDistributor) {
        this.energyPerDistributor = energyPerDistributor;
    }

    public List<Distributor> getDistributorList() {
        return distributorList;
    }

    public void setDistributorList(List<Distributor> distributorList) {
        this.distributorList = distributorList;
    }

    public List<MonthlyStatus> getMonthlyStatusList() {
        return monthlyStatusList;
    }

    public void setMonthlyStatusList(List<MonthlyStatus> monthlyStatusList) {
        this.monthlyStatusList = monthlyStatusList;
    }

    public void upgradeMonthlyStatusList(int month) {
        List<Distributor> copyDistributorList = java.util.List.copyOf(distributorList);
        MonthlyStatus m = new MonthlyStatus(month, copyDistributorList);
//        for (Distributor d : distributorList)
//            if (m.canUpdateDistribuitorsList(d))
//                monthlyStatusList.add(m);
//        System.out.println("Distrib din MUpdate " + m.getDistributors());

        monthlyStatusList.add(m);
    }

    //TODO check observer
    public boolean canAddObserver(Distributor distributor) {
        if (distributorList.size() == maxDistributors) {
            return false;
        }
        distributorList.add(distributor);
        return true;
    }

    //TODO check observer
    public void setModification() {
        setChanged();
        notifyObservers();
    }

    @Override
    public String toString() {
        return "Producer{" +
                "id=" + id +
                ", monthlyStatusList=" + monthlyStatusList +
                '}';
    }
}
