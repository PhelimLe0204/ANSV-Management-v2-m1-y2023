package vn.ansv.management.entity;

import javax.persistence.Column;
import javax.persistence.ColumnResult;
import javax.persistence.ConstructorResult;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedNativeQuery;
import javax.persistence.SqlResultSetMapping;
import javax.persistence.Table;

import vn.ansv.management.dto.Detail.HopDongDTO;

/* ===== HopDongRepository.findDetailTabHopDongById() ===== */
@NamedNativeQuery(name = "HopDongEntity.findDetailTabHopDongById", query = "SELECT "
        + "hd.id, hd.ngay_ky AS ngayKy, hd.ngay_hieu_luc AS ngayHieuLuc, hd.ngay_ket_thuc AS ngayKetThuc, hd.note "
        + "FROM hop_dong AS hd "
        + "WHERE hd.id = :id", resultSetMapping = "Mapping.HopDongDTO")

/* ===== HopDongRepository.findDetailTabHopDongByReport() ===== */
@NamedNativeQuery(name = "HopDongEntity.findDetailTabHopDongByReport", query = "SELECT "
        + "hd.id, hd.ngay_ky AS ngayKy, hd.ngay_hieu_luc AS ngayHieuLuc, hd.ngay_ket_thuc AS ngayKetThuc, hd.note "
        + "FROM hop_dong AS hd "
        + "INNER JOIN project AS p ON hd.project_id = p.id "
        + "INNER JOIN project_report AS pr ON p.id = pr.project_id "
        + "WHERE pr.id = :projectReportId AND pr.enabled = :enabled "
        + "ORDER BY hd.modified_at DESC LIMIT 1", resultSetMapping = "Mapping.HopDongDTO")

/* ===== Set mapping: HopDongDTO ===== */
@SqlResultSetMapping(name = "Mapping.HopDongDTO", classes = @ConstructorResult(targetClass = HopDongDTO.class, columns = {
        @ColumnResult(name = "id", type = Long.class),
        @ColumnResult(name = "ngayKy", type = String.class),
        @ColumnResult(name = "ngayHieuLuc", type = String.class),
        @ColumnResult(name = "ngayKetThuc", type = String.class),
        @ColumnResult(name = "note", type = String.class) }))

@Entity
@Table(name = "hop_dong")
public class HopDongEntity extends BaseEntity {
    @Column(name = "ngay_ky")
    private String ngayKy;

    @Column(name = "ngay_hieu_luc")
    private String ngayHieuLuc;

    @Column(name = "ngay_ket_thuc")
    private String ngayKetThuc;

    @Column(name = "note")
    private String note;

    @ManyToOne
    /*
     * @JoinColumn(name =
     * "khóa ngoại trong bảng 'hop_dong' kết nối với bảng 'project' (project_id)"
     * )
     */
    @JoinColumn(name = "project_id")
    private ProjectEntity project; // nhiều 'hop_dong' thuộc 1 'project' => hứng 1 bản ghi

    public String getNgayKy() {
        return this.ngayKy;
    }

    public void setNgayKy(String ngayKy) {
        this.ngayKy = ngayKy;
    }

    public String getNgayHieuLuc() {
        return this.ngayHieuLuc;
    }

    public void setNgayHieuLuc(String ngayHieuLuc) {
        this.ngayHieuLuc = ngayHieuLuc;
    }

    public String getNgayKetThuc() {
        return this.ngayKetThuc;
    }

    public void setNgayKetThuc(String ngayKetThuc) {
        this.ngayKetThuc = ngayKetThuc;
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
