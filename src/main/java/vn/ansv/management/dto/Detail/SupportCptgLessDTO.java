package vn.ansv.management.dto.Detail;

import java.time.Duration;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.util.Locale;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SupportCptgLessDTO {
    private Long id;

    private String soTien;
    private String hopDong;
    private String mucTieu;
    private String thucTe;
    private String note;
    private Boolean status;
    private String chenhLech;

    public String tinhNgayChenhLech(String dateHopDong, String dateMucTieu, String dateThucTe) {
        DateTimeFormatter dateFormatter = new DateTimeFormatterBuilder()
                .parseCaseInsensitive()
                .appendPattern("dd / MM / uuuu")
                .toFormatter(Locale.ENGLISH);
        String result = null;

        if ((dateHopDong == null || dateHopDong.isEmpty() || dateHopDong.trim().isEmpty())
                && (dateMucTieu == null || dateMucTieu.isEmpty() || dateMucTieu.trim().isEmpty())) {
            return result;
        }

        LocalDate muc_tieu = null;
        LocalDate thuc_te = null;

        if (dateMucTieu != null && !dateMucTieu.isEmpty() && !dateMucTieu.trim().isEmpty()) {
            muc_tieu = LocalDate.parse(dateMucTieu, dateFormatter);

            if (dateThucTe == null || dateThucTe.isEmpty() || dateThucTe.trim().isEmpty()) {
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
                this.status = false;
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
                this.status = true;
            }
        }

        return result;
    }

    public SupportCptgLessDTO(Long id, String soTien, String hopDong, String mucTieu, String thucTe, String note) {
        this.id = id;
        this.soTien = soTien;
        this.hopDong = hopDong;
        this.mucTieu = mucTieu;
        this.thucTe = thucTe;
        this.note = note;
        this.chenhLech = tinhNgayChenhLech(hopDong, mucTieu, thucTe);
    }

}
