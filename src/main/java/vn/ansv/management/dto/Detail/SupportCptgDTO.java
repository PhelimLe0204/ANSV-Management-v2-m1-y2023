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
public class SupportCptgDTO {
    private Long id;

    private String soTien;
    private String hopDong;
    private String mucTieu;
    private String thucTe;
    private String note;

    private String soTien2;
    private String hopDong2;
    private String mucTieu2;
    private String thucTe2;
    private String note2;

    private String soTien3;
    private String hopDong3;
    private String mucTieu3;
    private String thucTe3;
    private String note3;

    private String soTien4;
    private String hopDong4;
    private String mucTieu4;
    private String thucTe4;
    private String note4;

    private String soTien5;
    private String hopDong5;
    private String mucTieu5;
    private String thucTe5;
    private String note5;

    private String chenhLech;
    private String chenhLech2;
    private String chenhLech3;
    private String chenhLech4;
    private String chenhLech5;

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

    public SupportCptgDTO() {
    }

    public SupportCptgDTO(Long id, String soTien, String hopDong, String mucTieu, String thucTe, String note,
            String soTien2, String hopDong2, String mucTieu2, String thucTe2, String note2, String soTien3,
            String hopDong3, String mucTieu3, String thucTe3, String note3, String soTien4, String hopDong4,
            String mucTieu4, String thucTe4, String note4, String soTien5, String hopDong5, String mucTieu5,
            String thucTe5, String note5) {
        this.id = id;
        this.soTien = soTien;
        this.hopDong = hopDong;
        this.mucTieu = mucTieu;
        this.thucTe = thucTe;
        this.note = note;
        this.soTien2 = soTien2;
        this.hopDong2 = hopDong2;
        this.mucTieu2 = mucTieu2;
        this.thucTe2 = thucTe2;
        this.note2 = note2;
        this.soTien3 = soTien3;
        this.hopDong3 = hopDong3;
        this.mucTieu3 = mucTieu3;
        this.thucTe3 = thucTe3;
        this.note3 = note3;
        this.soTien4 = soTien4;
        this.hopDong4 = hopDong4;
        this.mucTieu4 = mucTieu4;
        this.thucTe4 = thucTe4;
        this.note4 = note4;
        this.soTien5 = soTien5;
        this.hopDong5 = hopDong5;
        this.mucTieu5 = mucTieu5;
        this.thucTe5 = thucTe5;
        this.note5 = note5;

        this.chenhLech = tinhNgayChenhLech(hopDong, mucTieu, thucTe);
        this.chenhLech2 = tinhNgayChenhLech(hopDong2, mucTieu2, thucTe2);
        this.chenhLech3 = tinhNgayChenhLech(hopDong3, mucTieu3, thucTe3);
        this.chenhLech4 = tinhNgayChenhLech(hopDong4, mucTieu4, thucTe4);
        this.chenhLech5 = tinhNgayChenhLech(hopDong5, mucTieu5, thucTe5);
    }

}
