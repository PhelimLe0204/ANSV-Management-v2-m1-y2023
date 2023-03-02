package vn.ansv.management.dto.Report;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ListReport3DTO {
    private Long id;
    private String jobName;
    private String customerName;
    private String picName;
    private String statusDisplay;
    private String statusColor;
    private String tinhTrangDuAn;
    private String tongGiaTriThucTe;
    private Integer week;
    private Integer year;

    public ListReport3DTO() {
    }

    public ListReport3DTO(Long id, String jobName, String customerName, String picName, String statusDisplay,
            String statusColor, String tinhTrangDuAn, String tongGiaTriThucTe, Integer week, Integer year) {
        this.id = id;
        this.jobName = jobName;
        this.customerName = customerName;
        this.picName = picName;
        this.statusDisplay = statusDisplay;
        this.statusColor = statusColor;
        this.tinhTrangDuAn = tinhTrangDuAn;
        this.tongGiaTriThucTe = tongGiaTriThucTe;
        this.week = week;
        this.year = year;
    }

}
