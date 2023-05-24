package vn.ansv.management.dto.Detail;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SupportCptgDTO {
    private String soTien;
    private String hopDong;
    private String mucTieu;
    private String thucTe;
    private String note;

    public SupportCptgDTO() {
    }

    public SupportCptgDTO(String soTien, String hopDong, String mucTieu, String thucTe, String note) {
        this.soTien = soTien;
        this.hopDong = hopDong;
        this.mucTieu = mucTieu;
        this.thucTe = thucTe;
        this.note = note;
    }

}
