package vn.ansv.management.dto.Report;

import java.util.Date;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AddNewReportDTO {
    // (7)
    private String uid;
    private Long amId;
    private Long pmId;
    private Long amManagerId;
    private Long pmManagerId;
    private String createdBy;
    private Date createdDate;

    // Tab 1 (9)
    private Long projectId;
    private Long projectTypeId;
    private Long projectPriorityId;
    private Long projectStatusId;
    private int week;
    private int year;
    private String maHopDong;
    private String maKeToan;
    private Long currencyUnitId;

    // Tab 2 (8)
    private String jobName;
    private Long customerId;
    private String description;
    private Integer mucDoKhaThi;
    private String tongMucDauTuDuKien;
    private String hinhThucDauTu;
    private String phamViCungCap;
    private String phanTichSwoot;

    // Tab 3 (15)
    private String soTienDac;
    private String hopDongDac;
    private String mucTieuDac;
    private String thucTeDac;
    private String soTienPac;
    private String hopDongPac;
    private String mucTieuPac;
    private String thucTePac;
    private String soTienFac;
    private String hopDongFac;
    private String mucTieuFac;
    private String thucTeFac;
    private String tongGiaTriThucTe;
    private String soTienTamUng;
    private String keHoachTamUng;

    // Tab 4 (7)
    private String tienDoChung;
    private String generalIssue;
    private String solution;
    private String keHoachTuanNay;
    private String keHoachTuanSau;
    private String ketQuaTuanTruoc;
    private String ketQuaTuanNay;

    public AddNewReportDTO() {
    }

    public AddNewReportDTO(String uid, Long amId, Long pmId, Long amManagerId, Long pmManagerId, String createdBy,
            Date createdDate, Long projectId, Long projectTypeId, Long projectPriorityId, Long projectStatusId,
            int week, int year, String maHopDong, String maKeToan, Long currencyUnitId, String jobName, Long customerId,
            String description, Integer mucDoKhaThi, String tongMucDauTuDuKien, String hinhThucDauTu,
            String phamViCungCap, String phanTichSwoot, String soTienDac, String hopDongDac, String mucTieuDac,
            String thucTeDac, String soTienPac, String hopDongPac, String mucTieuPac, String thucTePac,
            String soTienFac, String hopDongFac, String mucTieuFac, String thucTeFac, String tongGiaTriThucTe,
            String soTienTamUng, String keHoachTamUng, String tienDoChung, String generalIssue, String solution,
            String keHoachTuanNay, String keHoachTuanSau, String ketQuaTuanTruoc, String ketQuaTuanNay) {
        this.uid = uid;
        this.amId = amId;
        this.pmId = pmId;
        this.amManagerId = amManagerId;
        this.pmManagerId = pmManagerId;
        this.createdBy = createdBy;
        this.createdDate = createdDate;
        this.projectId = projectId;
        this.projectTypeId = projectTypeId;
        this.projectPriorityId = projectPriorityId;
        this.projectStatusId = projectStatusId;
        this.week = week;
        this.year = year;
        this.maHopDong = maHopDong;
        this.maKeToan = maKeToan;
        this.currencyUnitId = currencyUnitId;
        this.jobName = jobName;
        this.customerId = customerId;
        this.description = description;
        this.mucDoKhaThi = mucDoKhaThi;
        this.tongMucDauTuDuKien = tongMucDauTuDuKien;
        this.hinhThucDauTu = hinhThucDauTu;
        this.phamViCungCap = phamViCungCap;
        this.phanTichSwoot = phanTichSwoot;
        this.soTienDac = soTienDac;
        this.hopDongDac = hopDongDac;
        this.mucTieuDac = mucTieuDac;
        this.thucTeDac = thucTeDac;
        this.soTienPac = soTienPac;
        this.hopDongPac = hopDongPac;
        this.mucTieuPac = mucTieuPac;
        this.thucTePac = thucTePac;
        this.soTienFac = soTienFac;
        this.hopDongFac = hopDongFac;
        this.mucTieuFac = mucTieuFac;
        this.thucTeFac = thucTeFac;
        this.tongGiaTriThucTe = tongGiaTriThucTe;
        this.soTienTamUng = soTienTamUng;
        this.keHoachTamUng = keHoachTamUng;
        this.tienDoChung = tienDoChung;
        this.generalIssue = generalIssue;
        this.solution = solution;
        this.keHoachTuanNay = keHoachTuanNay;
        this.keHoachTuanSau = keHoachTuanSau;
        this.ketQuaTuanTruoc = ketQuaTuanTruoc;
        this.ketQuaTuanNay = ketQuaTuanNay;
    }

}
