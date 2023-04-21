package vn.ansv.management.dto.Statistic;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DashboardChartDTO {
    private int dangerPriority1;
    private int dangerPriority2;
    private int dangerPriority3;

    private int warningPriority1;
    private int warningPriority2;
    private int warningPriority3;

    private int ongoingPriority1;
    private int ongoingPriority2;
    private int ongoingPriority3;

    public DashboardChartDTO() {
    }

    public DashboardChartDTO(int dangerPriority1, int dangerPriority2, int dangerPriority3, int warningPriority1,
            int warningPriority2, int warningPriority3, int ongoingPriority1, int ongoingPriority2,
            int ongoingPriority3) {
        this.dangerPriority1 = dangerPriority1;
        this.dangerPriority2 = dangerPriority2;
        this.dangerPriority3 = dangerPriority3;
        this.warningPriority1 = warningPriority1;
        this.warningPriority2 = warningPriority2;
        this.warningPriority3 = warningPriority3;
        this.ongoingPriority1 = ongoingPriority1;
        this.ongoingPriority2 = ongoingPriority2;
        this.ongoingPriority3 = ongoingPriority3;
    }

}
