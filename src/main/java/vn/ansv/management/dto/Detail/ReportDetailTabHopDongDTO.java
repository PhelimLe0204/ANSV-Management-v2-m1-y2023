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
public class ReportDetailTabHopDongDTO {
    private Long id;

    private String ngayKy;
    private String ngayHieuLuc;
    private String ngayKetThuc;
    private String ngayMoBLTHHD; // Ngày mở bảo lãnh thực hiện hợp đồng
    private String ngayHetHanBLTHHD; // Ngày hết hạn bảo lãnh thực hiện hợp đồng
    private String noteBLTHHD; // Ghi chú bảo lãnh thực hiện hợp đồng
    private String ngayMoBLTU; // Ngày mở bảo lãnh tạm ứng
    private String ngayHetHanBLTU; // Ngày hết hạn bảo lãnh tạm ứng
    private String noteBLTU; // Ghi chú bảo lãnh tạm ứng
    private String ngayMoBLBH; // Ngày mở bảo lãnh bảo hành
    private String ngayHetHanBLBH; // Ngày hết hạn bảo lãnh bảo hành
    private String noteBLBH; // Ghi chú bảo lãnh bảo hành
    private Long projectId;

    private String chenhLechHieuLuc;
    private String chenhLechBLTHHD;
    private String chenhLechBLTU;
    private String chenhLechBLBH;

    public String tinhNgayChenhLech(String ngayHieuLuc, String ngayHetHan) {
        DateTimeFormatter dateFormatter = new DateTimeFormatterBuilder()
                .parseCaseInsensitive()
                .appendPattern("dd / MM / uuuu")
                .toFormatter(Locale.ENGLISH);
        String result = "Đã hết hiệu lực";

        if ((ngayHieuLuc == null || ngayHieuLuc.isEmpty() || ngayHieuLuc.trim().isEmpty())
                || (ngayHetHan == null || ngayHetHan.isEmpty() || ngayHetHan.trim().isEmpty())) {
            return result;
        }

        LocalDate startDate = LocalDate.parse(ngayHieuLuc, dateFormatter);
        LocalDate endDate = LocalDate.parse(ngayHetHan, dateFormatter);

        Long dif = Duration.between(startDate.atStartOfDay(), endDate.atStartOfDay()).toDays();

        if (dif >= 0) {
            result = "Còn " + Math.abs(dif) + " ngày";
        }

        return result;
    }

    public ReportDetailTabHopDongDTO(Long id, String ngayKy, String ngayHieuLuc, String ngayKetThuc,
            String ngayMoBLTHHD, String ngayHetHanBLTHHD, String noteBLTHHD, String ngayMoBLTU, String ngayHetHanBLTU,
            String noteBLTU, String ngayMoBLBH, String ngayHetHanBLBH, String noteBLBH, Long projectId) {
        this.id = id;
        this.ngayKy = ngayKy;
        this.ngayHieuLuc = ngayHieuLuc;
        this.ngayKetThuc = ngayKetThuc;
        this.ngayMoBLTHHD = ngayMoBLTHHD;
        this.ngayHetHanBLTHHD = ngayHetHanBLTHHD;
        this.noteBLTHHD = noteBLTHHD;
        this.ngayMoBLTU = ngayMoBLTU;
        this.ngayHetHanBLTU = ngayHetHanBLTU;
        this.noteBLTU = noteBLTU;
        this.ngayMoBLBH = ngayMoBLBH;
        this.ngayHetHanBLBH = ngayHetHanBLBH;
        this.noteBLBH = noteBLBH;
        this.projectId = projectId;
        this.chenhLechHieuLuc = tinhNgayChenhLech(ngayHieuLuc, ngayKetThuc);
        this.chenhLechBLTHHD = tinhNgayChenhLech(ngayMoBLTHHD, ngayHetHanBLTHHD);
        this.chenhLechBLTU = tinhNgayChenhLech(ngayMoBLTU, ngayHetHanBLTU);
        this.chenhLechBLBH = tinhNgayChenhLech(ngayMoBLBH, ngayHetHanBLBH);
    }

}
