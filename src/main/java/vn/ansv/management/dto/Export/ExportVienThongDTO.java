package vn.ansv.management.dto.Export;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ExportVienThongDTO {
    private Long id;
    private String jobName;
    private String customerName;
    private String description;
    private String hinhThucDauTu;
    private String tongMucDauTuDuKien;
    private Integer mucDoKhaThi;
    private String phanTichSwoot;
    private String generalIssue;
    private String solution;
    private String priorityName;
    private String statusName;
    private String amName;
    private String amManagerName;
    private String ketQuaTuanTruoc;
    private String ketQuaTuanNay;
    private String keHoachTuanNay;
    private String keHoachTuanSau;

    public ExportVienThongDTO(Long id, String jobName, String customerName, String description, String hinhThucDauTu,
            String tongMucDauTuDuKien, Integer mucDoKhaThi, String phanTichSwoot, String generalIssue, String solution,
            String priorityName, String statusName, String amName, String amManagerName, String ketQuaTuanTruoc,
            String ketQuaTuanNay, String keHoachTuanNay, String keHoachTuanSau) {
        this.id = id;
        this.jobName = jobName;
        this.customerName = customerName;
        this.description = description;
        this.hinhThucDauTu = hinhThucDauTu;
        this.tongMucDauTuDuKien = tongMucDauTuDuKien;
        this.mucDoKhaThi = mucDoKhaThi;
        this.phanTichSwoot = phanTichSwoot;
        this.generalIssue = generalIssue;
        this.solution = solution;
        this.priorityName = priorityName;
        this.statusName = statusName;
        this.amName = amName;
        this.amManagerName = amManagerName;
        this.ketQuaTuanTruoc = ketQuaTuanTruoc;
        this.ketQuaTuanNay = ketQuaTuanNay;
        this.keHoachTuanNay = keHoachTuanNay;
        this.keHoachTuanSau = keHoachTuanSau;
    }

}
