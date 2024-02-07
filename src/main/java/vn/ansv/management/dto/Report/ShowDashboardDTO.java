package vn.ansv.management.dto.Report;

import java.time.Duration;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.util.Locale;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ShowDashboardDTO {
    private Long id;
    private String jobName;
    private String customerName;
    private String amName;
    private String pmName;
    private String currencyUnit;

    private String generalIssue;
    private String keHoachTuanNay;
    private String keHoachTuanSau;
    private String ketQuaTuanNay;
    private String solution;

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

    private String chenhLechGiaoHang;
    private String chenhLechDac;
    private String chenhLechPac;
    private String chenhLechFac;

    public String tinhNgayChenhLech(String dateHopDong, String dateMucTieu, String dateThucTe) {
        DateTimeFormatter dateFormatter = new DateTimeFormatterBuilder()
                .parseCaseInsensitive()
                .appendPattern("dd / MM / uuuu")
                .toFormatter(Locale.ENGLISH);
        String result = null;

        if ((dateHopDong == null || dateHopDong.length() == 0) && (dateMucTieu == null || dateMucTieu.length() == 0)) {
            return result;
        }

        LocalDate muc_tieu = null;
        LocalDate thuc_te = null;

        if (dateMucTieu != null) {
            muc_tieu = LocalDate.parse(dateMucTieu, dateFormatter);

            if (dateThucTe == null || dateThucTe.length() == 0) {
                // Không có ngày thực tế (chưa hoàn thành công việc)
                thuc_te = LocalDate.now();
                long dif = Duration.between(thuc_te.atStartOfDay(), muc_tieu.atStartOfDay()).toDays();

                if (dif > 0) {
                    result = "Còn " + Math.abs(dif) + " ngày";
                }
                if (dif == 0) {
                    result = "Deadline";
                }
                if (dif < 0) {
                    result = "Quá hạn " + Math.abs(dif) + " ngày";
                }
            } else {
                // Đã có ngày thực tế (hoàn thành công việc)
                thuc_te = LocalDate.parse(dateThucTe, dateFormatter);
                long dif = Duration.between(thuc_te.atStartOfDay(), muc_tieu.atStartOfDay()).toDays();

                if (dif > 0) {
                    result = "Sớm " + Math.abs(dif) + " ngày";
                } else if (dif == 0) {
                    result = "Đúng hạn";
                } else {
                    // dif < 0
                    result = "Chậm " + Math.abs(dif) + " ngày";
                }
            }
        }

        return result;
    }

    public ShowDashboardDTO() {

    }

    public ShowDashboardDTO(Long id, String jobName, String customerName, String amName, String pmName,
            String currencyUnit, String generalIssue, String keHoachTuanNay, String keHoachTuanSau,
            String ketQuaTuanNay, String solution, String soTienGiaoHang, String hopDongGiaoHang,
            String mucTieuGiaoHang, String thucTeGiaoHang, String noteGiaoHang, String soTienDac, String hopDongDac,
            String mucTieuDac, String thucTeDac, String noteDac, String soTienPac, String hopDongPac, String mucTieuPac,
            String thucTePac, String notePac, String soTienFac, String hopDongFac, String mucTieuFac, String thucTeFac,
            String noteFac, String tongGiaTriThucTe, String noteTongGiaTri, String soTienTamUng, String keHoachTamUng,
            String noteTamUng) {
        this.id = id;
        this.jobName = jobName;
        this.customerName = customerName;
        this.amName = amName;
        this.pmName = pmName;
        this.currencyUnit = currencyUnit;
        this.generalIssue = generalIssue;
        this.keHoachTuanNay = keHoachTuanNay;
        this.keHoachTuanSau = keHoachTuanSau;
        this.ketQuaTuanNay = ketQuaTuanNay;
        this.solution = solution;
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
        this.chenhLechGiaoHang = tinhNgayChenhLech(hopDongGiaoHang, mucTieuGiaoHang, thucTeGiaoHang);
        this.chenhLechDac = tinhNgayChenhLech(hopDongDac, mucTieuDac, thucTeDac);
        this.chenhLechPac = tinhNgayChenhLech(hopDongPac, mucTieuPac, thucTePac);
        this.chenhLechFac = tinhNgayChenhLech(hopDongFac, mucTieuFac, thucTeFac);
    }

}
