package vn.ansv.management.dto.Export;

import java.time.Duration;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.util.Locale;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ExportTrienKhaiDTO {
    private Long id;
    private String jobName;
    private String maHopDong;
    private String maKeToan;
    private String customerName;
    private String tongGiaTriThucTe;
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
    private String tienDoChung;
    private String generalIssue;
    private String solution;
    private String priorityName;
    private String statusName;
    private String amName;
    private String pmName;
    private String pmManagerName;
    private String ketQuaTuanTruoc;
    private String ketQuaTuanNay;
    private String keHoachTuanNay;
    private String keHoachTuanSau;
    private String chenhLechDac;
    private String chenhLechPac;
    private String chenhLechFac;

    public ExportTrienKhaiDTO() {
    }

    public ExportTrienKhaiDTO(Long id, String jobName, String maHopDong, String maKeToan, String customerName,
            String tongGiaTriThucTe, String soTienDac, String hopDongDac, String mucTieuDac, String thucTeDac,
            String soTienPac, String hopDongPac, String mucTieuPac, String thucTePac, String soTienFac,
            String hopDongFac, String mucTieuFac, String thucTeFac, String tienDoChung, String generalIssue,
            String solution, String priorityName, String statusName, String amName, String pmName,
            String pmManagerName, String ketQuaTuanTruoc, String ketQuaTuanNay, String keHoachTuanNay,
            String keHoachTuanSau) {
        this.id = id;
        this.jobName = jobName;
        this.maHopDong = maHopDong;
        this.maKeToan = maKeToan;
        this.customerName = customerName;
        this.tongGiaTriThucTe = tongGiaTriThucTe;
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
        this.tienDoChung = tienDoChung;
        this.generalIssue = generalIssue;
        this.solution = solution;
        this.priorityName = priorityName;
        this.statusName = statusName;
        this.amName = amName;
        this.pmName = pmName;
        this.pmManagerName = pmManagerName;
        this.ketQuaTuanTruoc = ketQuaTuanTruoc;
        this.ketQuaTuanNay = ketQuaTuanNay;
        this.keHoachTuanNay = keHoachTuanNay;
        this.keHoachTuanSau = keHoachTuanSau;
        this.chenhLechDac = tinhNgayChenhLech(hopDongDac, mucTieuDac, thucTeDac);
        this.chenhLechPac = tinhNgayChenhLech(hopDongPac, mucTieuPac, thucTePac);
        this.chenhLechFac = tinhNgayChenhLech(hopDongFac, mucTieuFac, thucTeFac);
    }

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

        if (dateMucTieu != null && dateMucTieu.length() > 0) {
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

}
