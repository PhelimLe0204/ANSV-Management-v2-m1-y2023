package vn.ansv.management.dto.Dashboard;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProjectDashboardDTO {
    private Long id;
    private String jobName;
    private String customerName;
    private String picName;
    private String statusDisplay;
    private String statusColor;
    private String tinhTrangDuAn;

    public ProjectDashboardDTO() {
    }

    public ProjectDashboardDTO(Long id, String jobName, String customerName, String picName, String statusDisplay,
            String statusColor, String tinhTrangDuAn) {
        this.id = id;
        this.jobName = jobName;
        this.customerName = customerName;
        this.picName = picName;
        this.statusDisplay = statusDisplay;
        this.statusColor = statusColor;
        this.tinhTrangDuAn = tinhTrangDuAn;
    }

}
