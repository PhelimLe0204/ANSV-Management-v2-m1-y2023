package vn.ansv.management.dto.Detail;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ReportDetailTabDuThauDTO {
    private Long id;
    private String jobName;
    private String description;
    private String phanTichSwoot;
    private String customerName;
    private String hinhThucDauTu;
    private String phamViCungCap;
    private String tongMucDauTuDuKien;
    private Integer mucDoKhaThi;

    public ReportDetailTabDuThauDTO(Long id, String jobName, String description, String phanTichSwoot,
            String customerName, String hinhThucDauTu, String phamViCungCap, String tongMucDauTuDuKien,
            Integer mucDoKhaThi) {
        this.id = id;
        this.jobName = jobName;
        this.description = description;
        this.phanTichSwoot = phanTichSwoot;
        this.customerName = customerName;
        this.hinhThucDauTu = hinhThucDauTu;
        this.phamViCungCap = phamViCungCap;
        this.tongMucDauTuDuKien = tongMucDauTuDuKien;
        this.mucDoKhaThi = mucDoKhaThi;
    }

}
