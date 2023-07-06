package vn.ansv.management.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedNativeQuery;
import javax.persistence.Table;

/* ===== BaoLanhTuRepository.findDetailTabHopDongByReport() ===== */
@NamedNativeQuery(name = "BaoLanhTuEntity.findDetailTabHopDongByReport", query = "SELECT "
        + "bltu.id, bltu.ngay_phat_hanh AS ngayPhatHanh, bltu.ngay_het_han AS ngayHetHan, bltu.note "
        + "FROM bao_lanh_tu AS bltu "
        + "INNER JOIN project AS p ON bltu.project_id = p.id "
        + "INNER JOIN project_report AS pr ON p.id = pr.project_id "
        + "WHERE pr.id = :projectReportId AND pr.enabled = :enabled "
        + "ORDER BY bltu.modified_at DESC LIMIT 1", resultSetMapping = "Mapping.SupportBaoLanhHopDongDTO")

/* ===== BaoLanhTuRepository.findDetailTabHopDongById() ===== */
@NamedNativeQuery(name = "BaoLanhTuEntity.findDetailTabHopDongById", query = "SELECT "
        + "bltu.id, bltu.ngay_phat_hanh AS ngayPhatHanh, bltu.ngay_het_han AS ngayHetHan, bltu.note "
        + "FROM bao_lanh_tu AS bltu "
        + "WHERE bltu.id = :id", resultSetMapping = "Mapping.SupportBaoLanhHopDongDTO")

/* ===== Set mapping: SupportBaoLanhHopDongDTO ===== */
// @SqlResultSetMapping(name = "Mapping.SupportBaoLanhHopDongDTO", classes =
// @ConstructorResult(targetClass = SupportBaoLanhHopDongDTO.class, columns = {
// @ColumnResult(name = "id", type = Long.class),
// @ColumnResult(name = "ngayPhatHanh", type = String.class),
// @ColumnResult(name = "ngayHetHan", type = String.class),
// @ColumnResult(name = "note", type = String.class) }))

@Entity
@Table(name = "bao_lanh_tu")
public class BaoLanhTuEntity extends BaseEntity {
    @Column(name = "ngay_phat_hanh")
    private String ngayPhatHanh;

    @Column(name = "ngay_het_han")
    private String ngayHetHan;

    @Column(name = "note")
    private String note;

    @ManyToOne
    /*
     * @JoinColumn(name =
     * "khóa ngoại trong bảng 'bao_lanh_tu' kết nối với bảng 'project' (project_id)"
     * )
     */
    @JoinColumn(name = "project_id")
    private ProjectEntity project; // nhiều 'bao_lanh_tu' thuộc 1 'project' => hứng 1 bản ghi

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