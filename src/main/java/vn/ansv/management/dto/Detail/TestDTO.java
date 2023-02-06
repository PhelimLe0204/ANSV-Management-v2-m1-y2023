package vn.ansv.management.dto.Detail;

import java.time.Duration;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.util.Locale;

import javax.persistence.Transient;

public class TestDTO {
    private Long id;

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

    // calculated field = transient, not exist in MySql
    @Transient
    private String chenhLechDac; // "Chênh lệch DAC" is calculated from "hợp đồng DAC", "mục tiêu DAC" và
                                 // "thực tế DAC"
    @Transient
    private String chenhLechPac; // "Chênh lệch PAC" is calculated from "hợp đồng PAC", "mục tiêu PAC" và
                                 // "thực tế PAC"
    @Transient
    private String chenhLechFac; // "Chênh lệch FAC" is calculated from "hợp đồng FAC", "mục tiêu FAC" và
                                 // "thực tế FAC"

    public String tinhNgayChenhLech(String dateHopDong, String dateMucTieu, String dateThucTe) {
        DateTimeFormatter dateFormatter = new DateTimeFormatterBuilder()
                .parseCaseInsensitive()
                .appendPattern("dd/MM/uuuu")
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
                    result = "Đã chậm " + Math.abs(dif) + " ngày";
                }
            } else {
                thuc_te = LocalDate.parse(dateThucTe, dateFormatter);
                long dif = Duration.between(thuc_te.atStartOfDay(), muc_tieu.atStartOfDay()).toDays();
                if (dif > 0) {
                    result = "Sớm " + Math.abs(dif) + " ngày";
                }
                if (dif == 0) {
                    result = "Đúng hạn";
                }
                if (dif < 0) {
                    result = "Bị chậm " + Math.abs(dif) + " ngày";
                }
            }
        }

        return result;
    }

    public TestDTO(Long id, String soTienDac, String hopDongDac, String mucTieuDac,
            String thucTeDac, String soTienPac, String hopDongPac, String mucTieuPac, String thucTePac,
            String soTienFac, String hopDongFac, String mucTieuFac, String thucTeFac, String tongGiaTriThucTe,
            String soTienTamUng, String keHoachTamUng, String chenhLechDac, String chenhLechPac, String chenhLechFac) {
        this.id = id;
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
        this.chenhLechDac = chenhLechDac;
        this.chenhLechPac = chenhLechPac;
        this.chenhLechFac = chenhLechFac;
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSoTienDac() {
        return this.soTienDac;
    }

    public void setSoTienDac(String soTienDac) {
        this.soTienDac = soTienDac;
    }

    public String getHopDongDac() {
        return this.hopDongDac;
    }

    public void setHopDongDac(String hopDongDac) {
        this.hopDongDac = hopDongDac;
    }

    public String getMucTieuDac() {
        return this.mucTieuDac;
    }

    public void setMucTieuDac(String mucTieuDac) {
        this.mucTieuDac = mucTieuDac;
    }

    public String getThucTeDac() {
        return this.thucTeDac;
    }

    public void setThucTeDac(String thucTeDac) {
        this.thucTeDac = thucTeDac;
    }

    public String getSoTienPac() {
        return this.soTienPac;
    }

    public void setSoTienPac(String soTienPac) {
        this.soTienPac = soTienPac;
    }

    public String getHopDongPac() {
        return this.hopDongPac;
    }

    public void setHopDongPac(String hopDongPac) {
        this.hopDongPac = hopDongPac;
    }

    public String getMucTieuPac() {
        return this.mucTieuPac;
    }

    public void setMucTieuPac(String mucTieuPac) {
        this.mucTieuPac = mucTieuPac;
    }

    public String getThucTePac() {
        return this.thucTePac;
    }

    public void setThucTePac(String thucTePac) {
        this.thucTePac = thucTePac;
    }

    public String getSoTienFac() {
        return this.soTienFac;
    }

    public void setSoTienFac(String soTienFac) {
        this.soTienFac = soTienFac;
    }

    public String getHopDongFac() {
        return this.hopDongFac;
    }

    public void setHopDongFac(String hopDongFac) {
        this.hopDongFac = hopDongFac;
    }

    public String getMucTieuFac() {
        return this.mucTieuFac;
    }

    public void setMucTieuFac(String mucTieuFac) {
        this.mucTieuFac = mucTieuFac;
    }

    public String getThucTeFac() {
        return this.thucTeFac;
    }

    public void setThucTeFac(String thucTeFac) {
        this.thucTeFac = thucTeFac;
    }

    public String getTongGiaTriThucTe() {
        return this.tongGiaTriThucTe;
    }

    public void setTongGiaTriThucTe(String tongGiaTriThucTe) {
        this.tongGiaTriThucTe = tongGiaTriThucTe;
    }

    public String getSoTienTamUng() {
        return this.soTienTamUng;
    }

    public void setSoTienTamUng(String soTienTamUng) {
        this.soTienTamUng = soTienTamUng;
    }

    public String getKeHoachTamUng() {
        return this.keHoachTamUng;
    }

    public void setKeHoachTamUng(String keHoachTamUng) {
        this.keHoachTamUng = keHoachTamUng;
    }

    public String getChenhLechDac() {
        return tinhNgayChenhLech(hopDongDac, mucTieuDac, thucTeDac);
    }

    // public void setChenhLechDac(String chenhLechDac) {
    // this.chenhLechDac = chenhLechDac;
    // }

    public String getChenhLechPac() {
        return tinhNgayChenhLech(hopDongPac, mucTieuPac, thucTePac);
    }

    // public void setChenhLechPac(String chenhLechPac) {
    // this.chenhLechPac = chenhLechPac;
    // }

    public String getChenhLechFac() {
        return tinhNgayChenhLech(hopDongFac, mucTieuFac, thucTeFac);
    }

    // public void setChenhLechFac(String chenhLechFac) {
    // this.chenhLechFac = chenhLechFac;
    // }

}
