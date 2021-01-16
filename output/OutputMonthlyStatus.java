package output;

import fileio.Distributor;
import java.util.ArrayList;
import java.util.List;

public class OutputMonthlyStatus {
    private int month;

    private List<Integer> distributorsIds = new ArrayList<>();

    public OutputMonthlyStatus(int month, List<Distributor> distributors) {
        this.month = month;
        for (Distributor d : distributors) {
            distributorsIds.add(d.getId());
        }
    }

    public int getMonth() {
        return month;
    }

    public void setMonth(int month) {
        this.month = month;
    }

    public List<Integer> getDistributorsIds() {
        return distributorsIds;
    }

    public void setDistributorsIds(List<Integer> distributorsIds) {
        this.distributorsIds = distributorsIds;
    }
}
