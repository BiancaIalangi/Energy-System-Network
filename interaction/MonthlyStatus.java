package interaction;

import java.util.List;

/**
 * producers' monthly status
 */
public final class MonthlyStatus {
    private final int month;

    // distributors that are subscribed to a certain producer in a certain month
    private final List<Distributor> distributors;

    public MonthlyStatus(final int month, final List<Distributor> distributors) {
        this.month = month;
        this.distributors = distributors;
    }

    public int getMonth() {
        return month;
    }

    public List<Distributor> getDistributors() {
        return distributors;
    }
}
