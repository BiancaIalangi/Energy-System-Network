package output;

import interaction.Distributor;
import java.util.ArrayList;
import java.util.List;

public final class OutputMonthlyStatus {
    private final int month;

    private final List<Integer> distributorsIds = new ArrayList<>();

    public OutputMonthlyStatus(final int month, final List<Distributor> distributors) {
        this.month = month;
        for (Distributor d : distributors) {
            distributorsIds.add(d.getId());
        }
    }

    public int getMonth() {
        return month;
    }

    public List<Integer> getDistributorsIds() {
        return distributorsIds;
    }
}
