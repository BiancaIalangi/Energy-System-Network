package fileio;
import java.util.List;

public final class MonthlyStatus {
    private int month;
    private final List<Distributor> distributors;

    public MonthlyStatus(final int month, final List<Distributor> distributors) {
        this.month = month;
        this.distributors = distributors;
    }

    public void setMonth(final int month) {
        this.month = month;
    }

    public int getMonth() {
        return month;
    }

    public List<Distributor> getDistributors() {
        return distributors;
    }
}
