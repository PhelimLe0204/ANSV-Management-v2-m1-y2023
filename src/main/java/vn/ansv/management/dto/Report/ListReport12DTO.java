package vn.ansv.management.dto.Report;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ListReport12DTO {
    private Long id;
    private String jobName;
    private String customerName;
    private String picName;
    private String statusDisplay;
    private String statusColor;
    private String tinhTrangDuAn;
    private Integer mucDoKhaThi;
    private String tongMucDauTuDuKien;
    private Integer week;
    private Integer year;

    public ListReport12DTO() {
    }

    public ListReport12DTO(Long id, String jobName, String customerName, String picName, String statusDisplay,
            String statusColor, String tinhTrangDuAn, Integer mucDoKhaThi, String tongMucDauTuDuKien,
            Integer week, Integer year) {
        this.id = id;
        this.jobName = jobName;
        this.customerName = customerName;
        this.picName = picName;
        this.statusDisplay = statusDisplay;
        this.statusColor = statusColor;
        this.tinhTrangDuAn = tinhTrangDuAn;
        this.mucDoKhaThi = mucDoKhaThi;
        this.tongMucDauTuDuKien = tongMucDauTuDuKien;
        this.week = week;
        this.year = year;
    }

}
