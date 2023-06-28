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

import vn.ansv.management.dto.Detail.SupportBaoLanhHopDongDTO;

/* ===== BaoLanhThhdRepository.findDetailTabHopDong() ===== */
@NamedNativeQuery(name = "BaoLanhThhdEntity.findDetailTabHopDong", query = "SELECT "
        + "blthhd.id, blthhd.ngay_phat_hanh AS ngayPhatHanh, blthhd.ngay_het_han AS ngayHetHan, blthhd.note "
        + "FROM bao_lanh_thhd AS blthhd "
        + "INNER JOIN project AS p ON blthhd.project_id = p.id "
        + "INNER JOIN project_report AS pr ON p.id = pr.project_id "
        + "WHERE pr.id = :projectReportId AND pr.enabled = :enabled "
        + "ORDER BY blthhd.modified_at DESC LIMIT 1", resultSetMapping = "Mapping.SupportBaoLanhHopDongDTO")

/* ===== Set mapping: SupportBaoLanhHopDongDTO ===== */
@SqlResultSetMapping(name = "Mapping.SupportBaoLanhHopDongDTO", classes = @ConstructorResult(targetClass = SupportBaoLanhHopDongDTO.class, columns = {
        @ColumnResult(name = "id", type = Long.class),
        @ColumnResult(name = "ngayPhatHanh", type = String.class),
        @ColumnResult(name = "ngayHetHan", type = String.class),
        @ColumnResult(name = "note", type = String.class) }))

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
