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
public class ReportDetailTabCptgDTO {
    // DTO for tab "Thoi gian & chi phi" (detail + update)
    private Long id;

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

    private String soTienDac2;
    private String hopDongDac2;
    private String mucTieuDac2;
    private String thucTeDac2;
    private String noteDac2;

    private String soTienDac3;
    private String hopDongDac3;
    private String mucTieuDac3;
    private String thucTeDac3;
    private String noteDac3;

    private String soTienDac4;
    private String hopDongDac4;
    private String mucTieuDac4;
    private String thucTeDac4;
    private String noteDac4;

    private String soTienDac5;
    private String hopDongDac5;
    private String mucTieuDac5;
    private String thucTeDac5;
    private String noteDac5;

    private String soTienPac;
    private String hopDongPac;
    private String mucTieuPac;
    private String thucTePac;
    private String notePac;

    private String soTienPac2;
    private String hopDongPac2;
    private String mucTieuPac2;
    private String thucTePac2;
    private String notePac2;

    private String soTienPac3;
    private String hopDongPac3;
    private String mucTieuPac3;
    private String thucTePac3;
    private String notePac3;

    private String soTienPac4;
    private String hopDongPac4;
    private String mucTieuPac4;
    private String thucTePac4;
    private String notePac4;

    private String soTienPac5;
    private String hopDongPac5;
    private String mucTieuPac5;
    private String thucTePac5;
    private String notePac5;

    private String soTienFac;
    private String hopDongFac;
    private String mucTieuFac;
    private String thucTeFac;
    private String noteFac;

    private String soTienFac2;
    private String hopDongFac2;
    private String mucTieuFac2;
    private String thucTeFac2;
    private String noteFac2;

    private String soTienFac3;
    private String hopDongFac3;
    private String mucTieuFac3;
    private String thucTeFac3;
    private String noteFac3;

    private String soTienFac4;
    private String hopDongFac4;
    private String mucTieuFac4;
    private String thucTeFac4;
    private String noteFac4;

    private String soTienFac5;
    private String hopDongFac5;
    private String mucTieuFac5;
    private String thucTeFac5;
    private String noteFac5;

    private String tongGiaTriThucTe;
    private String noteTongGiaTri;

    private String soTienTamUng;
    private String keHoachTamUng;
    private String noteTamUng;

    private String chenhLechGiaoHang;

    private String chenhLechDac;
    private String chenhLechDac2;
    private String chenhLechDac3;
    private String chenhLechDac4;
    private String chenhLechDac5;

    private String chenhLechPac;
    private String chenhLechPac2;
    private String chenhLechPac3;
    private String chenhLechPac4;
    private String chenhLechPac5;

    private String chenhLechFac;
    private String chenhLechFac2;
    private String chenhLechFac3;
    private String chenhLechFac4;
    private String chenhLechFac5;

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

