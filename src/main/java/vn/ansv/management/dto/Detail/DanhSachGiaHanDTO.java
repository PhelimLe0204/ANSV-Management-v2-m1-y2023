package vn.ansv.management.dto.Detail;

import java.time.Duration;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.util.Date;
import java.util.Locale;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DanhSachGiaHanDTO {
    private Long id;

    private String ngayPhatHanh;
    private String ngayHetHan;
    private String note;
    private String chenhLech;
    private String modifiedBy;
    private Date modifiedAt;

    public String tinhNgayChenhLech(String ngayPhatHanh, String ngayHetHan) {
        DateTimeFormatter dateFormatter = new DateTimeFormatterBuilder()
                .parseCaseInsensitive()
                .appendPattern("dd / MM / uuuu")
                .toFormatter(Locale.ENGLISH);
        String result = null;

        if ((ngayPhatHanh == null || ngayPhatHanh.isEmpty() || ngayPhatHanh.trim().isEmpty())
                || (ngayHetHan == null || ngayHetHan.isEmpty() || ngayHetHan.trim().isEmpty())) {
            return result;
        }

        LocalDate startDate = LocalDate.parse(ngayPhatHanh, dateFormatter);
        LocalDate endDate = LocalDate.parse(ngayHetHan, dateFormatter);

        Long dif = Duration.between(startDate.atStartOfDay(), endDate.atStartOfDay()).toDays();

        if (dif >= 0) {
            result = "Còn " + Math.abs(dif) + " ngày";
        }

        if (dif == 0) {
            result = "Đã hết hiệu lực";
        }

        return result;
    }

    public DanhSachGiaHanDTO() {
    }

    public DanhSachGiaHanDTO(Long id, String ngayPhatHanh, String ngayHetHan, String note, String modifiedBy,
            Date modifiedAt) {
        this.id = id;
        this.ngayPhatHanh = ngayPhatHanh;
        this.ngayHetHan = ngayHetHan;
        this.note = note;
        this.chenhLech = tinhNgayChenhLech(ngayPhatHanh, ngayHetHan);
        this.modifiedBy = modifiedBy;
        this.modifiedAt = modifiedAt;
    }
}
