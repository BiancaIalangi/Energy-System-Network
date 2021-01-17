package fileio;
import java.util.ArrayList;
import java.util.List;

public class MonthlyStatus {
    private int month = 0;
    private List<Distributor> distributors = new ArrayList<>();

    public MonthlyStatus(int month, List<Distributor> distributors) {
        this.month = month;
        this.distributors = distributors;
    }

    public void setMonth(int month) {
        this.month = month;
    }

    public boolean canUpdateDistribuitorsList(Distributor d) {
        if (!distributors.isEmpty()) {
            for (Distributor distributor : distributors) {
                if (distributor.getId() == d.getId()) {
                    return false;
                }
            }
        }
        return true;
    }

    public int getMonth() {
        return month;
    }

    public List<Distributor> getDistributors() {
        return distributors;
    }

    public String toString() {
        StringBuilder str = new StringBuilder();
        str.append("month = ");
        str.append(month);
        str.append(" and distributorsIds = ");
        for (int i = 0; i < distributors.size(); ++i) {
            str.append(distributors.get(i).getId());
            str.append(" ");
        }
        return str.toString();
    }
}