    public ReportDetailTabCptgDTO(Long id, String soTienGiaoHang, String hopDongGiaoHang, String mucTieuGiaoHang,
            String thucTeGiaoHang, String noteGiaoHang, String soTienDac, String hopDongDac, String mucTieuDac,
            String thucTeDac, String noteDac, String soTienDac2, String hopDongDac2, String mucTieuDac2,
            String thucTeDac2, String noteDac2, String soTienDac3, String hopDongDac3, String mucTieuDac3,
            String thucTeDac3, String noteDac3, String soTienDac4, String hopDongDac4, String mucTieuDac4,
            String thucTeDac4, String noteDac4, String soTienDac5, String hopDongDac5, String mucTieuDac5,
            String thucTeDac5, String noteDac5, String soTienPac, String hopDongPac, String mucTieuPac,
            String thucTePac, String notePac, String soTienPac2, String hopDongPac2, String mucTieuPac2,
            String thucTePac2, String notePac2, String soTienPac3, String hopDongPac3, String mucTieuPac3,
            String thucTePac3, String notePac3, String soTienPac4, String hopDongPac4, String mucTieuPac4,
            String thucTePac4, String notePac4, String soTienPac5, String hopDongPac5, String mucTieuPac5,
            String thucTePac5, String notePac5, String soTienFac, String hopDongFac, String mucTieuFac,
            String thucTeFac, String noteFac, String soTienFac2, String hopDongFac2, String mucTieuFac2,
            String thucTeFac2, String noteFac2, String soTienFac3, String hopDongFac3, String mucTieuFac3,
            String thucTeFac3, String noteFac3, String soTienFac4, String hopDongFac4, String mucTieuFac4,
            String thucTeFac4, String noteFac4, String soTienFac5, String hopDongFac5, String mucTieuFac5,
            String thucTeFac5, String noteFac5, String tongGiaTriThucTe, String noteTongGiaTri, String soTienTamUng,
            String keHoachTamUng, String noteTamUng) {
        this.id = id;

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

        this.soTienDac2 = soTienDac2;
        this.hopDongDac2 = hopDongDac2;
        this.mucTieuDac2 = mucTieuDac2;
        this.thucTeDac2 = thucTeDac2;
        this.noteDac2 = noteDac2;

        this.soTienDac3 = soTienDac3;
        this.hopDongDac3 = hopDongDac3;
        this.mucTieuDac3 = mucTieuDac3;
        this.thucTeDac3 = thucTeDac3;
        this.noteDac3 = noteDac3;

        this.soTienDac4 = soTienDac4;
        this.hopDongDac4 = hopDongDac4;
        this.mucTieuDac4 = mucTieuDac4;
        this.thucTeDac4 = thucTeDac4;
        this.noteDac4 = noteDac4;

        this.soTienDac5 = soTienDac5;
        this.hopDongDac5 = hopDongDac5;
        this.mucTieuDac5 = mucTieuDac5;
        this.thucTeDac5 = thucTeDac5;
        this.noteDac5 = noteDac5;

        this.soTienPac = soTienPac;
        this.hopDongPac = hopDongPac;
        this.mucTieuPac = mucTieuPac;
        this.thucTePac = thucTePac;
        this.notePac = notePac;

        this.soTienPac2 = soTienPac2;
        this.hopDongPac2 = hopDongPac2;
        this.mucTieuPac2 = mucTieuPac2;
        this.thucTePac2 = thucTePac2;
        this.notePac2 = notePac2;

        this.soTienPac3 = soTienPac3;
        this.hopDongPac3 = hopDongPac3;
        this.mucTieuPac3 = mucTieuPac3;
        this.thucTePac3 = thucTePac3;
        this.notePac3 = notePac3;

        this.soTienPac4 = soTienPac4;
        this.hopDongPac4 = hopDongPac4;
        this.mucTieuPac4 = mucTieuPac4;
        this.thucTePac4 = thucTePac4;
        this.notePac4 = notePac4;

        this.soTienPac5 = soTienPac5;
        this.hopDongPac5 = hopDongPac5;
        this.mucTieuPac5 = mucTieuPac5;
        this.thucTePac5 = thucTePac5;
        this.notePac5 = notePac5;

        this.soTienFac = soTienFac;
        this.hopDongFac = hopDongFac;
        this.mucTieuFac = mucTieuFac;
        this.thucTeFac = thucTeFac;
        this.noteFac = noteFac;

        this.soTienFac2 = soTienFac2;
        this.hopDongFac2 = hopDongFac2;
        this.mucTieuFac2 = mucTieuFac2;
        this.thucTeFac2 = thucTeFac2;
        this.noteFac2 = noteFac2;

        this.soTienFac3 = soTienFac3;
        this.hopDongFac3 = hopDongFac3;
        this.mucTieuFac3 = mucTieuFac3;
        this.thucTeFac3 = thucTeFac3;
        this.noteFac3 = noteFac3;

        this.soTienFac4 = soTienFac4;
        this.hopDongFac4 = hopDongFac4;
        this.mucTieuFac4 = mucTieuFac4;
        this.thucTeFac4 = thucTeFac4;
        this.noteFac4 = noteFac4;

        this.soTienFac5 = soTienFac5;
        this.hopDongFac5 = hopDongFac5;
        this.mucTieuFac5 = mucTieuFac5;
        this.thucTeFac5 = thucTeFac5;
        this.noteFac5 = noteFac5;

        this.tongGiaTriThucTe = tongGiaTriThucTe;
        this.noteTongGiaTri = noteTongGiaTri;
        this.soTienTamUng = soTienTamUng;
        this.keHoachTamUng = keHoachTamUng;
        this.noteTamUng = noteTamUng;

        this.chenhLechGiaoHang = tinhNgayChenhLech(hopDongGiaoHang, mucTieuGiaoHang, thucTeGiaoHang);

        this.chenhLechDac = tinhNgayChenhLech(hopDongDac, mucTieuDac, thucTeDac);
        this.chenhLechDac2 = tinhNgayChenhLech(hopDongDac2, mucTieuDac2, thucTeDac2);
        this.chenhLechDac3 = tinhNgayChenhLech(hopDongDac3, mucTieuDac3, thucTeDac3);
        this.chenhLechDac4 = tinhNgayChenhLech(hopDongDac4, mucTieuDac4, thucTeDac4);
        this.chenhLechDac5 = tinhNgayChenhLech(hopDongDac5, mucTieuDac5, thucTeDac5);

        this.chenhLechPac = tinhNgayChenhLech(hopDongPac, mucTieuPac, thucTePac);
        this.chenhLechPac2 = tinhNgayChenhLech(hopDongPac2, mucTieuPac2, thucTePac2);
        this.chenhLechPac3 = tinhNgayChenhLech(hopDongPac3, mucTieuPac3, thucTePac3);
        this.chenhLechPac4 = tinhNgayChenhLech(hopDongPac4, mucTieuPac4, thucTePac4);
        this.chenhLechPac5 = tinhNgayChenhLech(hopDongPac5, mucTieuPac5, thucTePac5);

        this.chenhLechFac = tinhNgayChenhLech(hopDongFac, mucTieuFac, thucTeFac);
        this.chenhLechFac2 = tinhNgayChenhLech(hopDongFac2, mucTieuFac2, thucTeFac2);
        this.chenhLechFac3 = tinhNgayChenhLech(hopDongFac3, mucTieuFac3, thucTeFac3);
        this.chenhLechFac4 = tinhNgayChenhLech(hopDongFac4, mucTieuFac4, thucTeFac4);
        this.chenhLechFac5 = tinhNgayChenhLech(hopDongFac5, mucTieuFac5, thucTeFac5);
    }

