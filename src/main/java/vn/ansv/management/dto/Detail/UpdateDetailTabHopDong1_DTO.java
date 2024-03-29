package vn.ansv.management.dto.Detail;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UpdateDetailTabHopDong1_DTO {
    private Long id;
    private String ngayKy;
    private String ngayHieuLuc;
    private String ngayKetThuc;
    private String note;
    private String createdBy;
    private String modifiedBy;

    public UpdateDetailTabHopDong1_DTO(Long id, String ngayKy, String ngayHieuLuc, String ngayKetThuc, String note) {
        this.id = id;
        this.ngayKy = ngayKy;
        this.ngayHieuLuc = ngayHieuLuc;
        this.ngayKetThuc = ngayKetThuc;
        this.note = note;
    }

}
