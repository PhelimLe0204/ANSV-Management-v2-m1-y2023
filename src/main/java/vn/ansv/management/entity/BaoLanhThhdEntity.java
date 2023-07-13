package vn.ansv.management.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.ColumnResult;
import javax.persistence.ConstructorResult;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedNativeQuery;
import javax.persistence.SqlResultSetMapping;
import javax.persistence.Table;

import vn.ansv.management.dto.Detail.DanhSachGiaHanDTO;
import vn.ansv.management.dto.Detail.SupportBaoLanhHopDongDTO;

/* ===== BaoLanhThhdRepository.getListGiaHan() ===== */
@NamedNativeQuery(name = "BaoLanhThhdEntity.getListGiaHan", query = "SELECT "
        + "blthhd.id, blthhd.ngay_phat_hanh AS ngayPhatHanh, blthhd.ngay_het_han AS ngayHetHan, blthhd.note, "
        + "blthhd.modified_by AS modifiedBy, blthhd.modified_at AS modifiedAt "
        + "FROM bao_lanh_thhd AS blthhd "
        + "INNER JOIN project AS p ON blthhd.project_id = p.id "
        + "INNER JOIN project_report AS pr ON p.id = pr.project_id "
        + "WHERE pr.id = :projectReportId AND pr.enabled = :enabled "
        + "ORDER BY str_to_date(`ngayPhatHanh`, '%d / %m / %Y')", resultSetMapping = "Mapping.DanhSachGiaHanDTO")

/* ===== BaoLanhThhdRepository.findDetailTabHopDongByReport() ===== */
@NamedNativeQuery(name = "BaoLanhThhdEntity.findDetailTabHopDongByReport", query = "SELECT "
        + "blthhd.id, blthhd.ngay_phat_hanh AS ngayPhatHanh, blthhd.ngay_het_han AS ngayHetHan, blthhd.note "
        + "FROM bao_lanh_thhd AS blthhd "
        + "INNER JOIN project AS p ON blthhd.project_id = p.id "
        + "INNER JOIN project_report AS pr ON p.id = pr.project_id "
        + "WHERE pr.id = :projectReportId AND pr.enabled = :enabled "
        + "ORDER BY str_to_date(`ngayPhatHanh`, '%d / %m / %Y') DESC LIMIT 1", resultSetMapping = "Mapping.SupportBaoLanhHopDongDTO")

/* ===== BaoLanhThhdRepository.findDetailTabHopDongById() ===== */
@NamedNativeQuery(name = "BaoLanhThhdEntity.findDetailTabHopDongById", query = "SELECT "
        + "blthhd.id, blthhd.ngay_phat_hanh AS ngayPhatHanh, blthhd.ngay_het_han AS ngayHetHan, blthhd.note "
        + "FROM bao_lanh_thhd AS blthhd "
        + "WHERE blthhd.id = :id", resultSetMapping = "Mapping.SupportBaoLanhHopDongDTO")

/* ===== Set mapping: SupportBaoLanhHopDongDTO ===== */
@SqlResultSetMapping(name = "Mapping.SupportBaoLanhHopDongDTO", classes = @ConstructorResult(targetClass = SupportBaoLanhHopDongDTO.class, columns = {
        @ColumnResult(name = "id", type = Long.class),
        @ColumnResult(name = "ngayPhatHanh", type = String.class),
        @ColumnResult(name = "ngayHetHan", type = String.class),
        @ColumnResult(name = "note", type = String.class) }))

/* ===== Set mapping: DanhSachGiaHanDTO ===== */
@SqlResultSetMapping(name = "Mapping.DanhSachGiaHanDTO", classes = @ConstructorResult(targetClass = DanhSachGiaHanDTO.class, columns = {
        @ColumnResult(name = "id", type = Long.class),
        @ColumnResult(name = "ngayPhatHanh", type = String.class),
        @ColumnResult(name = "ngayHetHan", type = String.class),
        @ColumnResult(name = "note", type = String.class),
        @ColumnResult(name = "modifiedBy", type = String.class),
        @ColumnResult(name = "modifiedAt", type = Date.class) }))

@Entity
@Table(name = "bao_lanh_thhd")
public class BaoLanhThhdEntity extends BaseEntity {
    @Column(name = "ngay_phat_hanh")
    private String ngayPhatHanh;

    @Column(name = "ngay_het_han")
    private String ngayHetHan;

    @Column(name = "note")
    private String note;

    @ManyToOne
    /*
     * @JoinColumn(name =
     * "khóa ngoại trong bảng 'bao_lanh_thhd' kết nối với bảng 'project' (project_id)"
     * )
     */
    @JoinColumn(name = "project_id")
    private ProjectEntity project; // nhiều 'bao_lanh_thhd' thuộc 1 'project' => hứng 1 bản ghi

    public String getNgayPhatHanh() {
        return this.ngayPhatHanh;
    }

    public void setNgayPhatHanh(String ngayPhatHanh) {
        this.ngayPhatHanh = ngayPhatHanh;
    }

    public String getNgayHetHan() {
        return this.ngayHetHan;
    }

    public void setNgayHetHan(String ngayHetHan) {
        this.ngayHetHan = ngayHetHan;
    }

    public String getNote() {
        return this.note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public ProjectEntity getProject() {
        return this.project;
    }

    public void setProject(ProjectEntity project) {
        this.project = project;
    }

}