    // public List<SupportCptgDTO> transferData(String soTienThem, String
    // hopDongThem, String mucTieuThem,
    // String thucTeThem, String noteThem) {
    // List<SupportCptgDTO> listUserLdap = new ArrayList<SupportCptgDTO>();
    // SupportCptgDTO data = new SupportCptgDTO();

    // if (soTienThem != null) {
    // int data_SoTien_count = Integer.parseInt(soTienThem.substring(0, 2));

    // for (int i = 0; i < data_SoTien_count; i++) {

    // }
    // }

    // if (hopDongThem != null) {
    // int data_HopDong_count = Integer.parseInt(hopDongThem.substring(0, 2));

    // for (int i = 0; i < data_HopDong_count; i++) {

    // }
    // }

    // if (mucTieuThem != null) {
    // int data_MucTieu_count = Integer.parseInt(mucTieuThem.substring(0, 2));

    // for (int i = 0; i < data_MucTieu_count; i++) {

    // }
    // }

    // if (thucTeThem != null) {
    // int data_ThucTe_count = Integer.parseInt(thucTeThem.substring(0, 2));

    // for (int i = 0; i < data_ThucTe_count; i++) {

    // }
    // }

    // if (noteThem != null) {
    // int data_note_count = Integer.parseInt(noteThem.substring(0, 2));

    // for (int i = 0; i < data_note_count; i++) {

    // }
    // }

    // return listUserLdap;
    // }

}
