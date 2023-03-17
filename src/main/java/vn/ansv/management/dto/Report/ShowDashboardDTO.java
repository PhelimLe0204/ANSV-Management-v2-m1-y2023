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

    private String generalIssue;
    private String keHoachTuanNay;
    private String keHoachTuanSau;
    private String ketQuaTuanNay;
    private String solution;

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

    private String chenhLechDac;
    private String chenhLechPac;
    private String chenhLechFac;

    public String tinhNgayChenhLech(String dateHopDong, String dateMucTieu, String dateThucTe) {
        DateTimeFormatter dateFormatter = new DateTimeFormatterBuilder()
                .parseCaseInsensitive()
                .appendPattern("dd / MM / uuuu")
                .toFormatter(Locale.ENGLISH);
        String result = null;

        if (dateHopDong == null && dateMucTieu == null) {
            return result;
        }

        LocalDate muc_tieu = null;
        LocalDate thuc_te = null;

        if (dateMucTieu != null) {
            muc_tieu = LocalDate.parse(dateMucTieu, dateFormatter);

            if (dateThucTe == null) {
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

    public ShowDashboardDTO(Long id, String jobName, String customerName, String amName, String pmName,
            String generalIssue, String keHoachTuanNay, String keHoachTuanSau, String ketQuaTuanNay, String solution,
            String soTienDac, String hopDongDac, String mucTieuDac, String thucTeDac, String soTienPac,
            String hopDongPac, String mucTieuPac, String thucTePac, String soTienFac, String hopDongFac,
            String mucTieuFac, String thucTeFac, String tongGiaTriThucTe, String soTienTamUng, String keHoachTamUng) {
        this.id = id;
        this.jobName = jobName;
        this.customerName = customerName;
        this.amName = amName;
        this.pmName = pmName;
        this.generalIssue = generalIssue;
        this.keHoachTuanNay = keHoachTuanNay;
        this.keHoachTuanSau = keHoachTuanSau;
        this.ketQuaTuanNay = ketQuaTuanNay;
        this.solution = solution;
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
        this.chenhLechDac = tinhNgayChenhLech(hopDongDac, mucTieuDac, thucTeDac);
        this.chenhLechPac = tinhNgayChenhLech(hopDongPac, mucTieuPac, thucTePac);
        this.chenhLechFac = tinhNgayChenhLech(hopDongFac, mucTieuFac, thucTeFac);
    }

}
