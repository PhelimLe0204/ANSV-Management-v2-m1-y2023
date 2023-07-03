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
public class HopDongDTO {
    private Long id;

    private String ngayKy;
    private String ngayHieuLuc;
    private String ngayKetThuc;
    private String note;
    private String chenhLech;

    public String tinhNgayChenhLech(String ngayHieuLuc, String ngayKetThuc) {
        DateTimeFormatter dateFormatter = new DateTimeFormatterBuilder()
                .parseCaseInsensitive()
                .appendPattern("dd / MM / uuuu")
                .toFormatter(Locale.ENGLISH);
        String result = null;

        if ((ngayHieuLuc == null || ngayHieuLuc.isEmpty() || ngayHieuLuc.trim().isEmpty())
                || (ngayKetThuc == null || ngayKetThuc.isEmpty() || ngayKetThuc.trim().isEmpty())) {
            return result;
        }

        LocalDate startDate = LocalDate.parse(ngayHieuLuc, dateFormatter);
        LocalDate endDate = LocalDate.parse(ngayKetThuc, dateFormatter);

        Long dif = Duration.between(startDate.atStartOfDay(), endDate.atStartOfDay()).toDays();

        if (dif >= 0) {
            result = "Còn " + Math.abs(dif) + " ngày";
        }

        if (dif == 0) {
            result = "Đã hết hiệu lực";
        }

        return result;
    }

    public HopDongDTO(Long id, String ngayKy, String ngayHieuLuc, String ngayKetThuc, String note) {
        this.id = id;
        this.ngayKy = ngayKy;
        this.ngayHieuLuc = ngayHieuLuc;
        this.ngayKetThuc = ngayKetThuc;
        this.note = note;
        this.chenhLech = tinhNgayChenhLech(ngayHieuLuc, ngayKetThuc);
    }
}
