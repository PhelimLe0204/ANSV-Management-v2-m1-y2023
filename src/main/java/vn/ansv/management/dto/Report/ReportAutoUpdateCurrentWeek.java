package vn.ansv.management.dto.Report;

import java.util.Date;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ReportAutoUpdateCurrentWeek {
    // (8)
    private Long id;
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

    // Tab 3 (25)
    private String soTienGiaoHang;
    private String hopDongGiaoHang;
    private String mucTieuGiaoHang;
    private String thucTeGiaoHang;
    private String noteGiaoHang;
    private String soTienDac;
    private String hopDongDac;
    private String mucTieuDac;
    private String thucTeDac;
    private String noteDac;
    private String soTienPac;
    private String hopDongPac;
    private String mucTieuPac;
    private String thucTePac;
    private String notePac;
    private String soTienFac;
    private String hopDongFac;
    private String mucTieuFac;
    private String thucTeFac;
    private String noteFac;
    private String tongGiaTriThucTe;
    private String noteTongGiaTri;
    private String soTienTamUng;
    private String keHoachTamUng;
    private String noteTamUng;

    // Tab 4 (7)
    private String tienDoChung;
    private String generalIssue;
    private String solution;
    private String keHoachTuanNay;
    private String keHoachTuanSau;
    private String ketQuaTuanTruoc;
    private String ketQuaTuanNay;

    public ReportAutoUpdateCurrentWeek() {
    }

    public ReportAutoUpdateCurrentWeek(Long id, String uid, Long amId, Long pmId, Long amManagerId, Long pmManagerId,
            String createdBy, Date createdDate, Long projectId, Long projectTypeId, Long projectPriorityId,
            Long projectStatusId, int week, int year, String maHopDong, String maKeToan, Long currencyUnitId,
            String jobName, Long customerId, String description, Integer mucDoKhaThi, String tongMucDauTuDuKien,
            String hinhThucDauTu, String phamViCungCap, String phanTichSwoot, String soTienGiaoHang,
            String hopDongGiaoHang, String mucTieuGiaoHang, String thucTeGiaoHang, String noteGiaoHang,
            String soTienDac, String hopDongDac, String mucTieuDac, String thucTeDac, String noteDac, String soTienPac,
            String hopDongPac, String mucTieuPac, String thucTePac, String notePac, String soTienFac, String hopDongFac,
            String mucTieuFac, String thucTeFac, String noteFac, String tongGiaTriThucTe, String noteTongGiaTri,
            String soTienTamUng, String keHoachTamUng, String noteTamUng, String tienDoChung, String generalIssue,
            String solution, String keHoachTuanNay, String keHoachTuanSau, String ketQuaTuanTruoc,
            String ketQuaTuanNay) {
        this.id = id;
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
        this.soTienGiaoHang = soTienGiaoHang;
        this.hopDongGiaoHang = hopDongGiaoHang;
        this.mucTieuGiaoHang = mucTieuGiaoHang;
        this.thucTeGiaoHang = thucTeGiaoHang;
        this.noteGiaoHang = noteGiaoHang;
        this.soTienDac = soTienDac;
        this.hopDongDac = hopDongDac;
        this.mucTieuDac = mucTieuDac;
        this.thucTeDac = thucTeDac;
        this.noteDac = noteDac;
        this.soTienPac = soTienPac;
        this.hopDongPac = hopDongPac;
        this.mucTieuPac = mucTieuPac;
        this.thucTePac = thucTePac;
        this.notePac = notePac;
        this.soTienFac = soTienFac;
        this.hopDongFac = hopDongFac;
        this.mucTieuFac = mucTieuFac;
        this.thucTeFac = thucTeFac;
        this.noteFac = noteFac;
        this.tongGiaTriThucTe = tongGiaTriThucTe;
        this.noteTongGiaTri = noteTongGiaTri;
        this.soTienTamUng = soTienTamUng;
        this.keHoachTamUng = keHoachTamUng;
        this.noteTamUng = noteTamUng;
        this.tienDoChung = tienDoChung;
        this.generalIssue = generalIssue;
        this.solution = solution;
        this.keHoachTuanNay = keHoachTuanNay;
        this.keHoachTuanSau = keHoachTuanSau;
        this.ketQuaTuanTruoc = ketQuaTuanTruoc;
        this.ketQuaTuanNay = ketQuaTuanNay;
    }

}
