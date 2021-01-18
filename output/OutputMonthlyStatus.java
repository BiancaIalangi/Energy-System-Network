package output;

import fileio.Distributor;
import java.util.ArrayList;
import java.util.List;

public final class OutputMonthlyStatus {
    private int month;

    private List<Integer> distributorsIds = new ArrayList<>();

    public OutputMonthlyStatus(final int month, final List<Distributor> distributors) {
        this.month = month;
        for (Distributor d : distributors) {
            distributorsIds.add(d.getId());
        }
    }

    public int getMonth() {
        return month;
    }

    public void setMonth(final int month) {
        this.month = month;
    }

    public List<Integer> getDistributorsIds() {
        return distributorsIds;
    }

    public void setDistributorsIds(final List<Integer> distributorsIds) {
        this.distributorsIds = distributorsIds;
    }
}
