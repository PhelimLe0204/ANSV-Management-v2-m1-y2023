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
public class SupportBaoLanhHopDongDTO {
    private Long id; // ID của bảng bảo lãnh thực hiện truy vấn

    private String ngayPhatHanh; // Ngày phát hành bảo lãnh thực hiện hợp đồng
    private String ngayHetHan; // Ngày hết hạn bảo lãnh thực hiện hợp đồng
    private String note; // Ghi chú bảo lãnh thực hiện hợp đồng
    private String chenhLech;

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

    public SupportBaoLanhHopDongDTO(Long id, String ngayPhatHanh, String ngayHetHan, String note) {
        this.id = id;
        this.ngayPhatHanh = ngayPhatHanh;
        this.ngayHetHan = ngayHetHan;
        this.note = note;
        this.chenhLech = tinhNgayChenhLech(ngayPhatHanh, ngayHetHan);
    }

}
